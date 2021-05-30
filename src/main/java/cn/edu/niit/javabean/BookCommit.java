package cn.edu.niit.javabean;

public class BookCommit {
    private int id;
    private int bookid;
    private String commit;

    public BookCommit() {
    }

    public BookCommit(int id, int bookid, String commit) {
        this.id = id;
        this.bookid = bookid;
        this.commit = commit;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBookid() {
        return bookid;
    }

    public void setBookid(int bookid) {
        this.bookid = bookid;
    }

    public String getCommit() {
        return commit;
    }

    public void setCommit(String commit) {
        this.commit = commit;
    }
}
