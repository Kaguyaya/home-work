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
import java.util.HashMap;

@WebServlet(name = "BorrowBookTableServlet",urlPatterns = "/book/boorowtable")
public class BorrowBookTableServlet extends HttpServlet {
    private BookService bookService = new BookService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String paramJson = IOUtils.toString(
                req.getInputStream(), "UTF-8");
        HashMap<String, Object> parseObject =
                JSON.parseObject(paramJson,
                        HashMap.class);
        int id = (int) parseObject.get("id");
        String result=bookService.returnBook(id);
        resp.getWriter().print(result);
    }
}
