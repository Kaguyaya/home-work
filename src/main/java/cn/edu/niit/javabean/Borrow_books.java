package cn.edu.niit.javabean;


import com.alibaba.fastjson.annotation.JSONField;

public class Borrow_books {
    @JSONField(name = "id")
    private int id;
    @JSONField(name = "card_id")
    private int card_id;
    @JSONField(name = "book_id")
    private int book_id;
    @JSONField(name = "borrow_date")
    private String borrow_date;
    @JSONField(name = "end_date")
    private String end_date;
    @JSONField(name = "return_date")
    private String return_date;
    @JSONField(name = "illegal")
    private String illegal;
    @JSONField(name = "manager_id")
    private String manager_id;

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }
    @JSONField(name="book_name")
    private String book_name;
    public Borrow_books(int id, int card_id, int book_id, String borrow_date, String end_date, String return_date, String illegal, String manager_id) {
        this.id = id;
        this.card_id = card_id;
        this.book_id = book_id;
        this.borrow_date = borrow_date;
        this.end_date = end_date;
        this.return_date = return_date;
        this.illegal = illegal;
        this.manager_id = manager_id;
    }

    public Borrow_books() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCard_id() {
        return card_id;
    }

    public void setCard_id(int card_id) {
        this.card_id = card_id;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public String getBorrow_date() {
        return borrow_date;
    }

    public void setBorrow_date(String borrow_date) {
        this.borrow_date = borrow_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public String getReturn_date() {
        return return_date;
    }

    public void setReturn_date(String return_date) {
        this.return_date = return_date;
    }

    public String getIllegal() {
        return illegal;
    }

    public void setIllegal(String illegal) {
        this.illegal = illegal;
    }

    public String getManager_id() {
        return manager_id;
    }

    public void setManager_id(String manager_id) {
        this.manager_id = manager_id;
    }

    @Override
    public String toString() {
        return "Borrow_books{" +
                "id=" + id +
                ", card_id=" + card_id +
                ", book_id=" + book_id +
                ", borrow_date='" + borrow_date + '\'' +
                ", end_date='" + end_date + '\'' +
                ", return_date='" + return_date + '\'' +
                ", illegal='" + illegal + '\'' +
                ", manager_id='" + manager_id + '\'' +
                '}';
    }
}
