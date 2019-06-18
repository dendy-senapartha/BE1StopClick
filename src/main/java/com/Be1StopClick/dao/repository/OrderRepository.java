package com.Be1StopClick.dao.repository;

import com.Be1StopClick.dao.OrderDao;
import com.Be1StopClick.model.Orders;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
    public List<Orders> findOrderByUserId(int userId) {
        return null;
    }

    @Override
    public Optional<Orders> findById(Integer integer) {
        return Optional.empty();
    }

    @Override
    public List<Orders> findAll() {
        return null;
    }

    @Override
    public boolean save(Orders o) {
        return false;
    }

    @Override
    public boolean update(Orders o) {
        return false;
    }

    @Override
    public boolean delete(Orders o) {
        return false;
    }
}
