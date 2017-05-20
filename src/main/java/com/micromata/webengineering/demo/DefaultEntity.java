package com.micromata.webengineering.demo;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Jonas Scherbaum on 19.05.2017.
 */
@MappedSuperclass
public abstract class DefaultEntity {

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    protected Date createdAt;

    @PrePersist
    void onCreate() {
        this.createdAt = new Date();
    }

    public Date getCreatedAt() {
        return createdAt;
    }
}
