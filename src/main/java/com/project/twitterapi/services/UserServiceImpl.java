package com.project.twitterapi.services;

import com.project.twitterapi.domain.User;
import com.project.twitterapi.exception.UserIsNotLoggedInException;
import com.project.twitterapi.exception.UsernameNotFoundException;
import com.project.twitterapi.repository.UserRepository;
import com.project.twitterapi.util.MessageText;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public User registerUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }


    @Override
    public List<User> getUsersByUsernameOrFirstNameOrLastName(String username, String firstName, String lastName) {
        return userRepository.findByUsernameOrFirstNameOrLastName(username, firstName, lastName);
    }

    @Override
    public User getUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User with " + username + " doesn't exists");
        }
        return user.get();
    }

    @Override
    public void checkLoggedUser(String username) throws UserIsNotLoggedInException {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isEmpty()) {
            throw new UserIsNotLoggedInException(MessageText.USER_NOT_LOGGED_IN);
        }
    }

    @Override
    public List<User> getUsersV2(User user) {
        List<User> users;
        if (user.getUsername() != null || user.getFirstName() != null || user.getLastName() != null) {
            users = userRepository.findByUsernameOrFirstNameOrLastName(user.getUsername(), user.getFirstName(), user.getLastName());
        } else users = userRepository.findAll();

        return users;
    }


}
