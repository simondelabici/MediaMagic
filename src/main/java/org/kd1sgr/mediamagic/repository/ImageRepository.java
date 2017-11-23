package org.kd1sgr.mediamagic.repository;

import org.kd1sgr.mediamagic.model.CameraImageEntity;
import org.kd1sgr.mediamagic.model.MediaEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;


@Repository
@Transactional
public class ImageRepository {

    public ImageRepository() {}

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void save(CameraImageEntity cameraImageEntity ) {
        em.persist( cameraImageEntity );
    }

    @Transactional
    public List<CameraImageEntity> findImages() {
        Query query = em.createQuery( "SELECT u From CameraImageEntity u" );
        return query.getResultList();
    }

}
