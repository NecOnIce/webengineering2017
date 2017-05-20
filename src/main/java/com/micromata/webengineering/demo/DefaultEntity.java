package com.micromata.webengineering.demo;

import javax.persistence.PrePersist;
import java.util.Date;

/**
 * Created by Jonas Scherbaum on 19.05.2017.
 */
public abstract class DefaultEntity {


    protected Date createdAt;

    @PrePersist
    void onCreate() {
        this.createdAt = new Date();
    }

    public Date getCreatedAt() {
        return createdAt;
    }
}
