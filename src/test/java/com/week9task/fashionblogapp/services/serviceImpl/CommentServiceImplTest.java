package com.week9task.fashionblogapp.services.serviceImpl;

import com.week9task.fashionblogapp.dtos.CommentDto;
import com.week9task.fashionblogapp.dtos.PostDto;
import com.week9task.fashionblogapp.entities.Comment;
import com.week9task.fashionblogapp.entities.Post;
import com.week9task.fashionblogapp.entities.User;
import com.week9task.fashionblogapp.repositories.CommentRepository;
import com.week9task.fashionblogapp.repositories.PostRepository;
import com.week9task.fashionblogapp.repositories.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpSession;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CommentServiceImplTest {
    @InjectMocks
    private CommentServiceImpl commentService;

    @Mock
    private CommentRepository commentRepository;
    @Mock
    private ModelMapper modelMapper;
    @Mock private UserRepository userRepository;
    @Mock private PostRepository postRepository;

    @Mock
    private User user = User.builder()
            .roles("hdhd")
            .build();

    @Test
    void createNewComment() {
        CommentDto commentDto = new CommentDto();
        commentDto.setCommentBody("comment body here");
        commentDto.setCommentAuthor(user);

        User isi = User.builder()
                .email("gugiyugug")
                .roles("admin")
                .build();

        Post post = Post.builder()
                .postTitle("new post title")
                .body("body of new post")
                .build();

        PostDto postDto = new PostDto();
        postDto.setPostTitle("new title here");
        postDto.setBody("new post body");


        when(userRepository.findById(any())).thenReturn(Optional.of(isi));
        when(postRepository.findById(any())).thenReturn(Optional.of(post));
        when(modelMapper.map(any(), eq(CommentDto.class))).thenReturn(commentDto);
        ResponseEntity<CommentDto> response = commentService.createNewComment(1,1, commentDto);

        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getCommentBody()).isEqualTo("comment body here");


    }


    @Test
    void editComment() {
    }

    @Test
    void getAllComments() {
        List<Comment> comments = new ArrayList<>();

        Comment comment = Comment.builder()
                .commentBody("new post title")
                .commentAuthor(user)
                .build();

        Comment commentTwo = Comment.builder()
                .commentBody("new post title")
                .commentAuthor(user)
                .build();

        comments.add(comment);
        comments.add(commentTwo);
        CommentDto commentDto = new CommentDto();
        commentDto.setCommentBody("new title here");
        commentDto.setCommentAuthor(user);

        when(commentRepository.findAll()).thenReturn(comments);
//        when(modelMapper.map(any(), eq(CommentDto.class))).thenReturn(commentDto);
        ResponseEntity<List<CommentDto>> response = commentService.getAllComments();
        assertThat(response).isNotNull();
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().size()).isEqualTo(2);
    }

//    @Test
//    void shoulddeleteComment() {
//        Comment comment = Comment.builder()
//                .commentBody("new post title")
//                .commentAuthor(user)
//                .build();
//        when(commentRepository.existsById(any())).thenReturn(true);
//        ResponseEntity<String> response = commentService.deleteComment(1L);
//        assertThat(response).isNotNull();
//        assertThat(response.getBody()).isEqualTo("post deleted");
//    }

    @Test
    void createComment() {
    }
}