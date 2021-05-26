package cn.edu.niit.servlet;

import cn.edu.niit.service.BookService;
import com.alibaba.fastjson.JSON;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "BorrowBookListServlet",urlPatterns = "/book/borrowlist")
public class BorrowListServlet extends HttpServlet {
    private BookService bookService = new BookService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = (String)req.getSession().getAttribute("borrow_user");
        String bookId =(String) req.getSession().getAttribute("borrow_bookid");
        String message = JSON.toJSONString(bookService.jsonBorrow_bookList(username, bookId));
        PrintWriter out = resp.getWriter();
        resp.setContentType("text/html;charset=utf-8");
        out.print(message);
        out.flush();
        out.close();
    }
}
