package com.project.twitterapi.exception;

public class UserIsNotLoggedInException extends Exception {
    public UserIsNotLoggedInException(String message) {
        super(message);
    }
}
