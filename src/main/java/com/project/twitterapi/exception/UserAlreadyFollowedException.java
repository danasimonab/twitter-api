package com.project.twitterapi.exception;

public class UserAlreadyFollowedException extends Exception {
    public UserAlreadyFollowedException(String message) {
        super(message);
    }
}
