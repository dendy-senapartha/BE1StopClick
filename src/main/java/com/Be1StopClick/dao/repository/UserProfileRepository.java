package com.Be1StopClick.dao.repository;

/*
 * Created by dendy-prtha on 05/03/2019.
 * repository for UserProfile
 */

import com.Be1StopClick.dao.UserProfileDao;
import com.Be1StopClick.model.UserProfile;

import java.util.List;
import java.util.Optional;

public class UserProfileRepository implements UserProfileDao {
    @Override
    public Optional<UserProfile> findByFirstName(String firstName) {
        return Optional.empty();
    }

    @Override
    public Optional<UserProfile> findByLastName(String lastName) {
        return Optional.empty();
    }

    @Override
    public Optional<UserProfile> findById(Integer integer) {
        return Optional.empty();
    }

    @Override
    public List<UserProfile> findAll() {
        return null;
    }

    @Override
    public boolean save(UserProfile o) {
        return false;
    }

    @Override
    public boolean update(UserProfile o) {
        return false;
    }

    @Override
    public boolean delete(UserProfile o) {
        return false;
    }
}
