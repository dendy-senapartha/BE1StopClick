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

        if(user != null)
        {
            return Optional.of(user);
        }
        else
        {
            return Optional.empty();
        }
    }

    @Override
    public Optional<User> findByUserNamePassword(String userName, String password) {
        String hql = "FROM User user WHERE user.userName = :userName AND user.password = :password ";
        System.out.println(hql);
        Query query = entityManager.createQuery(hql);
        query.setParameter("userName", userName);
        query.setParameter("password", password);

        List<User> results = query.getResultList();

        User user = null;

        if(user != null)
        {
            return Optional.of(user);
        }
        else
        {
            return Optional.empty();
        }
    }

    @Override
    public Optional<User> find(Long aLong) {
        //Session session = this.sessionFactory.openSession();

        String hql = "FROM User user WHERE user.id = :id";
        System.out.println(hql);
        Query query = entityManager.createQuery(hql);
        query.setParameter("id", aLong);
        //List result = query.list();
        List<User> results = query.getResultList();
        //session.close();
        User user = null;

        if(user != null)
        {
            return Optional.of(user);
        }
        else
        {
            return Optional.empty();
        }
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
        boolean status = false ;
        try
        {
            entityManager.persist(user);
            status = true;
        }
        catch (HibernateException ex)
        {
            System.out.println("exception: "+ex);
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
