package com.devconnect.controller;

import com.devconnect.entity.Post;
import com.devconnect.service.PostService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public Post createPost(@RequestBody String content) {
        return postService.createPost(content);
    }

    @GetMapping
    public List<Post> getAllPosts() {
        return postService.getAllPosts();
    }

    @GetMapping("/me")
    public List<Post> getMyPosts() {
        return postService.getMyPosts();
    }
}
