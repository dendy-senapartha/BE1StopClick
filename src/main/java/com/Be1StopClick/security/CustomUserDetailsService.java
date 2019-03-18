package com.Be1StopClick.security;

import com.Be1StopClick.dao.UserDao;
import com.Be1StopClick.dao.repository.UserRepository;
import com.Be1StopClick.exception.ResourceNotFoundException;
import com.Be1StopClick.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by dendy-prtha on 02/08/17.
 * //Service for user Detail
 */

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserDao userRepository;

    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User not found with email : " + email)
        );

        return UserPrincipal.create(user);
    }

    public UserDetails loadUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("User", "id", id)
        );

        return UserPrincipal.create(user);
    }
}