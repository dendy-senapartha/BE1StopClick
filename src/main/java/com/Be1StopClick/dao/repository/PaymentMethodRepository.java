package com.Be1StopClick.dao.repository;

import com.Be1StopClick.dao.PaymentMethodDao;
import com.Be1StopClick.dao.VideoDao;
import com.Be1StopClick.model.PaymentMethod;
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
public class PaymentMethodRepository implements PaymentMethodDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<PaymentMethod> findById(Integer id) {
        String hql = "FROM PaymentMethod pymthd WHERE pymthd.id = :id";
        Query query = entityManager.createQuery(hql);
        query.setParameter("id", id);
        List<PaymentMethod> results = query.getResultList();
        PaymentMethod paymentMethod = null;
        for (PaymentMethod e : results) {
            paymentMethod = e;
        }
        return Optional.ofNullable(paymentMethod);
    }

    @Override
    public List<PaymentMethod> findAll() {
        return null;
    }

    @Override
    public boolean save(PaymentMethod paymentMethod) {
        return false;
    }

    @Override
    public boolean update(PaymentMethod paymentMethod) {
        return false;
    }

    @Override
    public boolean delete(PaymentMethod paymentMethod) {
        return false;
    }
}
