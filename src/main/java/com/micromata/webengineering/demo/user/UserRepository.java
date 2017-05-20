package com.micromata.webengineering.demo.user;

import org.springframework.data.repository.Repository;

/**
 * Created by Jonas Scherbaum on 20.05.2017.
 */
public interface UserRepository extends Repository<User, Long> {

    /**
     * tries to find the User with the specified Email and Password
     *
     * @param email The Email of the User
     * @param password The Password of the User
     * @return The User with the specified Email and Password.
     */
    User findUserByEmailAndPassword(String email, String password);
}
