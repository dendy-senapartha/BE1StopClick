package com.Be1StopClick.dao.repository;

import com.Be1StopClick.dao.ProductDao;
import com.Be1StopClick.model.*;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

/*
 * Created by dendy-prtha on 16/04/2019.
 * Implementation of Movie DAO
 */

@Transactional
@Component
public class ProductRepository implements ProductDao {


    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<Product> findById(Integer id) {
        String hql = "FROM Product prdct WHERE prdct.id = :id";
        Query query = entityManager.createQuery(hql);
        query.setParameter("id", id);
        //List result = query.list();
        List<Product> results = query.getResultList();
        //session.close();
        Product product1 = null;
        for (Product product : results) {
            product1 = product;
        }
        return Optional.ofNullable(product1);
    }

    @Override
    public List<Product> findAll() {
        return null;
    }

    @Override
    public List<Product> findAllProductByCategoryId(int catId) {
        String hql = "FROM Product prdct WHERE prdct.category.id = " + catId;
        System.out.println(hql);
        Query query = entityManager.createQuery(hql);
        List<Product> results = query.getResultList();
        return results;
    }

    @Override
    public List<Product> findProductByCategoryIdAndTitle(int catId, String title) {
        String hql = "SELECT DISTINCT prdct FROM Product prdct " +
                "LEFT JOIN prdct.category ctgry " +
                "LEFT JOIN prdct.subcategory sbctgry " +
                "WHERE ctgry.id = " + catId +
                "AND prdct.productName LIKE '%" + title + "%'";

        Query query = entityManager.createQuery(hql);
        List<Product> results = query.getResultList();
        return results;
    }

    @Override
    public List<Product> getBuyedProductOfUserByCategory(int catId, String userId) {
        String hql = "SELECT DISTINCT prdct FROM Invoice invc " +
                "INNER JOIN invc.user usr " +
                "INNER JOIN invc.orders ordrs " +
                "INNER JOIN ordrs.orderItemList ordritm " +
                "INNER JOIN ordritm.product prdct " +
                "INNER JOIN prdct.category ctgry " +
                "WHERE ctgry.id = " + catId +
                "AND usr.id =" + userId;

        Query query = entityManager.createQuery(hql);
        List<Product> results = query.getResultList();
        return results;
    }

    @Override
    public List<Product> findBuyedProductOfUserByCategoryAndProdId(int catId, String userId, String productId) {
        String hql = "SELECT DISTINCT prdct FROM Invoice invc " +
                "INNER JOIN invc.user usr " +
                "INNER JOIN invc.orders ordrs " +
                "INNER JOIN ordrs.orderItemList ordritm " +
                "INNER JOIN ordritm.product prdct " +
                "INNER JOIN prdct.category ctgry " +
                "WHERE ctgry.id = " + catId + " " +
                "AND usr.id = " + userId + " " +
                "AND prdct.id = " + productId;

        Query query = entityManager.createQuery(hql);
        List<Product> results = query.getResultList();
        return results;
    }

    @Override
    public List<Product> findBuyedProductOfUserByCategoryAndProductName(int catId, String userId, String productName) {
        String hql = "SELECT DISTINCT prdct FROM Invoice invc " +
                "INNER JOIN invc.user usr " +
                "INNER JOIN invc.orders ordrs " +
                "INNER JOIN ordrs.orderItemList ordritm " +
                "INNER JOIN ordritm.product prdct " +
                "INNER JOIN prdct.category ctgry " +
                "WHERE ctgry.id = " + catId + " " +
                "AND usr.id = " + userId + " " +
                "AND prdct.productName LIKE '%" + productName + "%'";

        Query query = entityManager.createQuery(hql);
        List<Product> results = query.getResultList();
        return results;
    }

    @Override
    public List<Product> getAlbumProducts(String albumId) {
        String hql = "SELECT DISTINCT prdct FROM Product prdct " +
                "INNER JOIN prdct.trackList track " +
                "INNER JOIN track.album albm " +
                "WHERE albm.id = " + albumId;
        System.out.println(hql);
        Query query = entityManager.createQuery(hql);
        List<Product> results = query.getResultList();
        return results;
    }

    @Override
    public List<Product> findBuyedProductByUserIdAndAlbumId(String userId, String albumId) {
        String hql = "SELECT DISTINCT prdct FROM Invoice invc " +
                "INNER JOIN invc.user usr " +
                "INNER JOIN invc.orders ordrs " +
                "INNER JOIN ordrs.orderItemList ordritm " +
                "INNER JOIN ordritm.product prdct " +
                "INNER JOIN prdct.trackList track " +
                "INNER JOIN track.album albm " +
                "WHERE usr.id = " + userId + " " +
                "AND albm.id = " + albumId;
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
