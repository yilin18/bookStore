package cn.bookStore.utils;

import cn.bookStore.commons.beans.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;
import java.io.IOException;

/**
 * company: www.abc.com
 * Author: 苏依林
 * Create Data: 2019/4/25
 */
public class loginTag extends SimpleTagSupport {
    public void doTag() throws IOException {
        PageContext context = (PageContext) this.getJspContext();
        HttpServletRequest request = (HttpServletRequest) context.getRequest();
        HttpServletResponse response = (HttpServletResponse) context.getResponse();
        User user = (User) context.getSession().getAttribute("login_user");
        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/client/error/privilege.jsp");
        }

    }
}
