package com.Be1StopClick.dao;

import com.Be1StopClick.model.UserProfile;

import java.util.Optional;
/*
 * Created by dendy-prtha on 05/03/2019.
 * User Profile Dao
 */

public interface UserProfileDao extends Dao<UserProfile, Integer> {

    /**
     * Find {@link UserProfile} by its firstName
     *
     * @param firstName username
     * @return UserProfile
     */
    Optional<UserProfile> findByFirstName(String firstName);

    /**
     * Find {@link UserProfile} by its lastName
     *
     * @param lastName username
     * @return UserProfile
     */
    Optional<UserProfile> findByLastName(String lastName);
}
