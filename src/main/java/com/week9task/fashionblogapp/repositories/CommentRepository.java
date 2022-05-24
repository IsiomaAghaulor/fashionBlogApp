package com.week9task.fashionblogapp.repositories;

import com.week9task.fashionblogapp.entities.Comment;
import com.week9task.fashionblogapp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
