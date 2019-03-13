package com.Be1StopClick.controllers;

import com.Be1StopClick.dao.UserDao;
import com.Be1StopClick.model.User;
import com.Be1StopClick.model.UserProfile;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/*
 * Created by dendy-prtha on 01/03/2019.
 * User REST Controller for user
 */

@RestController
public class UserController {

    @Autowired
    private UserDao userRepository;

    @RequestMapping(value = "/user/get-all-user",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public String showListUser(Model model) {
        ObjectMapper mapper = new ObjectMapper();
        String arrayToJson = null;
        try {
            //http://www.makeinjava.com/convert-list-objects-tofrom-json-java-jackson-objectmapper-example/
            //Map<String, List> container= new HashMap<>();
            //container.put("dafuq", userRepository.findAll());
            arrayToJson = mapper.writeValueAsString(userRepository.findAll());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return arrayToJson;
    }

    @RequestMapping(value = "/user/list", method = RequestMethod.GET)
    public ModelAndView showAllUser(Model model) {
        //UserRepository userRepository = appContext.getBean(UserRepository.class);
        List<User> user = userRepository.findAll();
        System.out.println("showListUser ");
        model.addAttribute("users", user);
        return new ModelAndView("users/list");
    }

    @PostMapping("/user/insert")
    public boolean insertUser(@RequestBody Map<String, Object> body) {
        String username = body.get("username").toString();
        String password = body.get("password").toString();
        ObjectMapper objectMapper = new ObjectMapper();
        Object usrPrflObject = body.get("user_profile");
        UserProfile usrPrfl = objectMapper.convertValue(usrPrflObject, UserProfile.class);
        User user = new User(null, username, password);
        user.setUserProfile(new UserProfile(null, usrPrfl.getFirstName(), usrPrfl.getLastName(),
                usrPrfl.getDob(), usrPrfl.getPhone(), usrPrfl.getProfilePhoto()));
        return userRepository.save(user);
    }

    @PostMapping("/user/login")
    public User login(@RequestBody Map<String, Object> body) {
        String username = body.get("username").toString();
        String password = body.get("password").toString();
        Optional<User> optionalUser = userRepository.findByUserNamePassword(username, password);
        if(optionalUser.isPresent()) {
            return optionalUser.get();
        }
        return null;
    }

    @PostMapping("/user/update")
    public boolean updateUser(@RequestBody Map<String, Object> body) {
        String id = body.get("id").toString();
        String username = body.get("username").toString();
        String password = body.get("password").toString();
        ObjectMapper objectMapper = new ObjectMapper();
        Object usrPrflObject = body.get("user_profile");
        UserProfile usrPrfl = objectMapper.convertValue(usrPrflObject, UserProfile.class);
        User user = new User(Long.parseLong(id), username, password);
        user.setUserProfile(new UserProfile(usrPrfl.getId(), usrPrfl.getFirstName(), usrPrfl.getLastName(),
                usrPrfl.getDob(), usrPrfl.getPhone(), usrPrfl.getProfilePhoto()));
        return userRepository.update(user);
    }

    @PostMapping("/user/delete")
    public boolean deleteUser(@RequestBody Map<String, Object> body) {
        String id = body.get("id").toString();
        User delUser = userRepository.find(Long.parseLong(id)).get();
        return userRepository.delete(delUser);
    }

}