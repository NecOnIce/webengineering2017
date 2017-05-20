package com.micromata.webengineering.demo.post;

import com.micromata.webengineering.demo.ServerAddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * HTTP endpoint for a post-related HTTP requests.
 */
@RestController
public class PostController {

    @Autowired
    public PostController(PostService postService, ServerAddressService serverAddressService) {
        this.postService = postService;
        this.serverAddressService = serverAddressService;
    }

    private static class CreatedPost {
        private String url;
    }

    private final PostService postService;
    private final ServerAddressService serverAddressService;

    @RequestMapping(value = "/api/post", method = RequestMethod.GET)
    public Iterable<Post> getPostList() {
        return postService.getPosts();
    }

    @RequestMapping(value = "/api/post", method = RequestMethod.POST)
    public ResponseEntity<Object> addPost(@RequestBody Post post, HttpServletRequest httpServletRequest) {

        if (post.getTitle() != null && post.getTitle().length() > Post.POST_TITLE_LENGTH) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        postService.addPost(post);
        CreatedPost createdPost = new CreatedPost();
        createdPost.url = serverAddressService.getServerURL() + "/api/post/" + post.getId();
        return ResponseEntity.ok(createdPost);
    }

    @RequestMapping(value = "/api/post/{id}", method = RequestMethod.GET)
    public Post getPost(@PathVariable Long id) {
        return postService.getPost(id);
    }

    @RequestMapping(value = "/api/post/{id}", method = RequestMethod.DELETE)
    public void deletePost(@PathVariable Long id) {
        postService.deletePost(id);
    }
}
