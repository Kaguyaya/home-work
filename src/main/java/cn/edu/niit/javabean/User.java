package cn.edu.niit.javabean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String username;
    private String password;
    private String reader;
    private String header;
    private String cellphone;
    private String email;
    private String describe;
    private boolean sex;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public User(String username, String password, String describe) {
        this.username = username;
        this.password = password;
        this.describe = describe;
    }

    public User(String username, String nickname, String sex,
                String cellphone,
                String email, String remarks) {
        this.username = username;
        this.reader = nickname;
        if ("男".equals(sex)) {
            this.sex = true;
        } else {
            this.sex = false;
        }
        this.cellphone = cellphone;
        this.email = email;
        this.describe = remarks;
    }
}
