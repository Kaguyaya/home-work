package cn.edu.niit.servlet;

import cn.edu.niit.javabean.Book;
import cn.edu.niit.javabean.Borrow_books;
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

@WebServlet(name = "BorrowHistoryServlet",urlPatterns = "/book/borrowhistory")
public class BorrowHistoryServlet extends HttpServlet {
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
        String param = (String) parseObject.get("search");

        int pageNum = (int) parseObject.get("pageNum");
        int pageSize = (int) parseObject.get("pageSize");
        List<Borrow_books> borrow_books = new ArrayList<>();
        int count = 0;
        //2.
        if (param != null) {
            //带参数查询
        } else {
            //无参查询
            borrow_books = bookService.selectAllBorrow_bookslist((String) req.getSession().getAttribute("id"), pageNum,
                    pageSize);
        }

        count = bookService.countNum();
        //3. 将结果放入session
        req.getSession().setAttribute("borrow_books", borrow_books);
        //将count直接作为ajax请求的结果返回
        resp.getWriter().print(count);
    }
}
