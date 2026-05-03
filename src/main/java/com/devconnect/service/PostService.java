package com.devconnect.service;

import com.devconnect.entity.Post;
import com.devconnect.repository.PostRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
public class PostService {

    private final PostRepository postRepository;
    private final AsyncService asyncService;

    public PostService(PostRepository postRepository, AsyncService asyncService) {
        this.postRepository = postRepository;
        this.asyncService = asyncService;
    }

    public Post createPost(String content) {
        String email = (String) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        Post post = Post.builder()
                .content(content)
                .authorEmail(email)
                .build();

        Post savedPost = postRepository.save(post);

        CompletableFuture<String> contentFuture = asyncService.processContent(content);
        CompletableFuture<String> notificationFuture = asyncService.sendNotification(email);
        CompletableFuture<String> analyticsFuture = asyncService.saveAnalytics(email);

        CompletableFuture.allOf(contentFuture, notificationFuture, analyticsFuture)
                .thenRun(() -> System.out.println("All async tasks completed"));

        return savedPost;
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public List<Post> getMyPosts() {
        String email = (String) SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getPrincipal();

        return postRepository.findByAuthorEmail(email);
    }
}
