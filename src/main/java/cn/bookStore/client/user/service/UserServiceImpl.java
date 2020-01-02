package cn.bookStore.client.user.service;

import cn.bookStore.commons.beans.User;
import cn.bookStore.client.user.dao.IUserDao;
import cn.bookStore.utils.MailUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

/**
 * company: www.abc.com
 * Author: 苏依林
 * Create Data: 2019/4/17
 */
@Service
public class UserServiceImpl implements IUserService {
    @Resource
    private IUserDao userDao;
    @Override
    public int addUser(User user, HttpServletRequest request) {
        int row=userDao.insertUser(user);
        if (row>0){
            try {
                MailUtils.sendMail(user.getEmail(), "激活邮件", request);
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }

        return row;

    }

    @Override
    public int active(String activeCode) {
        return userDao.active(activeCode);
    }

    @Override
    public User findMail(String email) {
        return userDao.findMail(email);
    }

    @Override
    public User findUsername(String username) {
        return userDao.findUsername(username);
    }

    @Override
    public User findUserByLogin(User user) {
        user=userDao.selectUserByLogin(user);
        return user;
    }

    @Override
    public int modifyUser(User user) {
        return userDao.updateUser(user);
    }
}
