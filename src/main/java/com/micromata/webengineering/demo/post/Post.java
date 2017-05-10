package com.micromata.webengineering.demo.post;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicLong;

/**
 * This Pojo represents a Post
 *
 * Created by Jonas Scherbaum on 24.04.2017.
 */
public class Post {

    private int id;
    private String title;
    private LocalDateTime createdAt;

    /**
     * create a Post with the given title
     *
     * @param title the title of the Post
     */
    public Post(String title) {
        this.title = title;
        this.createdAt = LocalDateTime.now();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Post post = (Post) o;

        if (id != post.id) return false;
        if (title != null ? !title.equals(post.title) : post.title != null) return false;
        return createdAt != null ? createdAt.equals(post.createdAt) : post.createdAt == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        return result;
    }
}
