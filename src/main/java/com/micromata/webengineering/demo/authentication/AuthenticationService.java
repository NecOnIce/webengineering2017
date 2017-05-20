package com.micromata.webengineering.demo.authentication;

import com.micromata.webengineering.demo.user.User;
import com.micromata.webengineering.demo.user.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

/**
 * This Service handles all Authentication processes like generating and parsing the JWT tokens
 * as well as setting the User object for the current Request.
 *
 * Created by Jonas Scherbaum on 20.05.2017.
 */
@Service
public class AuthenticationService {

    @Autowired
    public AuthenticationService(UserService userService) {
        this.userService = userService;
    }

    /**
     * Return object containing a valid user and his corresponding JWT token.
     */
    public static class UserToken {
        public User user;
        public String token;
    }


    private static final String secret = "This is not a secure method to store this secret!";

    private final UserService userService;

    /**
     * Logs in the user with the specified email and password
     *
     * @param email The email of the User
     * @param password The password of the user
     * @return the UserToken to identify the user by subsequent requests.
     */
    public UserToken login(String email, String password) {

        User user = this.userService.getUser(email, password);
        if (user == null) {
            return null;
        }

        String token = Jwts.builder()
                .setSubject(email)
                .setId(user.getId().toString())
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();

        UserToken userToken = new UserToken();
        userToken.user = user;
        userToken.token = token;
        return userToken;
    }

    /**
     * Validate that a token is valid and returns its body.
     *
     * Throws a SignatureException if the token is not valid.
     * @param jwtToken JWT token
     * @return JWT body
     */
    public Object parseToken(String jwtToken) {
        return Jwts.parser()
                .setSigningKey(secret)
                .parse(jwtToken)
                .getBody();
    }

    /**
     * Set a user for the current request.
     *
     * @param id user id
     * @param email user email
     */
    public void setUser(Long id, String email) {
        User user = new User();
        user.setId(id);
        user.setEmail(email);
        UsernamePasswordAuthenticationToken secAuth = new UsernamePasswordAuthenticationToken(user, null);
        SecurityContextHolder.getContext().setAuthentication(secAuth);
    }
}
