package com.project.twitterapi.repository;

import com.project.twitterapi.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByUsernameOrFirstNameOrLastName(String username, String firstName, String lastName);
}
