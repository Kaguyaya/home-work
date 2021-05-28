package cn.edu.niit.service;

import cn.edu.niit.dao.BookDao;
import cn.edu.niit.javabean.Book;
import cn.edu.niit.javabean.Borrow_books;
import cn.edu.niit.javabean.ListJson;

import java.util.ArrayList;
import java.util.List;

public class BookService {
    private BookDao bookDao = new BookDao();

    //搜索所有书的方法
    public List<Book> searchAllBooks(String username, int pageNum,
                                     int pageSize) {

        List<Book> books = bookDao.selectAll(pageNum, pageSize);
        for (Book book : books) {
            book.setStore(isStore(username, book.getId()));
            book.setIsborrow(isBorrow(username,book.getId()));
        }
        return books;
    }

    public int countNum() {
        return bookDao.count();
    }
    public boolean isBorrow(String username,String bookId){
        return bookDao.selectStoreBookExist(username,bookId);
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
    public String getBooksid(String bookname){
        String result=bookDao.selectBookid(bookname);
        return result;
    }
    public String returnBook(int id){
        int result=bookDao.updateReturnBook(id);
        if (result>0){
            return "归还成功";
        }else{
            return "归还失败";
        }
    }
    public String collectionBook(String username, String bookId) {
        boolean isCollection=bookDao.selectCollectionBookExist(username,bookId);
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
    public List<Book> borrowList(String username,int pageNum,int pageSize){
        List<Book> books=bookDao.selectBorrow(Integer.parseInt(username),pageNum,pageSize);
        for (Book book : books) {
            book.setStore(isStore(username, book.getId()));
        }
        return books;
    }
    public List<Borrow_books> selectAllBorrow_bookslist(String username,int pageNum,int pageSize){
    List<Borrow_books> borrow_books=bookDao.selectAllBorrow(Integer.parseInt(username),pageNum,pageSize);
    return borrow_books;
    }
    public List<Borrow_books> borrow_booksList(String card_id, String book_id){
        List<Borrow_books> borrow_booksList=new ArrayList<>();
        try {
          borrow_booksList= new BookDao().selectBorrow_books(card_id,book_id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return borrow_booksList;
    }

    public ListJson jsonBorrow_bookList(String card_id,String book_id){
        ListJson listJson=new ListJson(0,
                "",
                1000,
                borrow_booksList(card_id,book_id));
        return listJson;
    }
}
