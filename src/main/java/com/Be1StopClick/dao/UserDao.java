package com.Be1StopClick.dao;

import com.Be1StopClick.model.User;
import java.util.Optional;

/*
 * Created by dendy-prtha on 01/03/2019.
 * Provides interface specific to {@link User} data
 */

public interface UserDao extends Dao<User, Long> {
    /**
     * Find {@link User} by its username
     *
     * @param userName username
     * @return user
     */
    Optional<User> findByUserName(String userName);

    /**
     * Find {@link User} by its username and password.
     *
     * @param userName username
     * @param password password
     * @return user
     */
    Optional<User> findByEmailPassword(String userName, String password);

    /**
     * Find {@link User} by its email.
     *
     * @param email email     *
     * @return user
     */
    Optional<User> findByEmail(String email);
}
