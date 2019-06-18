package com.Be1StopClick.dao.repository;

import com.Be1StopClick.dao.VideoDao;
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
public class VideoRepository implements VideoDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<Video> findVideoByProductId(String productId) {
        String hql ="FROM Video video WHERE video.product.id = :productId";
        System.out.println(hql);
        Query query = entityManager.createQuery(hql);
        query.setParameter("productId", Integer.parseInt(productId));
        List<Video> results = query.getResultList();
        return results;
    }

    @Override
    public Optional<Video> findById(Integer integer) {
        return Optional.empty();
    }

    @Override
    public List<Video> findAll() {
        return null;
    }

    @Override
    public boolean save(Video o) {
        return false;
    }

    @Override
    public boolean update(Video o) {
        return false;
    }

    @Override
    public boolean delete(Video o) {
        return false;
    }
}
