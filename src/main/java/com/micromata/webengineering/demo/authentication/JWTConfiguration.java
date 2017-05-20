package com.micromata.webengineering.demo.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * This configuration class adds the JWTFilter to the Filters.
 *
 * Created by Jonas Scherbaum on 20.05.2017.
 */
@Configuration
public class JWTConfiguration {

    private final AuthenticationService authenticationService;

    @Autowired
    public JWTConfiguration(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @Bean
    public FilterRegistrationBean jwtFilter() {
        FilterRegistrationBean jwtFilterBean = new FilterRegistrationBean();
        jwtFilterBean.setFilter(new JWTFilter(this.authenticationService));
        jwtFilterBean.addUrlPatterns("/api/*");
        return jwtFilterBean;
    }
}
