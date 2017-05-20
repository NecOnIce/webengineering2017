package com.micromata.webengineering.demo.user;

import com.micromata.webengineering.demo.DefaultEntity;
import com.micromata.webengineering.demo.post.Post;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.List;

/**
 * This class represents a User
 *
 * Created by Jonas Scherbaum on 20.05.2017.
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@AttributeOverride(name = "id", column = @Column(name = "post_id",
        nullable = false, columnDefinition = "BIGINT"))
@Data
@NoArgsConstructor
public class User extends DefaultEntity{

    @Column
    private String username;

    @Column
    private String password;

    @Column
    private String email;

    @OneToMany
    private List<Post> posts;
}
