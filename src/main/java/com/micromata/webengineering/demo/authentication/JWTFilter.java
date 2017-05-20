package com.micromata.webengineering.demo.authentication;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This filter filters all request for a valid bearer token to authenticate the request.
 *
 * Created by Jonas Scherbaum on 20.05.2017.
 */
@Slf4j
public class JWTFilter extends GenericFilterBean {

    private AuthenticationService authenticationService;

    /**
     * The name of the Regex-Group for the Bearer-Token
     */
    private static final String TOKEN_GROUP_NAME = "token";

    /**
     * This regular expression validates each Authentication header for a valid bearer token.
     */
    private static final Pattern BEARER_PATTERN = Pattern.compile("Bearer\\s(?<" + TOKEN_GROUP_NAME +
            ">[A-Za-z0-9\\-._~+/]+=*)");


    public JWTFilter(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
            throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        Optional<String> optionalAuth = Optional.of(httpServletRequest.getHeader("Authorization"));

        if (!optionalAuth.isPresent()) {
            log.warn("No authorization token submitted");
            httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
            return;
        }

        Matcher bearerMatcher = BEARER_PATTERN.matcher(optionalAuth.get());

        if (!bearerMatcher.matches()) {
            log.warn("No authorization token submitted");
            httpServletResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
            return;
        }

        // Extract token contents.
        String token = bearerMatcher.group(TOKEN_GROUP_NAME);
        try {
            Claims body = (Claims) authenticationService.parseToken(token);
            log.info("Successful login from {}/{}", body.getSubject(), body.getId());
            authenticationService.setUser(Long.parseLong(body.getId()), body.getSubject());
            filterChain.doFilter(request, response);
        } catch (SignatureException e) {
            log.warn("Token is invalid: {}", token);
            httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }

    }
}
