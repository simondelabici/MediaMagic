package org.kd1sgr.mediamagic.services;

import org.kd1sgr.mediamagic.model.CameraImageMediaEntity;
import org.kd1sgr.mediamagic.model.OtherImageEntity;
import org.kd1sgr.mediamagic.repository.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class ImageService {

    @Autowired
    private ImageRepository imageRepository;

    public ImageService() {}

    @Transactional
    public void saveOtherImage( OtherImageEntity otherImageEntity) {
        imageRepository.save( otherImageEntity );
    }

    @Transactional
    public void saveCameraImage(CameraImageMediaEntity cameraImageEntity) {
        imageRepository.save( cameraImageEntity );
    }

    public List<CameraImageMediaEntity> findImages() {

        return imageRepository.findCameraImages();
    }



}
