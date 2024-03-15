package lk.ijse.ormCoursework.entity;

import javax.persistence.*;
import java.util.Date;

@Entity(name = "detail")
public class Detail {
    @Id
    @Column(name = "d_id")
    private String Id;
    @Column(name = "d_g_date")
    private Date gDate;
    @Column(name = "d_r_date")
    private Date rDate;
    @Column(name = "d_user")
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
    @Column(name = "dBook")
    @ManyToOne
    @JoinColumn(name = "b_id")
    private Book book;

    public Detail() {
    }

    public Detail(String id, Date gDate, Date rDate, User user, Book book) {
        Id = id;
        this.gDate = gDate;
        this.rDate = rDate;
        this.user = user;
        this.book = book;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public Date getgDate() {
        return gDate;
    }

    public void setgDate(Date gDate) {
        this.gDate = gDate;
    }

    public Date getrDate() {
        return rDate;
    }

    public void setrDate(Date rDate) {
        this.rDate = rDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
