package com.project.twitterapi.services;

import com.project.twitterapi.domain.Follow;
import com.project.twitterapi.domain.User;
import com.project.twitterapi.exception.UserAlreadyFollowedException;
import com.project.twitterapi.repository.FollowRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class FollowServiceImpl implements FollowService {

    @Autowired
    private FollowRepository followRepository;

    @Override
    public void followUser(User loggedUser, User followedUser) throws UserAlreadyFollowedException {
        checkIfUserIsFollowedByLoggedUser(loggedUser, followedUser);
        Follow follow = new Follow();
        follow.setUser(loggedUser);
        follow.setFollowing(followedUser);
        follow.setTimestamp(new Date().getTime());

        followRepository.save(follow);
    }

    @Override
    public List<User> getFollowedUsers(User loggedUser) {
        return followRepository.findByUser(loggedUser).stream().map(Follow::getFollowing).toList();

    }

    private void checkIfUserIsFollowedByLoggedUser(User loggedUser, User followedUser) throws UserAlreadyFollowedException {
        Optional<Follow> follow = followRepository.findByUserAndFollowing(loggedUser, followedUser);
        if (follow.isPresent()) {
            throw new UserAlreadyFollowedException("User " + followedUser.getUsername() + " is already followed by " + loggedUser.getUsername());
        }
    }
}
