package org.kd1sgr.mediamagic.repository;

import org.kd1sgr.mediamagic.model.CameraImageMediaEntity;
import org.kd1sgr.mediamagic.model.OtherImageEntity;
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
    public void save(CameraImageMediaEntity cameraImageEntity ) {
        em.persist( cameraImageEntity );
    }

    @Transactional
    public void save( OtherImageEntity otherImageEntity ) {
        em.persist( otherImageEntity );
    }

    @Transactional
    public List<CameraImageMediaEntity> findCameraImages() {
        Query query = em.createQuery( "SELECT u From CameraImageMediaEntity u" );
        return query.getResultList();
    }

}
