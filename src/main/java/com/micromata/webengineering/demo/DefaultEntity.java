package com.micromata.webengineering.demo;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * This class is the base class for all Entities with the field created at and the entity id
 * (the id should be overridden)
 *
 * Created by Jonas Scherbaum on 19.05.2017.
 */
@MappedSuperclass
@Data
public abstract class DefaultEntity {

    @Id
    @GeneratedValue
    protected Long id;

    @Column
    @Temporal(TemporalType.TIMESTAMP)
    protected Date createdAt;

    /**
     * This method is called right before the entity is stored.
     * It is used to create the createdAt Field.
     */
    @PrePersist
    void onCreate() {
        this.createdAt = new Date();
    }
}
