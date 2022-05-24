package com.week9task.fashionblogapp.repositories;

import com.week9task.fashionblogapp.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
 User findByUserName(String userName);
 User findByUserNameAndPassword(String userName, String password);
}
