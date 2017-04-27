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

    @RequestMapping(value = "/post/${id}")
    public Post getPost(@PathVariable("id") Long id) {
        return postService.getPost(id);
    }
}
