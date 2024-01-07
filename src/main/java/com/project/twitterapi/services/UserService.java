package com.project.twitterapi.services;

import com.project.twitterapi.domain.User;

import java.util.List;

public interface UserService {

    User registerUser(User user);

    List<User> getUsers(String username, String firstName, String lastName);
}
