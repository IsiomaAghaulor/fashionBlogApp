package com.week9task.fashionblogapp.services.serviceImpl;

import com.week9task.fashionblogapp.dtos.PostDto;
import com.week9task.fashionblogapp.entities.Like;
import com.week9task.fashionblogapp.entities.Post;
import com.week9task.fashionblogapp.entities.User;
import com.week9task.fashionblogapp.exceptions.CustomRequestException;
import com.week9task.fashionblogapp.repositories.LikeRepository;
import com.week9task.fashionblogapp.repositories.PostRepository;
import com.week9task.fashionblogapp.services.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service

public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final LikeRepository likeRepository;
    private final ModelMapper modelMapper;
    private final HttpSession session;

    @Autowired
    public PostServiceImpl(PostRepository postRepository, LikeRepository likeRepository, ModelMapper modelMapper, HttpSession session) {
        this.postRepository = postRepository;
        this.likeRepository = likeRepository;
        this.modelMapper = modelMapper;
        this.session = session;
    }

//    @Override
//    public Post fetchSinglePost(String postTitle) {
//        Post singlePost = postRepository.findByPostTitle(postTitle);
//        //post.setLikeCount(postLikes);
//        return Post;
//
//        // User byUserName = userRepository.findByUserName(registrationDto.getUserName());
//    }

//    public Post fetchSinglePost(Long postId) {
//        Long postLikes = likeRepository.findLikesByPostId(postId);
//        Post post = postRepository.getById(postId);
//        //post.setLikeCount(postLikes);
//        return post;
//
//        // User byUserName = userRepository.findByUserName(registrationDto.getUserName());
//    }


    @Override
    public ResponseEntity<List<PostDto>> fetchAllPosts() {
        List<Post> allPosts = postRepository.findAll();
        List<PostDto> allPostDto = allPosts.stream()
                .map(post -> new ModelMapper().map(post, PostDto.class))
                .collect(Collectors.toList());

//        List<PostDto> postDtos = new ArrayList<>();
//        for (Post each: allPosts) {
//          ModelMapper modelMapper = new ModelMapper() ;
//            PostDto map = modelMapper.map(each, PostDto.class);
//            postDtos.add(map);
//        }

        return new ResponseEntity<>(allPostDto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<PostDto> createPost(PostDto postDto) {
        User user = (User) session.getAttribute("user");
        if (user.getRoles().equalsIgnoreCase("admin")) {

            Post byTitle = postRepository.findByPostTitle(postDto.getPostTitle());

            if (byTitle == null) {
                Post newPost = new Post();
                newPost.setPostTitle(postDto.getPostTitle());
                newPost.setBody(postDto.getBody());
                newPost.setDateCreated(Date.from(Instant.now()));
                newPost.setPostCreator(user);

                Post savePost = postRepository.save(newPost);
                PostDto postDto1 = modelMapper.map(savePost, PostDto.class);
                return new ResponseEntity<>(postDto1, HttpStatus.CREATED);
            }

            throw new CustomRequestException("This post already exists.");
        }
        else throw new CustomRequestException("Only admins can create a post!");

    }

    @Override
    public ResponseEntity<PostDto> fetchSinglePost(String postTitle) {
        Post post = postRepository.findByPostTitle(postTitle);
        PostDto postDto = modelMapper.map(post, PostDto.class);

        return new ResponseEntity<>(postDto, HttpStatus.FOUND);

    }


    @Override
    public void likePost(final Long postId, final Long userId) {
        Like like= Like.builder()
                .postId(postId)
                .userId(userId).build();
       likeRepository.save(like);
    }

    @Override
    public ResponseEntity<String> deletePost(Long id) {
        boolean ifPostExists = postRepository.existsById(id);
        if(!ifPostExists){
            throw new CustomRequestException("This post does not exist");
        }
        postRepository.deleteById(id);

    return new ResponseEntity<>("post deleted",HttpStatus.OK);
    }

    @Override
    public void dislikePost(Long postId, Long userId) {
        Like like = likeRepository.findLikeByPostIdAndUserId(postId,userId);
        likeRepository.delete(like);
    }
}
