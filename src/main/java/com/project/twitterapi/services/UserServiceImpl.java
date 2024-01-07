package com.project.twitterapi.services;

import com.project.twitterapi.domain.User;
import com.project.twitterapi.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;


    @Override
    public User registerUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getUsers(String username, String firstName, String lastName) {
        List<User> users;
        if(username != null || firstName != null || lastName != null ){
            users = userRepository.findByUsernameOrFirstNameOrLastName(username, firstName, lastName);
        } else users = userRepository.findAll();

        return users;
    }

    @Override
    public User followUser(User user) {
        return null;
    }
}
