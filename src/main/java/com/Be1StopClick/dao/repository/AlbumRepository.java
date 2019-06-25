package com.Be1StopClick.dao.repository;

import com.Be1StopClick.dao.AlbumDao;
import com.Be1StopClick.model.Album;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;

/*
 * Created by dendy-prtha on 21/06/2019.
 * Album Repository
 */

@Transactional
@Component
public class AlbumRepository implements AlbumDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Album> getBuyedAlbumOfUser(String userId) {
        String hql = "SELECT DISTINCT albm FROM Invoice invc " +
                "INNER JOIN invc.user usr " +
                "INNER JOIN invc.orders ordrs " +
                "INNER JOIN invc.receipt rcpt " +
                "INNER JOIN ordrs.itemList ordritm " +
                "INNER JOIN ordritm.product prdct " +
                "INNER JOIN prdct.trackList track " +
                "INNER JOIN track.album albm " +
                "WHERE usr.id =" + userId;

        Query query = entityManager.createQuery(hql);
        List<Album> results = query.getResultList();
        return results;
    }

    @Override
    public Optional<Album> findById(Integer integer) {
        return Optional.empty();
    }

    @Override
    public List<Album> findAll() {
        String hql = "From Album";
        System.out.println(hql);
        Query query = entityManager.createQuery(hql);
        List<Album> results = query.getResultList();
        return results;
    }

    @Override
    public boolean save(Album o) {
        return false;
    }

    @Override
    public boolean update(Album o) {
        return false;
    }

    @Override
    public boolean delete(Album o) {
        return false;
    }
}
