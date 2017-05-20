package com.micromata.webengineering.demo.post;

import com.micromata.webengineering.demo.DefaultEntity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * This Pojo represents a Post
 *
 * Created by Jonas Scherbaum on 24.04.2017.
 */
@Entity
public class Post extends DefaultEntity {

    @Id
    @GeneratedValue
    private Long id;

    @Column(length = 512)
    private String title;

    public Post() {
    }

    /**
     * create a Post with the given title
     *
     * @param title the title of the Post
     */
    public Post(String title) {
        this.title = title;
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
