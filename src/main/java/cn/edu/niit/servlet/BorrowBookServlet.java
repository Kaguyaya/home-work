package cn.edu.niit.servlet;


import cn.edu.niit.service.BookService;
import com.alibaba.fastjson.JSON;
import org.apache.commons.io.IOUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;

@WebServlet(name = "BorrowBookServlet", urlPatterns = "/book/borrowbook")
public class BorrowBookServlet extends HttpServlet {

    private BookService bookService = new BookService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // response.setContentType("text/html");
        // 设置字符编码为UTF-8, 这样支持汉字显示
        // response.setCharacterEncoding("UTF-8");

        resp.setContentType("text/html;charset=utf-8");

        /** 设置响应头允许ajax跨域访问 **/
        resp.setHeader("Access-Control-Allow-Origin", "*");
        /* 星号表示所有的异域请求都可以接受， */
        resp.setHeader("Access-Control-Allow-Methods", "GET,POST");



        //将建json对象转换为java对象


    }
}
