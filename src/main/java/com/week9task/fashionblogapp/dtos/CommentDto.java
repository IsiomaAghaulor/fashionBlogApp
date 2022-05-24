package com.week9task.fashionblogapp.dtos;

import com.week9task.fashionblogapp.entities.User;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

@Api(value = "comment model information")
@Data
public class CommentDto {
    @ApiModelProperty(value = "comment body")
    private String commentBody;

    @ApiModelProperty(value = "comment body")
    private User commentAuthor;
}
