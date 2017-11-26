package org.kd1sgr.mediamagic.services;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kd1sgr.mediamagic.model.CameraImage;
import org.kd1sgr.mediamagic.model.CameraVO;
import org.kd1sgr.mediamagic.model.OrientationVO;
import org.kd1sgr.mediamagic.model.ResolutionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.util.List;
import java.util.Optional;

@RunWith(SpringRunner.class)
@SpringBootTest
@Component
public class ImageServiceTests {

    @Autowired
    private ImageService imageService;

    @Test
    public void testSaveAndFindImages()
    {
        CameraImage cameraImage = new CameraImage( new File( "some file" ),
                ResolutionVO.UNKOWN,
                OrientationVO.ROTATE_NONE,
                CameraVO.UNKOWN,
                "aperture X", "fNo Y", "1/100s", "1.0 ");

        imageService.saveCameraImage( cameraImage );

        List<CameraImage> imageList = imageService.findCameraImages();
        Assert.assertNotNull( imageList );
        Assert.assertEquals( imageList.size(),1 );
        Assert.assertEquals( cameraImage.getId(), imageList.get( 0 ).getId() );
        Assert.assertEquals( cameraImage.getFilename(), imageList.get( 0 ).getFilename() );
        Assert.assertEquals( cameraImage.getResolutionVO(), imageList.get( 0 ).getResolutionVO() );
        Assert.assertEquals( cameraImage.getOrientationVO(), imageList.get( 0 ).getOrientationVO() );
        Assert.assertEquals( cameraImage.getCamera(), imageList.get( 0 ).getCamera() );
        Assert.assertEquals( cameraImage.getAperture(), imageList.get( 0 ).getAperture() );
        Assert.assertEquals( cameraImage.getfNo(), imageList.get( 0 ).getfNo() );
        Assert.assertEquals( cameraImage.getShutterSpeed(), imageList.get( 0 ).getShutterSpeed() );
        Assert.assertEquals( cameraImage.getZoom(), imageList.get( 0 ).getZoom() );
     }

    @Test
    public void testSaveAndFindImage()
    {
        CameraImage sourceImage = new CameraImage( new File( "some file" ),
                ResolutionVO.UNKOWN,
                OrientationVO.ROTATE_NONE,
                CameraVO.UNKOWN,
                "aperture X", "fNo Y", "1/100s", "1.0 ");

        final String imageId = imageService.saveCameraImage( sourceImage );
        Optional<CameraImage> result = imageService.getCameraImage( imageId );

        Assert.assertTrue( result.isPresent() );
        final CameraImage restoredImage = result.get();

        Assert.assertNotNull( restoredImage );
        Assert.assertEquals( sourceImage.getId(), restoredImage.getId() );
        Assert.assertEquals( sourceImage.getFilename(), restoredImage.getFilename() );
        Assert.assertEquals( sourceImage.getResolutionVO(), restoredImage.getResolutionVO() );
        Assert.assertEquals( sourceImage.getOrientationVO(), restoredImage.getOrientationVO() );
        Assert.assertEquals( sourceImage.getCamera(), restoredImage.getCamera() );
        Assert.assertEquals( sourceImage.getAperture(), restoredImage.getAperture() );
        Assert.assertEquals( sourceImage.getfNo(), restoredImage.getfNo() );
        Assert.assertEquals( sourceImage.getShutterSpeed(), restoredImage.getShutterSpeed() );
        Assert.assertEquals( sourceImage.getZoom(), restoredImage.getZoom() );

    }
}
