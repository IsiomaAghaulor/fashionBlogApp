package com.week9task.fashionblogapp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;


@Entity
@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue
    private long userId;
    private String firstName;
    private String lastName;
    private String email;
    @Column(unique = true,nullable = false)
    private String userName;
    @JsonIgnore
    private String password;
    //@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private String roles;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Post> posts;



}


