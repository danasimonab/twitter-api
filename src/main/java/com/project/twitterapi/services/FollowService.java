package com.project.twitterapi.services;

import com.project.twitterapi.domain.User;
import com.project.twitterapi.exception.UserAlreadyFollowedException;

import java.util.List;

public interface FollowService {

    void followUser(User loggedUser, User followedUser) throws UserAlreadyFollowedException;

    List<User> getFollowedUsers(User loggedUser);
}
