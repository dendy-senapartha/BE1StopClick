package com.Be1StopClick.dao.repository;

import com.Be1StopClick.dao.TrackDao;
import com.Be1StopClick.dao.VideoDao;
import com.Be1StopClick.model.Product;
import com.Be1StopClick.model.Track;
import com.Be1StopClick.model.Video;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;
import java.util.Optional;
/*
 * Created by dendy-prtha on 11/06/2019.
 * video repository
 */

@Transactional
@Component
public class TrackRepository implements TrackDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Track> findTrackByProductId(String productId) {
        String hql = "FROM Track track WHERE track.product.id = :productId";
        System.out.println(hql);
        Query query = entityManager.createQuery(hql);
        query.setParameter("productId", Integer.parseInt(productId));
        List<Track> results = query.getResultList();
        return results;
    }

    @Override
    public List<Track> getTracksByAlbumAndUserId(String productId) {
        String hql = "FROM Track track WHERE track.product.id = :productId";
        System.out.println(hql);
        Query query = entityManager.createQuery(hql);
        query.setParameter("productId", Integer.parseInt(productId));
        List<Track> results = query.getResultList();
        return results;
    }

    @Override
    public Optional<Track> findById(Integer integer) {
        return Optional.empty();
    }

    @Override
    public List<Track> findAll() {
        return null;
    }

    @Override
    public boolean save(Track o) {
        return false;
    }

    @Override
    public boolean update(Track o) {
        return false;
    }

    @Override
    public boolean delete(Track o) {
        return false;
    }
}
