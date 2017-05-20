package com.micromata.webengineering.demo.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * This Service handles all processing steps with the User-Entity
 *
 * Created by Jonas Scherbaum on 20.05.2017.
 */
@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * get the User with the specified Email and Password.
     *
     * @param email The Email of the User
     * @param password The Password of the User
     * @return The User
     */
    public User getUser(String email, String password) {
        return userRepository.findUserByEmailAndPassword(email, password);
    }

    /**
     * Retrieve the currently active user or null, if no user is logged in.
     *
     * @return the current user.
     */
    public User getCurrentUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
