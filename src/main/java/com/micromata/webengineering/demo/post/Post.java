package com.micromata.webengineering.demo.post;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * This Pojo represents a Post
 *
 * Created by Jonas Scherbaum on 24.04.2017.
 */
@Entity
public class Post {

    @Id
    @GeneratedValue
    private Long id;

    private String title;
    private Date createdAt;

    /**
     * create a Post with the given title
     *
     * @param title the title of the Post
     */
    public Post(String title) {
        this.title = title;
        this.createdAt = new Date();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Long getId() {
        return id;
    }
}
