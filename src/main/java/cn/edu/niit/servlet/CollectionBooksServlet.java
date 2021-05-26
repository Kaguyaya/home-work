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

@WebServlet(name = "CollectionBooksServlet", urlPatterns = "/book/collection")
public class CollectionBooksServlet extends HttpServlet {
    private BookService bookService = new BookService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String paramJson = IOUtils.toString(
                req.getInputStream(), "UTF-8");
        HashMap<String, Object> parseObject =
                JSON.parseObject(paramJson,
                        HashMap.class);
        String username = (String) parseObject.get("user");
        String bookId = (String) parseObject.get("book");
        String message = bookService.collectionBook(username,bookId);
        resp.getWriter().print(message);
    }
}
