package com.project.twitterapi.controller;


import com.project.twitterapi.domain.Post;
import com.project.twitterapi.domain.User;
import com.project.twitterapi.dto.PostRequestDto;
import com.project.twitterapi.dto.PostResponseDto;
import com.project.twitterapi.exception.UserIsNotLoggedInException;
import com.project.twitterapi.exception.UsernameNotFoundException;
import com.project.twitterapi.services.PostService;
import com.project.twitterapi.services.UserService;
import com.project.twitterapi.util.MessageText;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/posts")
public class PostController {

    @Autowired
    UserService userService;
    @Autowired
    PostService postService;
    @Autowired
    ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<String> postMessage(@RequestBody PostRequestDto postRequestDto, @RequestHeader("loggedUsername") String loggedUsername) throws UserIsNotLoggedInException, UsernameNotFoundException {
        userService.checkLoggedUser(loggedUsername);
        User ownerPost = userService.getUserByUsername(loggedUsername);
        Post post = this.modelMapper.map(postRequestDto, Post.class);
        postService.postMessage(post, ownerPost);
        return ResponseEntity.status(HttpStatus.CREATED).body(MessageText.NEW_POST_MESSAGE);
    }

    public ResponseEntity<List<PostResponseDto>> getPosts(@RequestParam(required = false) Long timestamp, @RequestHeader("loggedUsername") String loggedUsername) throws UserIsNotLoggedInException, UsernameNotFoundException {
        userService.checkLoggedUser(loggedUsername);
        User loggedUser = userService.getUserByUsername(loggedUsername);

        setModelMapping();
        List<PostResponseDto> posts = this.modelMapper.map(postService.getOwnPosts(loggedUser, timestamp), new TypeToken<List<PostResponseDto>>() {
        }.getType());
        return ResponseEntity.status(HttpStatus.OK).body(posts);
    }

    private void setModelMapping() {
        modelMapper.typeMap(Post.class, PostResponseDto.class)
                .addMappings(mapper -> mapper.map(post -> post.getUser().getUsername(), PostResponseDto::setUsername));

    }


}
