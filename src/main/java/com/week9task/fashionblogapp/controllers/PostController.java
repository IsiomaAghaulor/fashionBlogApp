package com.week9task.fashionblogapp.controllers;

import com.week9task.fashionblogapp.dtos.PostDto;
import com.week9task.fashionblogapp.entities.Post;
import com.week9task.fashionblogapp.services.PostService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController

public class PostController {
    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/posts")
    public ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto) {
        return postService.createPost(postDto);
    }

    @GetMapping("/posts")
    public ResponseEntity<List<PostDto>> fetchAllPosts(){
        return postService.fetchAllPosts();
    }

    @DeleteMapping("/posts/{postId}")
    public ResponseEntity<String> deletePost(@PathVariable Long postId){
        return postService.deletePost(postId);
    }
}


