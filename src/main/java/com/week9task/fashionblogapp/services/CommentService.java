package com.week9task.fashionblogapp.services;

import com.week9task.fashionblogapp.dtos.CommentDto;
import com.week9task.fashionblogapp.dtos.PostDto;
import com.week9task.fashionblogapp.entities.Comment;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface CommentService {
    ResponseEntity<CommentDto>  createNewComment(long userId, long postId, CommentDto commentDto);
    ResponseEntity<Comment> editComment(Long userId,PostDto postDto,Long CommentId);
    ResponseEntity<List<CommentDto>> getAllComments();
    ResponseEntity<String> deleteComment(Long id);

    String createComment(CommentDto commentDto);

}


