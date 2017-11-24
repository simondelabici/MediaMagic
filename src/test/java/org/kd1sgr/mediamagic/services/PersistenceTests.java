package org.kd1sgr.mediamagic.services;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kd1sgr.mediamagic.model.CameraVO;
import org.kd1sgr.mediamagic.model.CameraImageMediaEntity;
import org.kd1sgr.mediamagic.model.OrientationVO;
import org.kd1sgr.mediamagic.model.ResolutionVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Component
public class PersistenceTests {

    @Autowired
    private ImageService imageService;

    @Test
    public void testSaveImage()
    {
        CameraImageMediaEntity cameraImage = new CameraImageMediaEntity( new File( "some file" ),
                ResolutionVO.UNKOWN,
                OrientationVO.ROTATE_NONE,
                CameraVO.UNKOWN,
                "aperture X", "fNo Y", "1/100s", "1.0 ");

        imageService.saveCameraImage( cameraImage );

        List<CameraImageMediaEntity> imageList = imageService.findImages();
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
}
