package com.project.twitterapi.controller;

import com.project.twitterapi.domain.User;
import com.project.twitterapi.dto.UserResponseDto;
import com.project.twitterapi.exception.UserAlreadyFollowedException;
import com.project.twitterapi.exception.UsernameNotFoundException;
import com.project.twitterapi.services.FollowService;
import com.project.twitterapi.services.UserService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/followers")
public class FollowController {

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private FollowService followService;
    @Autowired
    private UserService userService;

    @PostMapping("/{username}")
    public ResponseEntity<String> follow(@PathVariable String username, @RequestHeader("loggedUsername") String loggedUsername) throws UsernameNotFoundException, UserAlreadyFollowedException {
        User loggedUser = userService.getUserByUsername(loggedUsername);
        User userToFollow = userService.getUserByUsername(username);
        followService.followUser(loggedUser, userToFollow);

        return ResponseEntity.status(HttpStatus.CREATED).body("User " + loggedUsername + "started following " + username);
    }

    @GetMapping()
    public ResponseEntity<List<UserResponseDto>> getFollowedUsers(@RequestHeader("loggedUsername") String loggedUsername) throws UsernameNotFoundException {
        User loggedUser = userService.getUserByUsername(loggedUsername);
        List<UserResponseDto> users = this.modelMapper.map(followService.getFollowedUsers(loggedUser), new TypeToken<List<UserResponseDto>>() {
        }.getType());

        return ResponseEntity.status(HttpStatus.OK).body(users);

    }


}
