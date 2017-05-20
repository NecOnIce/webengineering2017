package com.micromata.webengineering.demo.post;

import com.micromata.webengineering.demo.user.UserService;
import javafx.geometry.Pos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Handle all CRUD operations for posts.
 */
@Service
public class PostService {

    private final PostRepository repository;
    private final UserService userService;

    @Autowired
    public PostService(UserService userService, PostRepository repository) {
        this.userService = userService;
        this.repository = repository;
    }

    /**
     * Retrieve the list of all posts.
     *
     * @return post list
     */
    public Iterable<Post> getPosts() {
        return repository.findAllByOrderByCreatedAtDesc();
    }


    /**
     * Add a new post.
     *
     * @param post the post.
     */
    public void addPost(Post post) {
        post.setAuthor(userService.getCurrentUser());
        repository.save(post);
    }

    /**
     * Retrieve a single post.
     *
     * @param id post id
     * @return post with the id or null if no post is found
     */
    public Post getPost(Long id) {
        return repository.findOne(id);
    }

    /**
     * Remove a single post.
     *
     * @param id the post's id.
     */
    public void deletePost(Long id) {

        Post post = this.repository.findOne(id);
        if (!post.getAuthor().equals(userService.getCurrentUser())) {
            throw new IllegalStateException("User not allowed to delete post");
        }

        repository.delete(id);
    }
}
