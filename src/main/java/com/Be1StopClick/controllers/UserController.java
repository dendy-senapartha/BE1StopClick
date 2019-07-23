package com.Be1StopClick.controllers;

import com.Be1StopClick.dao.UserDao;
import com.Be1StopClick.dao.UserProfileDao;
import com.Be1StopClick.dto.UserProfileDTO;
import com.Be1StopClick.dto.request.UpdateUserProfileRequest;
import com.Be1StopClick.dto.response.UpdateUserProfileResponse;
import com.Be1StopClick.model.Orders;
import com.Be1StopClick.model.User;
import com.Be1StopClick.model.UserProfile;
import com.Be1StopClick.util.IdUtility;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
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

    @Autowired
    private UserProfileDao userProfileRepository;

    @Autowired
    private ModelMapper modelMapper;

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

    @PostMapping(value = "/user/insert", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String, String> insertUser(@RequestBody Map<String, Object> body) {
        String email = body.get("email").toString();
        boolean emailVerified = false;//body.get("email_verified").toString();
        String password = body.get("password").toString();
        String provider = body.get("provider").toString();
        String provider_id = IdUtility.generateUniqueID();
        ObjectMapper objectMapper = new ObjectMapper();
        Object usrPrflObject = body.get("user_profile");
        UserProfile usrPrfl = objectMapper.convertValue(usrPrflObject, UserProfile.class);
        User user = new User(null, email, emailVerified, password, provider, provider_id);
        user.addUserProfile(new UserProfile(null, usrPrfl.getName(),
                usrPrfl.getDob(), usrPrfl.getPhone(), usrPrfl.getImageUrl()));
        Map<String, String> map = new HashMap<>();
        Optional<User> userOptional = userRepository.findByEmail(email);
        if (userOptional.isPresent()) {
            map.put("result", "" + false);
            map.put("error_message", "Email already Used!");
        } else {
            map.put("result", "" + userRepository.save(user));
        }
        return map;
    }

    @PostMapping(value = "/user/update", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String, String> updateUser(@RequestBody Map<String, Object> body) {
        String id = body.get("id").toString();
        String username = body.get("username").toString();
        String password = body.get("password").toString();
        ObjectMapper objectMapper = new ObjectMapper();
        Object usrPrflObject = body.get("user_profile");
        UserProfile usrPrfl = objectMapper.convertValue(usrPrflObject, UserProfile.class);
        User user = new User(Long.parseLong(id), username, password);
        user.getUserProfile().setName(usrPrfl.getName());
        user.getUserProfile().setDob(usrPrfl.getDob());
        user.getUserProfile().setPhone(usrPrfl.getPhone());
        user.getUserProfile().setImageUrl(usrPrfl.getImageUrl());

        Map<String, String> map = new HashMap<>();
        map.put("result", "" + userRepository.update(user));
        return map;
    }

    @PostMapping(value = "/user/delete", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public Map<String, String> deleteUser(@RequestBody Map<String, Object> body) {
        String id = body.get("id").toString();
        User delUser = userRepository.findById(Long.parseLong(id)).get();
        Map<String, String> map = new HashMap<String, String>();
        map.put("result", "" + userRepository.delete(delUser));
        return map;
    }

    @PostMapping(value = "/user/get-user-profile-by-userid",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public  Map<String, UserProfileDTO> getUserProfileByUserId(@RequestBody Map<String, Object> body) {
        Map<String, UserProfileDTO> result = new HashMap<>();
        UserProfileDTO response = new UserProfileDTO();

        Optional<Object> userId= Optional.ofNullable(body.get("userId"));

        Optional<User> userOptional = userRepository.findById(Long.valueOf(userId.get().toString()));
        User currentUser = null;
        if (userOptional.isPresent()) {
            currentUser = userOptional.get();
            response.setId(currentUser.getUserProfile().getId());
            response.setName(currentUser.getUserProfile().getName());
            response.setDob(currentUser.getUserProfile().getDob());
            response.setImageUrl(currentUser.getUserProfile().getImageUrl());
            response.setPhone(currentUser.getUserProfile().getPhone());
        }
        result.put("result", modelMapper.map(response, UserProfileDTO.class));
        return result;
    }

    @PostMapping(value = "/user/update-user-profile",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public  Map<String, UpdateUserProfileResponse> updateUserProfile(@RequestBody UpdateUserProfileRequest request) {
        Map<String, UpdateUserProfileResponse> result = new HashMap<>();
        UpdateUserProfileResponse response = new UpdateUserProfileResponse();

        Optional<User> userOptional = userRepository.findById(request.getUserId());
        User currentUser = null;
        if (userOptional.isPresent()) {
            currentUser = userOptional.get();
            currentUser.getUserProfile().setName(request.getName());
            currentUser.getUserProfile().setDob(request.getDob());
            currentUser.getUserProfile().setImageUrl(request.getImageUrl());
            currentUser.getUserProfile().setPhone(request.getPhone());
        }
        response.setStatus(""+userRepository.update(currentUser));

        result.put("result", modelMapper.map(response, UpdateUserProfileResponse.class));
        return result;
    }

}