package org.kd1sgr.mediamagic.repository;

import org.kd1sgr.mediamagic.model.CameraImage;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ImageRepository extends CrudRepository<CameraImage, Long> {

    public List<CameraImage> findAllByFilenameContains( String target );


}
