package cn.bookStore.admin.user.handler;

import cn.bookStore.client.user.service.IUserService;
import cn.bookStore.commons.beans.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.mail.Session;
import javax.servlet.http.HttpSession;

/**
 * company: www.abc.com
 * Author: 苏依林
 * Create Data: 2019/5/21
 */
@RequestMapping("/admin/user")
@Controller
public class AdminUserHandler {
    @Autowired
    private IUserService userService;

    @RequestMapping("/adminUserLogin.do")
    public String adminUserLogin(User user, Model model, HttpSession session) {
        // System.out.println("管理员界面");
        user = userService.findUserByLogin(user);
        if (user != null) {
            if ("超级管理员".equals(user.getRole())) {

                session.setAttribute("admin_login", user);
                return "/admin/login/home.jsp";
            } else {
                return "/admin/error/privilege.jsp";
            }
        } else {
            model.addAttribute("msg", "用户名或密码错误,请重新输入!");
            return "/admin/login/login.jsp";
        }
    }

    @RequestMapping("/logout.do")
    public String logout(HttpSession session, Model model) {
        session.removeAttribute("admin_login");
        model.addAttribute("msg", "您已退出,请重新登录");
        return "/admin/login/login.jsp";
    }
}
