package com.project.twitterapi.controller;

import com.project.twitterapi.domain.User;
import com.project.twitterapi.dto.UserRequestDto;
import com.project.twitterapi.dto.UserResponseDto;
import com.project.twitterapi.exception.UserIsNotLoggedInException;
import com.project.twitterapi.services.UserService;
import com.project.twitterapi.util.MessageText;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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
    public ResponseEntity<String> registerUser(@RequestBody UserRequestDto userRequestDto) {
        User user = this.modelMapper.map(userRequestDto, User.class);
        try {
            User registerUser = userService.registerUser(user);
            return ResponseEntity.status(HttpStatus.CREATED).body("User " + registerUser.getUsername() + " was registered");
        } catch (DataAccessException e) {
            return ResponseEntity.status(HttpStatus.CREATED).body(MessageText.ERROR_REGISTER_USER_MESSAGE);
        }
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<UserResponseDto>> searchUsers(@RequestParam(required = false) String username, @RequestParam(required = false) String firstName, @RequestParam(required = false) String lastName, @RequestHeader("loggedUsername") String loggedUsername) throws UserIsNotLoggedInException, UserIsNotLoggedInException {
        userService.checkLoggedUser(loggedUsername);
        List<UserResponseDto> users = this.modelMapper.map(userService.getUsersByUsernameOrFirstNameOrLastName(username, firstName, lastName), new TypeToken<List<UserResponseDto>>() {
        }.getType());
        if (users.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(users);
    }

    @GetMapping(value = "/all", produces = "application/json")
    public ResponseEntity<List<UserResponseDto>> getAllUsers() {
        List<UserResponseDto> users = this.modelMapper.map(userService.getUsers(), new TypeToken<List<UserResponseDto>>() {
        }.getType());

        return ResponseEntity.status(HttpStatus.OK).body(users);
    }


    //RequestParam
    @GetMapping(produces = "application/json")
    public ResponseEntity<List<User>> getUsersV2(@Valid UserRequestDto userRequestDto) {
        User user = this.modelMapper.map(userRequestDto, User.class);
        List<User> usersV2 = userService.getUsersV2(user);
        if (usersV2.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(usersV2);
        }
        return ResponseEntity.status(HttpStatus.OK).body(usersV2);
    }

}
