package com.project.twitterapi.controller;

import com.project.twitterapi.domain.User;
import com.project.twitterapi.dto.UserRequestDto;
import com.project.twitterapi.dto.UserResponseDto;
import com.project.twitterapi.services.UserService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping
    public ResponseEntity<User> registerUser(@RequestBody UserRequestDto userRequestDto){
        User user = this.modelMapper.map(userRequestDto, User.class);
        return new ResponseEntity<>(userService.registerUser(user), HttpStatus.CREATED);
    }

   @GetMapping(produces = "application/json")
    public ResponseEntity<List<UserResponseDto>> getUsers(@RequestParam(required = false) String username, @RequestParam(required = false) String firstName, @RequestParam(required = false) String lastName){
        List<UserResponseDto> users = this.modelMapper.map(userService.getUsers(username, firstName, lastName), new TypeToken<UserResponseDto>(){

        }.getType());
        if(users.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(users);
        }
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }


}
