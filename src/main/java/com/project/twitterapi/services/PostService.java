package com.project.twitterapi.services;

import com.project.twitterapi.domain.Post;
import com.project.twitterapi.domain.User;
import com.project.twitterapi.exception.PostNotFoundException;

import java.util.HashMap;
import java.util.List;

public interface PostService {

    void postMessage(Post post, User user);

    HashMap<String, List<Post>> getFeed(User user);

    List<Post> getOwnPosts(User user, Long timestamp);

    Post getPost(Long postId) throws PostNotFoundException;

}
