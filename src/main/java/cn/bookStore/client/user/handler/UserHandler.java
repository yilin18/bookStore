package cn.bookStore.client.user.handler;

import cn.bookStore.commons.beans.User;
import cn.bookStore.client.user.service.IUserService;
import cn.bookStore.utils.ActiveCodeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * company: www.abc.com
 * Author: 苏依林
 * Create Data: 2019/4/17
 */
@Controller
@RequestMapping("/user")
public class UserHandler {
    @Autowired
    private IUserService userService;

    @RequestMapping("/register.do")
    public String register(User user, String textinput, HttpServletRequest request) {
        String check = (String) request.getSession().getAttribute("checkcode_session");
        if (textinput.equals(check)) {
            //验证码正确执行插入操作!
            //System.out.println("nihao"+textinput+user);
            //设置激活码
            user.setActiveCode(ActiveCodeUtils.createActiveCode());
            request.setAttribute("activeCode", user.getActiveCode());
            int row = userService.addUser(user, request);
            if (row > 0) {
                //插入成功
                //System.out.println("插入成功Handler");
                //返回插入成功页面!
                return "/client/registersuccess.jsp";
            }else
                return "/client/fail.jsp";

        } else {
            //System.out.println("验证码错误!");
            //验证码错误,重新注册!
            request.setAttribute("msg","验证码错误,请重新注册");
            request.setAttribute("user", user);
            return "/client/register.jsp";
        }
    }

    @RequestMapping("/active.do")
    public String active(String activeCode) {
        //System.out.println("进入激活");
        int row = userService.active(activeCode);
        if (row > 0) {
            return "/client/activesuccess.jsp";
        } else
            return "/client/fail.jsp";
    }

    @RequestMapping("/findMail.do")
    @ResponseBody
    public String findMail(String email) {
        User user = userService.findMail(email);
        //System.out.println(user);
        if (user != null) {
            //System.out.println("该邮箱已注册,请更换一个!");
            return "EXIST";
        }
        return "OK";
    }

    @RequestMapping("/findUsername.do")
    @ResponseBody
    public String findUsername(String username) {
        User user = userService.findUsername(username);
        //System.out.println(user);
        if (user != null) {
            //System.out.println("该用户名已存在,请更换一个!");
            return "EXIST";
        }
        return "OK";
    }

    @RequestMapping("/login.do")
    public String loginByLogin(User user, HttpServletRequest request, HttpServletResponse response, String checkUsername, String autoLogin, HttpSession session) {
        //System.out.println(user);
        // System.out.println("登陆选项"+checkUsername+autoLogin);
        user = userService.findUserByLogin(user);
        // System.out.println(user);
        //用户存在登陆成功,设置cookie
        if (user != null) {
            if (!(user.getState() == 1)) {
                request.setAttribute("msg", "请先去往邮箱激活再登陆!");
                return "/client/login.jsp";
            }
            //model.addAttribute("user",user);
            //先将查询出的账户放入session以供前台调用和保存登陆信息
            session.setAttribute("login_user", user);
            // 设置cookie
            if ("1".equals(checkUsername)||"1".equals(autoLogin)) {
                addCookie(autoLogin, request, user, response);
            }
            return "/client/myAccount.jsp";
        }
        //该用户不存在,转向登陆
        request.setAttribute("msg", "该用户不存在...请重新登陆!");
        return "/client/login.jsp";
    }

    private void addCookie(String autoLogin, HttpServletRequest request, User user, HttpServletResponse response) {
        try {
            Cookie cookie1 = null;
            cookie1 = new Cookie("username_cookie", URLEncoder.encode(user.getUsername(), "utf-8"));
            cookie1.setMaxAge(60 * 60);
            cookie1.setPath(request.getContextPath() + "/");
            response.addCookie(cookie1);
            if ("1".equals(autoLogin)) {
                Cookie cookie2 = new Cookie("password_cookie", user.getPassword());
                cookie2.setMaxAge(60 * 60);
                cookie2.setPath(request.getContextPath() + "/");
                response.addCookie(cookie2);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("/modifyUser.do")
    public String modifyUser(String flag, Model model, HttpSession session, User user) {
        if (flag == null) {
            user = (User) session.getAttribute("login_user");
            //System.out.println("session"+user);

            return "/client/modifyuserinfo.jsp";
        }
        User user1 = (User) session.getAttribute("login_user");
        user.setId(user1.getId());
        // System.out.println("修改user"+user);
        int row = userService.modifyUser(user);
        if (row > 0) {
            // System.out.println("修改成功");
            session.removeAttribute("login_user");
            model.addAttribute("msg", "信息修改成功,请重新登录");
            return "/client/login.jsp";
        } else
            // System.out.println("修改失败");
            return "/client/fail.jsp";
    }

    @RequestMapping("/myAccount.do")
    public String myAccount(HttpSession session, Model model, HttpServletRequest request) {
        User user = (User) session.getAttribute("login_user");
        if (user == null) {
            //session里没有用户信息,查看是否设置自动登录
            User user_cookie = autoLogin(request);
            // System.out.println("cookie缓存所查询的"+user_cookie);
            if (user_cookie.getUsername() != null&&user_cookie.getPassword()!=null) {
                //用cookie里的数据尝试登陆
                user = userService.findUserByLogin(user_cookie);
                if (user != null) {
                    session.setAttribute("login_user", user);
                    // System.out.println("使用cookie数据登陆");
                    return "/client/myAccount.jsp";
                } else {
                    model.addAttribute("msg", "您的登陆信息已过期,请重新登陆");
                    return "/client/login.jsp";
                }
            }
            model.addAttribute("msg", "您还未登录,请登陆再试!");
            return "/client/login.jsp";
        }

        return "/client/myAccount.jsp";
    }

    public User autoLogin(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        String username = null;
        String password = null;
        User user = new User();
        for (Cookie cookie : cookies) {
            if ("username_cookie".equals(cookie.getName())) {
                try {
                    username = URLDecoder.decode(cookie.getValue(), "utf-8");
                    // System.out.println("存在用户名cookie");
                    request.setAttribute("username", username);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

            }
            if ("password_cookie".equals(cookie.getName())) {
                password = cookie.getValue();
                // System.out.println("存在自动登录cookie");
            }
        }
        user.setUsername(username);
        user.setPassword(password);
        // System.out.println("cookie里保存的数据遍历"+user);
        return user;
    }

    @RequestMapping("/logout.do")
    public String logout(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        session.removeAttribute("login_user");
        Cookie cookie1 = new Cookie("username_cookie", null);
        Cookie cookie2 = new Cookie("password_cookie", null);
        cookie1.setMaxAge(0);
        cookie1.setPath(request.getContextPath() + "/");
        cookie2.setMaxAge(0);
        cookie2.setPath(request.getContextPath() + "/");
        response.addCookie(cookie1);
        response.addCookie(cookie2);
        return "/client/login.jsp";
    }
}
