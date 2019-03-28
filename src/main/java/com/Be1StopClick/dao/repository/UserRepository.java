package com.Be1StopClick.dao.repository;

import com.Be1StopClick.dao.UserDao;
import com.Be1StopClick.model.User;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.util.*;

/*
 * Created by dendy-prtha on 01/03/2019.
 * User Repository class
 */

@Transactional
@Component
public class UserRepository implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<User> findByUserName(String userName) {

        String hql = "FROM User user WHERE user.userName = :userName";
        System.out.println(hql);
        Query query = entityManager.createQuery(hql);
        query.setParameter("userName", userName);
        List<User> results = query.getResultList();
        User user = null;
        for( User e:results ) {
            user = new User(
                    e.getId(),
                    e.getEmail(),
                    e.getPassword());
            user.setProvider(e.getProvider());
            user.setEmailVerified(e.getEmailVerified());
            user.setProviderId(e.getProviderId());
            user.setUserProfile(e.getUserProfile());
        }
        return Optional.ofNullable(user);
    }

    @Override
    public Optional<User> findByEmailPassword(String email, String password) {
        String hql = "FROM User user WHERE user.email LIKE :email AND user.password LIKE :password";
        System.out.println(hql);
        Query query = entityManager.createQuery(hql);
        query.setParameter("email", email);
        query.setParameter("password", password);

        List<User> results = query.getResultList();
        User user = null;
        for( User e:results ) {
            user = new User(
                    e.getId(),
                    e.getEmail(),
                    e.getPassword());
            user.setProvider(e.getProvider());
            user.setEmailVerified(e.getEmailVerified());
            user.setProviderId(e.getProviderId());
            user.setUserProfile(e.getUserProfile());
        }

        return Optional.ofNullable(user);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        String hql = "FROM User user WHERE user.email = :email";
        System.out.println(hql);
        Query query = entityManager.createQuery(hql);
        query.setParameter("email", email);
        List<User> results = query.getResultList();
        User user = null;
        for( User e:results ) {
            user = new User(
                    e.getId(),
                    e.getEmail(),
                    e.getPassword());
            user.setEmail(e.getEmail());
            user.setProvider(e.getProvider());
            user.setEmailVerified(e.getEmailVerified());
            user.setProviderId(e.getProviderId());
            user.setUserProfile(e.getUserProfile());
        }
        return Optional.ofNullable(user);
    }

    @Override
    public Optional<User> findById(Long aLong) {
        //Session session = this.sessionFactory.openSession();

        String hql = "FROM User user WHERE user.id = :id";
        System.out.println(hql);
        Query query = entityManager.createQuery(hql);
        query.setParameter("id", aLong);
        //List result = query.list();
        List<User> results = query.getResultList();
        //session.close();
        User user = null;
        for( User e:results ) {
            user = new User(
                    e.getId(),
                    e.getEmail(),
                    e.getPassword());
            user.setUserProfile(e.getUserProfile());
        }
        return Optional.ofNullable(user);
    }

    @Override
    public List<User> findAll() {
        String hql = "FROM User";
        System.out.println(hql);
        Query query = entityManager.createQuery(hql);
        List<User> results = query.getResultList();
        return results;
    }

    @Override
    public boolean save(User user) {
        boolean status;
        try
        {
            entityManager.persist(user);
            status = true;
        }
        catch (HibernateException ex)
        {
            System.out.println("exception: "+ex);
            status = false;
        }

        return status;
    }

    @Override
    public boolean update(User user) {

        boolean status = false ;
        try
        {
            entityManager.merge(user);
            status = true;
        }
        catch (HibernateException ex)
        {
            System.out.println("exception: "+ex);
        }

        return status;
    }

    @Override
    public boolean delete(User user) {

        boolean status = false ;
        try
        {
            entityManager.remove(entityManager.contains(user) ? user : entityManager.merge(user));
            status = true;
        }
        catch (HibernateException ex)
        {
            System.out.println("exception: "+ex);
        }
        return status;
    }
}
