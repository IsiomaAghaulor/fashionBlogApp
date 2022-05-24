package com.week9task.fashionblogapp.services;

import com.week9task.fashionblogapp.dtos.PostDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PostService {
   ResponseEntity<PostDto> createPost(PostDto postDto);

   ResponseEntity<PostDto> fetchSinglePost(String postTitle);

   ResponseEntity<List<PostDto>> fetchAllPosts();

   void likePost(Long postId, Long userId);

   ResponseEntity<String> deletePost(Long id);

   void dislikePost(Long postId, Long userId);
}
