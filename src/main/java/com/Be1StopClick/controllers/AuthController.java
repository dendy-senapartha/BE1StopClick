package com.Be1StopClick.controllers;

import com.Be1StopClick.dao.UserDao;
import com.Be1StopClick.mail.EmailService;
import com.Be1StopClick.mail.MailObject;
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
    public EmailService emailService;

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

    @PostMapping(value = "/auth/forget-password",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, Object> forgetPassword(@RequestBody Map<String, Object> body) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("status", false);
        Optional<Object> email = Optional.ofNullable(body.get("email"));
        if (email.isPresent()) {
            Optional<User> userOptional = userRepository.findByEmail(email.get().toString());
            User user;
            if (userOptional.isPresent()) {
                user = userOptional.get();
                MailObject mailObject = new MailObject();
                mailObject.setTo(user.getEmail());
                //remove hard coded text
                mailObject.setSubject("[1StopClick Forgot Password] User "+user.getUserProfile().getName());
                mailObject.setText("Your Account password is : "+user.getPassword());
                emailService.sendSimpleMessage(mailObject.getTo(), mailObject.getSubject(), mailObject.getText());
                map.clear();
                map.put("status", true);
                map.put("error_message", "");
            }
            else
            {
                map.put("error_message", "User Email Not found! Please make sure your email are registered.");
            }
        }
        else
        {
            map.put("error_message", "no email request!");
        }
        return map;
    }
}
