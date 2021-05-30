package cn.edu.niit.servlet;

import cn.edu.niit.service.BookService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "BookCommitComitServlet",urlPatterns = "/book/comit")
public class BookCommitComitServlet extends HttpServlet {
    private BookService bookService = new BookService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String commit = req.getParameter("textname");
        int book_commit_id = (int) req.getSession().getAttribute("book_commit_id");
        String result = bookService.bookCommitComit(book_commit_id, commit);

    }
}
