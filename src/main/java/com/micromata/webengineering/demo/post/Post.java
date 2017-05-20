package com.micromata.webengineering.demo.post;

import com.micromata.webengineering.demo.DefaultEntity;
import com.micromata.webengineering.demo.user.User;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * This Pojo represents a Post
 *
 * Created by Jonas Scherbaum on 24.04.2017.
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@AttributeOverride(name = "id", column = @Column(name = "post_id",
        nullable = false, columnDefinition = "BIGINT"))
@Data
@NoArgsConstructor
public class Post extends DefaultEntity {

    public static final int POST_TITLE_LENGTH = 1024;

    @Column(length = POST_TITLE_LENGTH)
    private String title;

    @ManyToOne(optional = false)
    @JoinColumn
    private User author;
}
