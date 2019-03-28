package com.Be1StopClick.controllers;

import com.Be1StopClick.dao.UserDao;
import com.Be1StopClick.model.User;
import com.Be1StopClick.security.AppTokenProvider;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
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

    @PostMapping(value = "/auth/local-login",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> localLogin(@RequestBody Map<String, Object> body, HttpServletResponse response) {
        Map<String, Object> map = new HashMap<String, Object>();
        Optional<Object> email = Optional.ofNullable(body.get("email"));
        Optional<Object> password = Optional.ofNullable(body.get("password"));
        if (email.isPresent() && password.isPresent()) {
            Optional<User> userOptional = userRepository.findByEmailPassword(email.get().toString(), password.get().toString());
            User user;
            if (userOptional.isPresent()) {
                user = userOptional.get();
                AppTokenProvider.addAuthentication(response, user.getProviderId());
                ObjectMapper oMapper = new ObjectMapper();
                map = oMapper.convertValue(user, Map.class);
                return map;
            }
        }
        return null;
    }

    @PostMapping(value = "/auth/social-login",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> socialLogin(@RequestBody Map<String, Object> body) {
        Map<String, Object> map = new HashMap<String, Object>();
        Optional<Object> email = Optional.ofNullable(body.get("email"));
        if (email.isPresent()) {
            Optional<User> userOptional = userRepository.findByEmail(email.get().toString());
            User user;
            if (userOptional.isPresent()) {
                user = userOptional.get();
                ObjectMapper oMapper = new ObjectMapper();
                map = oMapper.convertValue(user, Map.class);
                return map;
            }
        }
        return null;
    }
}
