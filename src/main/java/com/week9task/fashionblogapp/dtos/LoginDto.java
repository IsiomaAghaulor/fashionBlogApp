package com.week9task.fashionblogapp.dtos;

import com.week9task.fashionblogapp.entities.Role;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "login information")
public class LoginDto {

    @ApiModelProperty(value = "user name")
    private String userName;

    @ApiModelProperty(value = "user password")
    private String password;
   // private List<Role> roles;



}
