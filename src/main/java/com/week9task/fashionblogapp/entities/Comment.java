package com.week9task.fashionblogapp.entities;

import lombok.*;

import javax.persistence.*;
import java.sql.Time;
import java.util.Date;

@Entity
@Data
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    @Id
    @GeneratedValue
    private Long commentId;
    private String commentBody;
    private Date dateCommented;
    private Time timeCommented;
    private Long likeCount;
    @ManyToOne
    private Post post;
    @ManyToOne
    private User commentAuthor;


}

