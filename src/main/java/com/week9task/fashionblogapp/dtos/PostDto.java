package com.week9task.fashionblogapp.dtos;

import com.week9task.fashionblogapp.entities.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "post model information")
public class PostDto {
    @ApiModelProperty(value = "blog post title")
    private String postTitle;

    @ApiModelProperty(value = "blog post body")
    private String body;
}
