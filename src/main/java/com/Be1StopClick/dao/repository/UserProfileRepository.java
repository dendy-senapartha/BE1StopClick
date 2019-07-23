package com.Be1StopClick.dao.repository;

/*
 * Created by dendy-prtha on 05/03/2019.
 * repository for UserProfile
 */

import com.Be1StopClick.dao.UserProfileDao;
import com.Be1StopClick.model.UserProfile;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Optional;


@Transactional
@Component
public class UserProfileRepository implements UserProfileDao {

    @PersistenceContext
    private EntityManager entityManager;

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
    public boolean update(UserProfile userProfile) {
        boolean status = false;
        try {
            entityManager.merge(userProfile);
            status = true;
        } catch (HibernateException ex) {
            System.out.println("exception: " + ex);
        }

        return status;
    }

    @Override
    public boolean delete(UserProfile o) {
        return false;
    }
}
