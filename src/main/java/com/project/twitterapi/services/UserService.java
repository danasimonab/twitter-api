package com.project.twitterapi.services;

import com.project.twitterapi.domain.User;
import com.project.twitterapi.exception.UserIsNotLoggedInException;
import com.project.twitterapi.exception.UsernameNotFoundException;

import java.util.List;

public interface UserService {

    User registerUser(User user);

    List<User> getUsers();

    List<User> getUsersByUsernameOrFirstNameOrLastName(String username, String firstName, String lastName);

    User getUserByUsername(String username) throws UsernameNotFoundException;

    void checkLoggedUser(String username) throws UserIsNotLoggedInException;

    public List<User> getUsersV2(User user);
}
