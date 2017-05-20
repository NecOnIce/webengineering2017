package com.micromata.webengineering.demo.post;

import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post, Long> {

    /**
     * finds all Posts ordered by their CreatedAt Timestamp
     *
     * @return the ordered List
     */
    Iterable<Post> findAllByOrderByCreatedAtDesc();
}
