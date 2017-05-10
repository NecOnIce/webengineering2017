package com.micromata.webengineering.demo.post;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * HTTP endpoint for a post-related HTTP requests.
 */
@RestController
public class PostController {

    @Autowired
    private PostService postService;

    @RequestMapping("/post")
    public List<Post> getPostList() {
        return postService.getPosts();
    }

    @RequestMapping(value = "/post/add", method = RequestMethod.POST)
    public void addPost(@RequestParam("title") String title) {
        postService.addPost(new Post(title));
    }

    @RequestMapping(value = "/post/{id}", method = RequestMethod.GET)
    public Post getPost(@PathVariable("id") int id) {
        return postService.getPost(id);
    }

    @RequestMapping(value = "/post/{id}", method = RequestMethod.DELETE)
    public Post deletePost(@PathVariable("id") int id) {
        return postService.deletePost(id);
    }
}
