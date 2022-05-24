package com.week9task.fashionblogapp.services.serviceImpl;

import com.week9task.fashionblogapp.dtos.CommentDto;
import com.week9task.fashionblogapp.dtos.PostDto;
import com.week9task.fashionblogapp.entities.Comment;
import com.week9task.fashionblogapp.entities.Post;
import com.week9task.fashionblogapp.entities.User;
import com.week9task.fashionblogapp.exceptions.CustomRequestException;
import com.week9task.fashionblogapp.repositories.CommentRepository;
import com.week9task.fashionblogapp.repositories.PostRepository;
import com.week9task.fashionblogapp.repositories.UserRepository;
import com.week9task.fashionblogapp.services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {
    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final ModelMapper modelMapper;

    public CommentServiceImpl(UserRepository userRepository, PostRepository postRepository, CommentRepository commentRepository,ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.postRepository = postRepository;
        this.commentRepository = commentRepository;
        this.modelMapper = modelMapper;

    }

    @Override
    public ResponseEntity<CommentDto> createNewComment(long userId, long postId, CommentDto commentDto) {
        Comment comment = new Comment();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new CustomRequestException("User not found"));
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new CustomRequestException("Post not found"));
        comment.setCommentBody(commentDto.getCommentBody());
        comment.setDateCommented(Date.valueOf(LocalDate.now()));
        comment.setTimeCommented(Time.valueOf(LocalTime.now()));
        comment.setPost(post);
        comment.setCommentAuthor(user);
        Comment saveComment = commentRepository.save(comment);
        CommentDto commentDto1 = modelMapper.map(saveComment, CommentDto.class);

        return new ResponseEntity<>(commentDto1, HttpStatus.CREATED);

    }

    @Override
    public ResponseEntity<Comment> editComment(Long userId, PostDto postDto, Long CommentId) {
        Comment comment = commentRepository.findById(CommentId)
                .orElseThrow(() -> new CustomRequestException("Comment not found"));
        if (comment.getCommentAuthor().getUserId() == userId) {
            comment.setCommentBody(postDto.getBody());
            commentRepository.save(comment);
        } else {
            throw new CustomRequestException("comment edit failed!");
        }
        return null;
    }

    @Override
    public ResponseEntity <List<CommentDto>> getAllComments() {
        List<Comment> allComments = commentRepository.findAll();
        List<CommentDto> allCommentDto = allComments.stream()
                .map(comment -> new ModelMapper().map(comment, CommentDto.class))
                .collect(Collectors.toList());


        return new ResponseEntity<>(allCommentDto, HttpStatus.OK);


    }


    @Override
    public ResponseEntity<String> deleteComment(Long id) {
          boolean ifCommentExists = commentRepository.existsById(id);
          if(!ifCommentExists){
                throw new CustomRequestException("This comment does not exist");
        }
           commentRepository.deleteById(id);

         return new ResponseEntity<>("post deleted", HttpStatus.OK);
    }

    @Override
    public String createComment(CommentDto commentDto) {
        return null;
    }

}
