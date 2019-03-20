package com.Be1StopClick.controllers;

import com.Be1StopClick.dao.UserDao;
import com.Be1StopClick.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/*
 * Created by dendy-prtha on 20/03/2019.
 * Controller for auth API
 */

@RestController
public class AuthController {

    @Autowired
    private UserDao userRepository;

    @PostMapping(value = "/auth/login",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public User login(@RequestBody Map<String, Object> body) {
        String email = body.get("username").toString();
        Optional<User> userOptional = userRepository.findByEmail(email);
        User user;
        if (userOptional.isPresent()) {
            user = userOptional.get();
            return user;
        }
        return null;
    }
}
