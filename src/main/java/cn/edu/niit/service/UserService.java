package cn.edu.niit.service;

import cn.edu.niit.dao.UserDao;
import cn.edu.niit.javabean.Admin;
import cn.edu.niit.javabean.User;

import javax.servlet.http.HttpSession;

public class UserService {

    private UserDao userDao = new UserDao();

    public String login(String username, String password,
                        HttpSession session) {
        User user = userDao.selectOne(username);
        if (user == null) {
            return "用户不存在";
        } else {
            if (password.equals(user.getPassword())) {
                session.setAttribute("user", user);
                session.setAttribute("isLogin", true);
                session.setAttribute("id", user.getUsername());
                return "1";
            } else {
                return "密码错误";
            }
        }
    }

    public String adminLogin(String username, String password,
                             HttpSession session) {
        Admin admin = userDao.selectOne(username, password);
        if (admin == null) {
            return "用户不存在";
        } else {
            if (password.equals(admin.getPassword())) {
                session.setAttribute("admin", admin);
                session.setAttribute("isLogin", true);
                return "1";
            } else {
                return "密码错误";
            }
        }
    }

    public String register(User register) {
        int result = userDao.addUser(register);
        if (result > 0) {
            return "注册成功";
        } else {
            return "用户已存在";
        }
    }

    public User getUserInfo(String username) {
        return userDao.selectOne(username);
    }

    public String uploadUserInfo(User user, HttpSession session) {
        int result = 0;
        result = userDao.updateOne(user);
        if (result > 0) {
            User userInfo = getUserInfo(user.getUsername());
            session.setAttribute("user", userInfo);
            return "更新成功";
        }
        return "更新失败";
    }
}
