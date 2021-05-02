package cn.edu.niit.servlet;

import cn.edu.niit.javabean.User;
import cn.edu.niit.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

/**
 * @program: Library
 * @ClassName: RegisterServlet
 * @description: Test
 * @author: Cai
 * @create: 2021-03-23 01:16
 **/
@WebServlet(name = "RegisterServlet", urlPatterns = "/register")
public class RegisterServlet extends HttpServlet {
    UserService registerService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User register = null;

        String result = "注册失败";
        //1【调用请求对象】读取【请求头】参数信息，得到用户注册信息
        String userName, password, reader, confirmPassword;
        userName = req.getParameter("username");
        password = req.getParameter("password");
        reader = req.getParameter("reader");
        confirmPassword = req.getParameter("repassword");
        if (password != null && password.equals(confirmPassword)) {
            register = new User(userName, password, reader);

            result = registerService.register(register);

            //注册成功：——>跳转至登录页面进行登录
            //注册失败：——>注册页面提示：注册失败
            if (result.equals("注册成功")) {
                // 注册
                resp.sendRedirect("/index.jsp?message=" + URLEncoder.encode(result, "utf-8"));
            } else {
                req.getRequestDispatcher("/register.jsp?message=" + result).forward(req, resp);

            }
        } else {
            req.getRequestDispatcher("/register.jsp?message=" +
                    "两次密码不一致").forward(req, resp);
        }
    }
}

