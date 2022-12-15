package com.trainingcode.config;

import com.trainingcode.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

//database seeding
@Configuration
@Profile("test")
public class TestConfig {
    //dependency association
    @Autowired
    private UserRepository userRepository;
}
