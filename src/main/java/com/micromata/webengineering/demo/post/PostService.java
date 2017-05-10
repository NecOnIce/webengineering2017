package com.micromata.webengineering.demo.post;

import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * Handle all CRUD operations for posts.
 */
@Service
public class PostService {

    private List<Post> posts = new LinkedList<>();

    /**
     * Retrieve the list of all posts.
     *
     * @return post list
     */
    public List<Post> getPosts() {
        return posts;
    }


    /**
     * Add a new post.
     *
     * @param post the post.
     */
    public void addPost(Post post) {
        if (posts.add(post)) {
            post.setId(posts.indexOf(post));
        }
    }

    /**
     * get the single Post with the given ID
     *
     * @param id The ID of the Post
     * @return The requested Post
     */
    public Post getPost(int id) {
        return posts.get(id);
    }

    /**
     * delete the Post with the given ID
     *
     * @param id The ID of the Post to delete
     * @return the deleted Post
     */
    public Post deletePost(int id) {
        return posts.remove(id);
    }
}
