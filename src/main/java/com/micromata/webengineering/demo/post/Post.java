package com.micromata.webengineering.demo.post;

import java.time.LocalDateTime;
import java.util.concurrent.atomic.AtomicLong;

/**
 * This Pojo represents a Post
 *
 * Created by Jonas Scherbaum on 24.04.2017.
 */
public class Post {

    private Long id;
    private String title;
    private LocalDateTime createdAt;

    static AtomicLong ID_COUNTER = new AtomicLong();

    /**
     * create a Post with the given title
     *
     * @param title the title of the Post
     */
    public Post(String title) {
        this.title = title;
        this.createdAt = LocalDateTime.now();
        this.id = ID_COUNTER.incrementAndGet();
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

    public Long getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Post post = (Post) o;

        return id != null ? id.equals(post.id) : post.id == null;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
