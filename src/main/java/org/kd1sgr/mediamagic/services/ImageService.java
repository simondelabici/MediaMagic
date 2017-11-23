package org.kd1sgr.mediamagic.services;

import org.kd1sgr.mediamagic.model.CameraImageEntity;
import org.kd1sgr.mediamagic.model.MediaEntity;
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
    public void saveImage(CameraImageEntity cameraImageEntity) {
        imageRepository.save( cameraImageEntity );
    }

    public List<CameraImageEntity> findImages() {

        return imageRepository.findImages();
    }



}
