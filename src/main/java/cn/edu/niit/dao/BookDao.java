package cn.edu.niit.dao;

import cn.edu.niit.db.JDBCUtil;
import cn.edu.niit.javabean.Book;
import cn.edu.niit.javabean.Borrow_books;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
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
    public List<Book> selectBorrow(int card_id,int pageNum,int pageSize){
        String sql = "select books.*,borrow_books.book_id,book_sort.name as sort from books,borrow_books,book_sort where books.id=borrow_books.book_id and books.sort_id=book_sort.id and card_id=? GROUP BY books.id limit ?,?";
        List<Book> books = new ArrayList<>();
        try (ResultSet rs =
                     JDBCUtil.getInstance().executeQueryRS(sql,
                             new Object[]{card_id,(pageNum - 1) * pageSize,
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
    public List<Borrow_books> selectAllBorrow(int card_id,int pageNum,int pageSize){
        String sql="SELECT * FROM borrow_books WHERE card_id=? LIMIT ?,?";
        String sql1="SELECT DISTINCT  books.name AS sort FROM books,borrow_books WHERE books.id=?";
        List<Borrow_books> borrow_books=new ArrayList<>();
        try (ResultSet rs =
                     JDBCUtil.getInstance().executeQueryRS(sql,
                             new Object[]{card_id,(pageNum - 1) * pageSize,
                                     pageSize})) {

            while (rs.next()) {
                ResultSet rs1=JDBCUtil.getInstance().executeQueryRS(sql1,
                        new Object[]{rs.getInt("book_id")}
                        );

                Borrow_books borrow_book = new Borrow_books(rs.getInt("id"),
                        rs.getInt("card_id"),
                        rs.getInt("book_id"),
                        rs.getString("borrow_date"),
                        rs.getString("end_date"),
                        rs.getString("return_date"),
                        rs.getString("illegal"),
                        rs.getString("manager_id")
                );
                while (rs1.next()){
                    borrow_book.setBook_name(rs1.getString("sort"));
                }
                borrow_books.add(borrow_book);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return borrow_books;
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
    public boolean selectStoreBookExist(String username,String bookId){
        String sql="select EXISTS (SELECT 1 FROM library.borrow_books where book_id=? and card_id=? )as store";
        try{
            ResultSet result=JDBCUtil.getInstance().executeQueryRS(sql,new Object[]{bookId,username});
            while (result.next()) {
                return result.getBoolean("store");
            }
        }
        catch (Exception e){

        }
        return false;
    }
    public String selectBookid(String str){
        String sql="SELECT books.id FROM books WHERE books.name=?";
        try {
            ResultSet resultSet=JDBCUtil.getInstance().executeQueryRS(sql,new Object[]{
                    str
            });
            while (resultSet.next()){
                return resultSet.getString("id");
            }
        }catch (Exception e){

        }

        return null;
    }
    //增加借阅图书
    public int insertStoreBook(String username, String bookId) {
        java.util.Date date= new java.util.Date();
        SimpleDateFormat format= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr =format.format(date);
        java.util.Date dateend= new java.util.Date(); //取时间
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(dateend);
        calendar.add(calendar.DATE,7); //把日期往后增加一天,整数  往后推,负数往前移动
        dateend=calendar.getTime(); //这个时间就是日期往后推一天的结果
        String enddate=format.format(dateend);
        String sql = "insert into borrow_books(book_id, card_id, " +
                "borrow_date,end_date) values(?,?,?,?)";
        int result = JDBCUtil.getInstance().executeUpdate(sql,
                new Object[]{
                        bookId, username,
                        dateStr,enddate
                });
        return result;
    }
    public Timestamp selectReturnDateTime(int id){
        Timestamp timestamp = null;
        String sql="select return_date from borrow_books where id=?";
        ResultSet resultSet = JDBCUtil.getInstance().executeQueryRS(sql, new Object[]{id});
       try{
           while (resultSet.next()){
               timestamp=resultSet.getTimestamp("return_date");
           }
       }catch (Exception e){

       }
        return timestamp;
    }

    public Timestamp selectEndDateTime(int id){
        Timestamp timestamp = null;
        String sql="select end_date from borrow_books where id=?";
        ResultSet resultSet = JDBCUtil.getInstance().executeQueryRS(sql, new Object[]{id});
        try{
            while (resultSet.next()){
                timestamp=resultSet.getTimestamp("end_date");
            }
        }catch (Exception e){

        }
        return timestamp;
    }
    public int updateReturnBook(int id){
        java.util.Date date= new java.util.Date();
        SimpleDateFormat format= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr =format.format(date);


        String sql="update borrow_books set return_date=? where id=?";
        int result=JDBCUtil.getInstance().executeUpdate(sql,new Object[]{
                dateStr,id
        });

        Timestamp begindate = selectReturnDateTime(id);
        Timestamp returndate = selectEndDateTime(id);
        if (begindate.before(returndate)){
            System.out.println("无违约");
        }
        else{
            long timeDifference = getTimeDifference(returndate,begindate );
            System.out.println(timeDifference);
        }

    return result;
    }
    public long getTimeDifference(Timestamp formatTime1, Timestamp formatTime2) {
        Timestamp currentTime = formatTime1;
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Timestamp createTime = formatTime2;
        long l=currentTime.getTime()-createTime.getTime();
        long day=l/(24*60*60*1000);
        return day;
    }
    public int illegal(int enddate,int returndate){
        int date=returndate-enddate;
        if (date>0){
            return date;
        }
        else if (date<=0){
            return 0;
        }
        else{
            return 0;
        }
    }
    public int insertCollectionBook(String username, String bookId) {
        String sql = "insert into favorite_books(bookid, cardid) values(?,?)";
        int result = JDBCUtil.getInstance().executeUpdate(sql,
                new Object[]{
                        bookId, username
                });
        return result;
    }
    public Boolean selectCollectionBookExist(String username, String bookId){
        String sql="select EXISTS (SELECT 1 FROM library.favorite_books where bookid=? and cardid=? )as collection";
        try{
            ResultSet result=JDBCUtil.getInstance().executeQueryRS(sql,new Object[]{bookId,username});
            while (result.next()) {
                return result.getBoolean("collection");
            }
        }
        catch (Exception e){

        }
        return false;
    }
    public int delectCollectionBookExist(String username,String bookId){
        String sql="delete from library.favorite_books where bookid=? and cardid=?";
        int result=JDBCUtil.getInstance().executeUpdate(sql,new Object[]{bookId,username});
    return result;
    }

   public List<Borrow_books> selectBorrow_books(String card_id,String book_id) throws Exception{
        Borrow_books borrow_books;
        String sql="SELECT * FROM borrow_books WHERE card_id=? AND book_id=?";
        List<Borrow_books> borrow_booksList = new ArrayList<>();
        ResultSet resultSet=JDBCUtil.getInstance().executeQueryRS(sql,new Object[]{card_id,book_id});
        while (resultSet.next()){
            borrow_books=new Borrow_books(resultSet.getInt("id"),
                    resultSet.getInt("card_id"),
                    resultSet.getInt("book_id"),
                    resultSet.getString("borrow_date"),
                    resultSet.getString("end_date"),
                    resultSet.getString("return_date"),
                    resultSet.getString("illegal"),
                    resultSet.getString("manager_id")
                    );
            borrow_booksList.add(borrow_books);
        }

        return borrow_booksList;
   }
}
