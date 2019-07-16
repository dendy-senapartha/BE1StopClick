package com.Be1StopClick.dao.repository;

import com.Be1StopClick.dao.OrderDao;
import com.Be1StopClick.model.Orders;
import org.hibernate.HibernateException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

/*
 * Created by dendy-prtha on 04/06/2019.
 * Repository for order
 */

@Transactional
@Component
public class OrderRepository implements OrderDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Orders> findOrderByUserId(long userId) {
        String hql = "SELECT DISTINCT ords FROM Orders ords " +
                "INNER JOIN ords.invoice invc " +
                "INNER JOIN invc.user usr " +
                "WHERE usr.id=" + userId;
        //System.out.println(hql);
        Query query = entityManager.createQuery(hql);
        List<Orders> results = query.getResultList();
        return results;
    }

    @Override
    public List<Orders> getUserOrderNeedTooPay(long userId) {
        String hql = "SELECT DISTINCT ords FROM Orders ords " +
                "INNER JOIN ords.invoice invc " +
                "INNER JOIN invc.user usr " +
                "WHERE usr.id=" + userId + " " +
                "AND (invc.status LIKE 'DRAFT' OR invc.status LIKE 'ISSUED')";
        //System.out.println(hql);
        Query query = entityManager.createQuery(hql);
        List<Orders> results = query.getResultList();
        return results;
    }

    @Override
    public List<Orders> findUserDraftOrder(long userId) {
        String hql = "SELECT DISTINCT ords FROM Orders ords " +
                "INNER JOIN ords.invoice invc " +
                "INNER JOIN invc.user usr " +
                "WHERE usr.id=" + userId + " " +
                "AND invc.status LIKE 'DRAFT'";
        //System.out.println(hql);
        Query query = entityManager.createQuery(hql);
        List<Orders> results = query.getResultList();
        return results;
    }

    @Override
    public Optional<Orders> findById(Integer id) {
        String hql = "FROM Orders orders WHERE orders.id = :id";
        //System.out.println(hql);
        Query query = entityManager.createQuery(hql);
        query.setParameter("id", id);
        //List result = query.list();
        List<Orders> results = query.getResultList();
        //session.close();
        Orders orders1 = null;
        for (Orders orders : results) {
            orders1 = orders;
        }
        return Optional.ofNullable(orders1);
    }

    @Override
    public List<Orders> findAll() {
        return null;
    }

    @Override
    public boolean save(Orders orders) {
        boolean status;
        try {
            entityManager.persist(orders);
            status = true;
        } catch (HibernateException ex) {
            System.out.println("exception: " + ex);
            status = false;
        }

        return status;
    }

    @Override
    public boolean update(Orders orders) {
        boolean status = false;
        try {
            entityManager.merge(orders);
            status = true;
        } catch (HibernateException ex) {
            System.out.println("exception: " + ex);
        }

        return status;
    }

    @Override
    public boolean delete(Orders order) {
        boolean status = false;
        try {
            entityManager.remove(entityManager.contains(order) ? order : entityManager.merge(order));
            //entityManager.remove(order);
            status = true;
        } catch (HibernateException ex) {
            System.out.println("exception: " + ex);
        }
        return status;
    }
}
