package com.micromata.webengineering.demo.post;

import com.micromata.webengineering.demo.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * HTTP endpoint for a post-related HTTP requests.
 */
@RestController
public class PostController {

    @Autowired
    private PostService postService;

    @RequestMapping(value = "/post", method = RequestMethod.GET)
    public Iterable<Post> getPostList() {
        return postService.getPosts();
    }

    @RequestMapping(value = "/post", method = RequestMethod.POST)
    public URL addPost(@RequestBody Post post, HttpServletRequest httpServletRequest) {
        String contextPath = httpServletRequest.getContextPath();
        postService.addPost(post);
        String getPostPath = contextPath.concat("/post/").concat(post.getId().toString());
        return new URL(getPostPath);
    }

    @RequestMapping(value = "/post/{id}", method = RequestMethod.GET)
    public Post getPost(@PathVariable Long id) {
        return postService.getPost(id);
    }

    @RequestMapping(value = "/post/{id}", method = RequestMethod.DELETE)
    public void deletePost(@PathVariable Long id) {
        postService.deletePost(id);
    }
}
