package com.project.twitterapi.controller;

import com.project.twitterapi.domain.User;
import com.project.twitterapi.dto.UserRequestDto;
import com.project.twitterapi.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/users")
public class UserController {
    UserService userService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    public ResponseEntity<User> registerUser(@RequestBody UserRequestDto userRequestDto){
        User user = this.modelMapper.map(userRequestDto, User.class);
        return new ResponseEntity<>(userService.registerUser(user), HttpStatus.CREATED);
    }


}
