package cn.edu.niit.servlet;

import cn.edu.niit.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

@WebServlet(name = "AdminLoginServlet", urlPatterns = "/admin/login")
public class AdminLoginServlet extends HttpServlet {

    private UserService userService = new UserService();

    @Override
    protected void doGet(HttpServletRequest req,
                         HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req,
                          HttpServletResponse resp) throws ServletException, IOException {

        String username = req.getParameter("username");
        String password = req.getParameter("password");

        String result = userService.adminLogin(username, password,
                req.getSession());
        if ("1".equals(result)) {
            resp.sendRedirect("/admin/main.jsp");
        } else {
            req.getRequestDispatcher("/index.jsp?message=" + URLEncoder.encode(result, "utf-8")).forward(req, resp);
        }
    }
}
