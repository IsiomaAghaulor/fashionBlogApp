package com.week9task.fashionblogapp.services.serviceImpl;

import com.week9task.fashionblogapp.dtos.PostDto;
import com.week9task.fashionblogapp.entities.Post;
import com.week9task.fashionblogapp.entities.User;
import com.week9task.fashionblogapp.exceptions.CustomRequestException;
import com.week9task.fashionblogapp.repositories.PostRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpSession;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PostServiceImplTest {
    @InjectMocks
    private PostServiceImpl postService;
    @Mock
    private HttpSession session;

    @Mock
    private PostRepository postRepository;
    @Mock
    private ModelMapper modelMapper;


    @Mock
    private User user = User.builder()
            .roles("hdhd")
                .build();


    @Test
    void shouldFetchAllPostsAndReturnTheirDtos() {
        List<Post> posts = new ArrayList<>();
        Post post = Post.builder()
                .postTitle("new post title")
                .body("body of new post")
                .build();

        Post post2 = Post.builder()
                .postTitle("new post title")
                .body("body of new post")
                .build();

        posts.add(post);
        posts.add(post2);

        when(postRepository.findAll()).thenReturn(posts);
        ResponseEntity<List<PostDto>> listResponseEntity = postService.fetchAllPosts();
        assertThat(listResponseEntity.getBody()).isNotNull();
        assertThat(listResponseEntity.getBody().size()).isEqualTo(2);
        assertThat(listResponseEntity.getBody().get(0)).isInstanceOf(PostDto.class);
        assertThat(listResponseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    void shouldCreatePost() {
        PostDto postDto = new PostDto();
        postDto.setPostTitle("new title here");
        postDto.setBody("new post body");

        User isi = User.builder()
                .email("gugiyugug")
                .roles("admin")
                .build();



        when(session.getAttribute(any())).thenReturn(isi);
        when(postRepository.findByPostTitle(postDto.getPostTitle())).thenReturn(null);
        when(modelMapper.map(any(), eq(PostDto.class))).thenReturn(postDto);
        ResponseEntity<PostDto> response = postService.createPost(postDto);

        assertThat(response.getBody().getPostTitle()).isEqualTo("new title here");

    }
    @Test
    void shouldThrowExceptionIfPostTitleExists(){
        User isi = User.builder()
                .email("gugiyugug")
                .roles("admin")
                .build();

        PostDto postDto = new PostDto();
        postDto.setPostTitle("new title here");
        postDto.setBody("new post body");

        Post post2 = Post.builder()
                .postTitle("new post title")
                .body("body of new post")
                .build();
        when((User)session.getAttribute("user")).thenReturn(isi);
        when(postRepository.findByPostTitle(postDto.getPostTitle())).thenReturn(post2);
        assertThatThrownBy(()-> postService.createPost(postDto))
                .isInstanceOf(CustomRequestException.class)
                .hasMessage("This post already exists.");


    }

    @Test
    void fetchSinglePost() {
        Post post = Post.builder()
                .postTitle("new post title")
                .body("body of new post")
                .build();

        PostDto postDto = new PostDto();
        postDto.setPostTitle("new title here");
        postDto.setBody("new post body");

        when(postRepository.findByPostTitle(any())).thenReturn(post);
        when(modelMapper.map(any(), eq(PostDto.class))).thenReturn(postDto);
        ResponseEntity<PostDto> response = postService.fetchSinglePost("new post title");
        assertThat(response).isNotNull();
        assertThat(response.getBody().getPostTitle()).isEqualTo("new title here");

    }

    @Test
    void likePost() {

    }

    @Test
    void shouldThrowExceptionWhenPostDoesntExist() {
        Post post = Post.builder()
                .postTitle("new post title")
                .body("body of new post")
                .build();
        when(postRepository.existsById(any())).thenReturn(false);
        assertThatThrownBy(()-> postService.deletePost(any()))
                .isInstanceOf(CustomRequestException.class)
                .hasMessage("This post does not exist");


    }

    @Test
    void shouldDeletePost() {
        Post post = Post.builder()
                .postTitle("new post title")
                .body("body of new post")
                .build();
        when(postRepository.existsById(any())).thenReturn(true);
        ResponseEntity<String> response = postService.deletePost(1L);
        assertThat(response).isNotNull();
        assertThat(response.getBody()).isEqualTo("post deleted");

    }

    @Test
    void dislikePost() {
    }
}