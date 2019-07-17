package com.Be1StopClick.dao.repository;

import com.Be1StopClick.dao.BalanceDao;
import com.Be1StopClick.model.Balance;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

/*
 * Created by dendy-prtha on 10/06/2019.
 * repository for balance
 */

@Transactional
@Component
public class BalanceRepository implements BalanceDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Balance> findBalanceByUserId(String userId) {
        String hql ="FROM Balance balance WHERE balance.user.id = :userId";
        System.out.println(hql);
        Query query = entityManager.createQuery(hql);
        query.setParameter("userId", Long.parseLong(userId));
        List<Balance> results = query.getResultList();
        return results;
    }

    @Override
    public Optional<Balance> findById(Integer integer) {
        return Optional.empty();
    }

    @Override
    public List<Balance> findAll() {
        return null;
    }

    @Override
    public boolean save(Balance balance) {
        boolean status;
        try {
            entityManager.persist(balance);
            status = true;
        } catch (HibernateException ex) {
            System.out.println("exception: " + ex);
            status = false;
        }

        return status;
    }

    @Override
    public boolean update(Balance balance) {
        boolean status = false;
        try {
            entityManager.merge(balance);
            status = true;
        } catch (HibernateException ex) {
            System.out.println("exception: " + ex);
        }

        return status;
    }

    @Override
    public boolean delete(Balance o) {
        return false;
    }
}
