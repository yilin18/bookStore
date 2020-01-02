package cn.bookStore.client.user.dao;

import cn.bookStore.commons.beans.User;

/**
 * company: www.abc.com
 * Author: 苏依林
 * Create Data: 2019/4/17
 */
public interface IUserDao {
    int insertUser(User user);

    int active(String activeCode);

    User findMail(String email);

    User findUsername(String username);

    User selectUserByLogin(User user);

    int updateUser(User user);
}
