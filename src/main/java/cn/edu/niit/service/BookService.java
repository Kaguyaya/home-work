package cn.edu.niit.service;

import cn.edu.niit.dao.BookDao;
import cn.edu.niit.javabean.Book;

import java.util.List;

public class BookService {
    private BookDao bookDao = new BookDao();

    //搜索所有书的方法
    public List<Book> searchAllBooks(String username, int pageNum,
                                     int pageSize) {

        List<Book> books = bookDao.selectAll(pageNum, pageSize);
        for (Book book : books) {
            book.setStore(isStore(username, book.getId()));
        }
        return books;
    }

    public int countNum() {
        return bookDao.count();
    }
    //判断是否被借
    public boolean isStore(String username, String bookId) {

        return bookDao.selectStore(username, bookId);
    }
    //借书
    public String storeBook(String username, String bookId) {
        int result = bookDao.insertStoreBook(username, bookId);
        if (result > 0) {
            return "借阅成功";
        } else {
            return "借阅失败";
        }
    }

    public String collectionBook(String username, String bookId) {
        boolean isCollection=bookDao.selectCollectionBookExist(username,bookId);
        System.out.println(isCollection);
        if (isCollection){
           int isDelete= bookDao.delectCollectionBookExist(username,bookId);
           if (isDelete>0)
           {
               return "取消收藏";
           }
           else
           {
               return "收藏异常";
           }
        }

        int result = bookDao.insertCollectionBook(username, bookId);
        if (result > 0) {
            return "收藏成功";
        } else {
            return "收藏失败";
        }
    }

    //搜索加入到收藏的书
    public List<Book> favoriteList(String username, int pageNum,
                                   int pageSize){
        List<Book> books = bookDao.selectFavorite(pageNum, pageSize);
        for (Book book : books) {
            book.setStore(isStore(username, book.getId()));
        }
        return books;
    }
}
