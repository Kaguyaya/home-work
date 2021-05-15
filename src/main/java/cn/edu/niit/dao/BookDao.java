package cn.edu.niit.dao;

import cn.edu.niit.db.JDBCUtil;
import cn.edu.niit.javabean.Book;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDao {
    //查询所有图书
    public List<Book> selectAll(int pageNum, int pageSize) {
        String sql = "select books.*, book_sort.name as sort " +
                "from books, book_sort where " +
                "books.sort_id=book_sort.id limit ?,?";

        List<Book> books = new ArrayList<>();
        try (ResultSet rs =
                     JDBCUtil.getInstance().executeQueryRS(sql,
                             new Object[]{(pageNum - 1) * pageSize,
                                     pageSize})) {

            while (rs.next()) {
                Book book = new Book(rs.getInt("id") + "",
                        rs.getString(
                                "name"),
                        rs.getString("author"),
                        rs.getString("sort"),
                        rs.getString("description"));
                books.add(book);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }
    //查询收藏图书
    public List<Book> selectFavorite(int pageNum, int pageSize){
        //此处多加了一个是否被收藏的判断
        String sql = "select books.*,borrow_books.book_id,book_sort.name as sort from books,borrow_books,book_sort where books.id=borrow_books.book_id and books.sort_id=book_sort.id GROUP BY books.id limit ?,?";

        List<Book> books = new ArrayList<>();
        try (ResultSet rs =
                     JDBCUtil.getInstance().executeQueryRS(sql,
                             new Object[]{(pageNum - 1) * pageSize,
                                     pageSize})) {

            while (rs.next()) {
                Book book = new Book(rs.getInt("id") + "",
                        rs.getString("name"),
                        rs.getString("author"),
                        rs.getString("sort"),
                        rs.getString("description"));
                books.add(book);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }
    //查询行数
    public int count() {
        String sql = "select count(*) as countNum from books";
        try (ResultSet rs =
                     JDBCUtil.getInstance().executeQueryRS(sql,
                             new Object[]{})) {

            while (rs.next()) {
                int count = rs.getInt("countNum");
                return count;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
    //查询是否被借阅
    public boolean selectStore(String username, String bookId) {
        String sql1 = "select EXISTS( SELECT 1 from favorite_books " +
                "where bookid=? and cardid=?) as store";
        try (ResultSet rs =
                     JDBCUtil.getInstance().executeQueryRS(sql1,
                             new Object[]{
                                     bookId, username
                             });) {

            while (rs.next()) {
                return rs.getBoolean("store");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;

    }
    //增加借阅图书
    public int insertStoreBook(String username, String bookId) {
        String sql = "insert into borrow_books(book_id, card_id, " +
                "borrow_date) values(?,?,?)";
        int result = JDBCUtil.getInstance().executeUpdate(sql,
                new Object[]{
                        bookId, username,
                        new  Date(System.currentTimeMillis())
                });
        return result;
    }

    public int insertCollectionBook(String username, String bookId) {
        String sql = "insert into favorite_books(bookid, cardid) values(?,?)";
        int result = JDBCUtil.getInstance().executeUpdate(sql,
                new Object[]{
                        bookId, username
                });
        return result;
    }
}
