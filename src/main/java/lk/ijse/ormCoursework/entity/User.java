package lk.ijse.ormCoursework.entity;

import javax.persistence.*;
import java.util.List;

@Entity(name = "user")
public class User {
    @Id
    @Column(name = "user_id", length = 20)
    private String userId;

    @Column(name = "user_Name")
    private String userName;

    @Column(name = "password")
    private String Password;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER,mappedBy = "user")
    private List<Detail> detailList;

    public User() {
    }

    public User(String userId, String userName, String password) {
        this.userId = userId;
        this.userName = userName;
        Password = password;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
