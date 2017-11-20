package org.kd1sgr.mediamagic.repository;

import org.kd1sgr.mediamagic.model.Image;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;


@Repository
@Transactional
public class ImageRepository {

    public ImageRepository() {}

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void save(Image image ) {
        em.persist( image );
    }

    @Transactional
    public List<Image> findImages() {
        Query query = em.createQuery( "SELECT u From Image u" );
        return query.getResultList();
    }

}
