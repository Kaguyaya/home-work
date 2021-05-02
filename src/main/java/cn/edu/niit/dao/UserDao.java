package cn.edu.niit.dao;

import cn.edu.niit.db.JDBCUtil;
import cn.edu.niit.javabean.Admin;
import cn.edu.niit.javabean.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {

    public User selectOne(String username) {
        User user = null;
        try (ResultSet resultSet =
                     JDBCUtil.getInstance().executeQueryRS("select " +
                                     "* " +
                                     "from " +
                                     "borrow_card where username=?",
                             new Object[]{username})) {

            while (resultSet.next()) {
                user = new User(resultSet.getString("username"),
                        resultSet.getString("password"),
                        resultSet.getString("reader"),
                        resultSet.getString("header"),
                        resultSet.getString("cellphone"),
                        resultSet.getString("email"),
                        resultSet.getString("describe"),
                        resultSet.getBoolean("sex"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return user;
    }

    public Admin selectOne(String username, String password) {
        Admin admin = null;
        try (ResultSet resultSet =
                     JDBCUtil.getInstance().executeQueryRS("select " +
                                     "* " +
                                     "from " +
                                     "admin where username=?",
                             new Object[]{username})) {

            while (resultSet.next()) {
                admin = new Admin(resultSet.getString("username"),
                        resultSet.getString("password"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return admin;
    }

    public int addUser(User register) {
        String sql = "INSERT IGNORE INTO `borrow_card` (username," +
                "`password`, reader) \n" +
                "SELECT ?,?,? WHERE NOT EXISTS (\n" +
                "SELECT 1 FROM borrow_card WHERE `username`=?);";
        return JDBCUtil.getInstance().executeUpdate(sql,
                new Object[]{register.getUsername(),
                        register.getPassword(),
                        register.getReader(),
                        register.getUsername()});
    }

    public int updateOne(User user) {
        int result = 0;
        StringBuilder sb = new StringBuilder("update borrow_card " +
                "set reader=?, cellphone=?, email=?, sex=?, " +
                "borrow_card.`describe`=? ");
        if (user.getHeader() != null) {
            sb.append(", header=? where " +
                    "username=?");
            result =
                    JDBCUtil.getInstance().executeUpdate(sb.toString(),
                            new Object[]{user.getReader(),
                                    user.getCellphone(),
                                    user.getEmail(), user.isSex(),
                                    user.getDescribe(),
                                    user.getHeader(),
                                    user.getUsername()});
        } else {
            sb.append("where username=?");
            result =
                    JDBCUtil.getInstance().executeUpdate(sb.toString(),
                            new Object[]{user.getReader(),
                                    user.getCellphone(),
                                    user.getEmail(), user.isSex(),
                                    user.getDescribe(),
                                    user.getUsername()});
        }
        return result;
    }

}
