package com.week9task.fashionblogapp.dtos;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class RegistrationDto {
    private String firstName;
    private String lastName;
    private String email;
    @Column(unique = true,nullable = false)
    private String userName;
    @Column(unique = true,nullable = false)
    private String password;



}
