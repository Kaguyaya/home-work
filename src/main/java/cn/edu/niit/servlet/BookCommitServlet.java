package cn.edu.niit.servlet;

import cn.edu.niit.javabean.BookCommit;
import cn.edu.niit.service.BookService;
import com.alibaba.fastjson.JSON;
import org.apache.commons.io.IOUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@WebServlet(name = "BookCommitServlet",urlPatterns = "/book/commit")
public class BookCommitServlet extends HttpServlet {
    private BookService bookService = new BookService();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int book_commit_id = (int) req.getSession().getAttribute("book_commit_id");
        List<BookCommit> bookCommitList;
        bookCommitList=bookService.selectallcommit(book_commit_id);
        req.getSession().setAttribute("commits",bookCommitList);

    }
}
