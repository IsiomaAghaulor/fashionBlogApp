package com.week9task.fashionblogapp.repositories;

import com.week9task.fashionblogapp.entities.Like;
import org.apache.tomcat.jni.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import lombok.Lombok;

@Repository
public interface LikeRepository extends JpaRepository<Like, Long>
{
    Like findLikeByPostIdAndUserId(Long postId, Long userId);
    @Query(nativeQuery = true,
            value = "SELECT COUNT(*) FROM likes l WHERE l.post_id =: postId")

    Long findLikesByPostId(@Param(value = "postId") Long postId);
}
