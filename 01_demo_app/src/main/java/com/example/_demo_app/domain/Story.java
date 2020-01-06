package com.example._demo_app.domain;

import javax.persistence.*;
import java.util.Date;

@Entity(name="stories") //Az adatbázis nevét megváltoztathatjuk, viszont a kódban Story továbbra is
public class Story {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String title;
    @Column(length = 10000, columnDefinition = "TEXT") //A tartalom méretét bővíthetjük, varchar helyett más adattípust is választhatunk
    private String content;
    private Date posted;
    //private String author;

    @ManyToOne // Sok sztori egy bloggerhez
    private Blogger blogger;

    //JPA-nél fontos a privát konstruktor --> Vagy mégsem? hibát jelez
    private Story() {
    }

    public Story(String title, String content, Date posted, Blogger blogger){
        this.title = title;
        this.content = content;
        this.posted = posted;
        this.blogger = blogger;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getPosted() {
        return posted;
    }

    public void setPosted(Date posted) {
        this.posted = posted;
    }

    public Blogger getBlogger() {
        return blogger;
    }

    public void setBlogger(Blogger blogger) {
        this.blogger = blogger;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
