package lk.ijse.ormCoursework.entity;

import javax.persistence.*;
import java.util.List;

@Entity(name = "book")
public class Book {
    @Id
    @Column(name = "b_id",length = 20)
    private String id;
    @Column(name = "b_title")
    private String title;
    @Column(name = "b_author")
    private String author;
    @Column(name = "b_genre")
    private String genre;
    @Column(name = "b_status")
    private String status;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER,mappedBy = "book")
    private List<Detail> detailList;

    public Book() {
    }

    public Book(String id, String title, String author, String genre, String status) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
