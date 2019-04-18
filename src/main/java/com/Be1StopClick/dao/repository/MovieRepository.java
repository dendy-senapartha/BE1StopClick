package com.Be1StopClick.dao.repository;

import com.Be1StopClick.dao.MovieDao;
import com.Be1StopClick.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

/*
 * Created by dendy-prtha on 16/04/2019.
 * TODO: Add a class header comment!
 */

@Transactional
@Component
public class MovieRepository implements MovieDao {


    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<Product> findById(Integer integer) {
        return Optional.empty();
    }

    @Override
    public List<Product> findAll() {
        String hql = "SELECT DISTINCT prdct FROM Product prdct " +
                "LEFT JOIN prdct.category ctgry " +
                "LEFT JOIN prdct.subcategory sbctgry " +
                "WHERE ctgry.id = 3";
        System.out.println(hql);
        Query query = entityManager.createQuery(hql);
        List<Product> results = query.getResultList();
        return results;
    }

    @Override
    public boolean save(Product o) {
        return false;
    }

    @Override
    public boolean update(Product o) {
        return false;
    }

    @Override
    public boolean delete(Product o) {
        return false;
    }
}
