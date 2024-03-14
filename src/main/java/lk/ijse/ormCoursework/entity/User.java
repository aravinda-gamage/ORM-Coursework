package lk.ijse.ormCoursework.entity;

import javax.persistence.*;

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

    public User(String userId, String userName, String password, List<Detail> detailList) {
        this.userId = userId;
        this.userName = userName;
        Password = password;
        this.detailList = detailList;
    }
}
