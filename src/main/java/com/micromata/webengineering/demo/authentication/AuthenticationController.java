package com.micromata.webengineering.demo.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Jonas Scherbaum on 20.05.2017.
 */
@RestController
@RequestMapping("/user")
public class AuthenticationController {

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    public static class UserLogin {
        private String email;
        private String password;
    }

    private final AuthenticationService authenticationService;

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public ResponseEntity<AuthenticationService.UserToken> login(@RequestBody UserLogin userLogin) {
        AuthenticationService.UserToken userToken = this.authenticationService.login(userLogin.email, userLogin.password);

        if (userToken == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(userToken, HttpStatus.OK);
    }
}
