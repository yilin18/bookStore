package cn.bookStore.client.user.service;

import cn.bookStore.commons.beans.User;

import javax.servlet.http.HttpServletRequest;

/**
 * company: www.abc.com
 * Author: 苏依林
 * Create Data: 2019/4/17
 */
public interface IUserService {
    int addUser(User user, HttpServletRequest request);

    int active(String activeCode);

    User findMail(String email);

    User findUsername(String username);

    User findUserByLogin(User user);

    int modifyUser(User user);
}
