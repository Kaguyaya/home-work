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

@WebServlet(name = "StoreBookServlet", urlPatterns = "/book/store")
public class StoreBookServlet extends HttpServlet {

    private BookService bookService = new BookService();

    @Override
    protected void doPost(HttpServletRequest req,
                          HttpServletResponse resp) throws ServletException, IOException {

        //1. 取参（req当前的页码, 每页的数量, 搜索）
        String paramJson = IOUtils.toString(
                req.getInputStream(), "UTF-8");
        HashMap<String, Object> parseObject =
                JSON.parseObject(paramJson,
                        HashMap.class);
        String username = (String) parseObject.get("user");
        String bookname = (String) parseObject.get("book");
        String bookid=bookService.getBooksid(bookname);
        String message = bookService.storeBook(username, bookid);
        resp.getWriter().print(message);
    }
}
