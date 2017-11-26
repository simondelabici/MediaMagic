package org.kd1sgr.mediamagic.services;

import org.kd1sgr.mediamagic.model.CameraImage;
import org.kd1sgr.mediamagic.repository.ImageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ImageService {

    private static final Logger logger = LoggerFactory.getLogger( ImageService.class );

    @Autowired
    private ImageRepository imageRepository;

    public ImageService() {

        logger.info( "ImageService constructed: " + this );
    }

    public String saveCameraImage( CameraImage cameraImageEntity )
    {
        imageRepository.save( cameraImageEntity );
        return cameraImageEntity.getId().toString();
    }

    public Optional<CameraImage> getCameraImage( String imageId )
    {
        return imageRepository.findById( Long.valueOf( imageId ) );
    }

    public List<CameraImage> findCameraImages() {

        List<CameraImage> list = new ArrayList<>();
        Iterable<CameraImage> myIterable = imageRepository.findAll();
        myIterable.iterator().forEachRemaining( list::add );
        return list;
    }

    public List<CameraImage> findCameraImagesByYearAndMonth( YearMonth yearMonth ) {

        List<CameraImage> list = new ArrayList<>();
        final String pattern = yearMonth.format( DateTimeFormatter.ofPattern( "yyyy-MM" ) );
        Iterable<CameraImage> myIterable = imageRepository.findAllByFilenameContains( pattern );
        myIterable.iterator().forEachRemaining( list::add );
        return list;
    }

}
