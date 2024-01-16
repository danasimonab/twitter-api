package com.project.twitterapi.exception;

import com.project.twitterapi.util.MessageText;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class CustomExceptionHandler {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(UsernameNotFoundException.class)
    public Map<String, String> handleUsernameNotFoundException(UsernameNotFoundException usernameNotFoundException) {
        Map<String, String> exceptionDetails = new HashMap<>();

        exceptionDetails.put(MessageText.ERROR_MESSAGE, usernameNotFoundException.getMessage());
        return exceptionDetails;
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(UserIsNotLoggedInException.class)
    public Map<String, String> handleUserInNotLoggedInException(UserIsNotLoggedInException userIsNotLoggedInException) {
        Map<String, String> exceptionDetails = new HashMap<>();

        exceptionDetails.put(MessageText.ERROR_MESSAGE, userIsNotLoggedInException.getMessage());
        return exceptionDetails;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UserAlreadyFollowedException.class)
    public Map<String, String> handleUserAlreadyFollowedException(UserAlreadyFollowedException userAlreadyFollowedException) {
        Map<String, String> exceptionDetails = new HashMap<>();

        exceptionDetails.put(MessageText.ERROR_MESSAGE, userAlreadyFollowedException.getMessage());
        return exceptionDetails;
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(PostNotFoundException.class)
    public Map<String, String> handlePostNotFoundException(PostNotFoundException postNotFoundException) {
        Map<String, String> exceptionDetails = new HashMap<>();

        exceptionDetails.put(MessageText.ERROR_MESSAGE, postNotFoundException.getMessage());
        return exceptionDetails;
    }


}
