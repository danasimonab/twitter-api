package com.project.twitterapi.services;

import com.project.twitterapi.domain.Post;
import com.project.twitterapi.domain.User;
import com.project.twitterapi.exception.PostNotFoundException;
import com.project.twitterapi.repository.PostRepository;
import com.project.twitterapi.util.MessageText;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    PostRepository postRepository;

    @Autowired
    FollowService followService;

    @Override
    public void postMessage(Post post, User user) {
        post.setUser(user);
        post.setTimestamp(new Date().getTime());
        postRepository.save(post);
    }

    @Override
    public HashMap<String, List<Post>> getFeed(User user) {
        HashMap<String, List<Post>> allFeed = new HashMap<>();
        List<User> followedUsers = followService.getFollowedUsers(user);
        allFeed.put(user.getUsername(), postRepository.findByUser(user));
        followedUsers.forEach(follwedUser -> allFeed.put(follwedUser.getUsername(), postRepository.findByUser(follwedUser)));

        return allFeed;
    }

    @Override
    public List<Post> getOwnPosts(User user, Long timestamp) {
        if (timestamp != null) {
            return postRepository.findByTimestampGreaterThan(timestamp);
        }
        return postRepository.findByUser(user);
    }

    @Override
    public Post getPost(Long postId) throws PostNotFoundException {
        Optional<Post> post = postRepository.findById(postId);
        if (post.isEmpty()) {
            throw new PostNotFoundException(MessageText.NOT_FOUND_POST);
        }
        return post.get();
    }
}
