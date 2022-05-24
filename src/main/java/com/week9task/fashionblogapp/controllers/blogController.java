package com.week9task.fashionblogapp.controllers;

import com.week9task.fashionblogapp.dtos.LoginDto;
import com.week9task.fashionblogapp.dtos.PostDto;
import com.week9task.fashionblogapp.dtos.RegistrationDto;
import com.week9task.fashionblogapp.entities.Post;
import com.week9task.fashionblogapp.entities.User;
import com.week9task.fashionblogapp.services.PostService;
import com.week9task.fashionblogapp.services.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Collections;
import java.util.List;

@RestController
@AllArgsConstructor
public class blogController {
    private final UserService userService;
   // private final PostService postService;

    @PostMapping("/users")
    public ResponseEntity<String> createUser(@RequestBody RegistrationDto registrationDto) {
        return ResponseEntity.ok(userService.createUser(registrationDto));
    }

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody LoginDto loginDto, HttpSession session) {
        ResponseEntity<User> response = userService.login(loginDto);
        session.setAttribute("user", response.getBody());
        return response;
    }

    @GetMapping("/users")
    public List<User> getUser() {
        return Collections.emptyList();
    }





//    @PostMapping("/users/{postId}{userId}/likes")
//    public ResponseEntity<?> likePost(@PathVariable(name = "postId") Long postId,
//                                      @PathVariable(name = "userId") Long userId) {
//        postService.likePost(postId,userId);
//        return ResponseEntity.ok().build();
//    }
//
//    @DeleteMapping("/users/{postId}{userId}/likes")
//    public ResponseEntity<?> dislikePost(@PathVariable(name = "postId") Long postId,
//                                      @PathVariable(name = "userId") Long userId) {
//        postService.likePost(postId,userId);
//        return ResponseEntity.ok().build();
//    }

//    @GetMapping("/posts")
//    public List<Post> getPost(){
//        return postService.fetchAllPosts();
//    }

//    @GetMapping("/users/{postId}{userId}/likes")
//    public ResponseEntity<Post> getPost(@PathVariable(name = "postId") Long postId ){
//        return ResponseEntity.ok(postService.fetchSinglePost(postId));
//    }

//    @GetMapping("/posts/{postId}{userId}/likes")
//    public ResponseEntity<Post> getPost(@PathVariable(name = "postTitle") String postTitle ){
//        return ResponseEntity.ok(postService.fetchSinglePost(postTitle));
//    }


}
