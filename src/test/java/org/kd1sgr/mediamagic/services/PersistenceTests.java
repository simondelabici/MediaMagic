package org.kd1sgr.mediamagic.services;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kd1sgr.mediamagic.model.CameraImageEntity;
import org.kd1sgr.mediamagic.model.MediaEntity;
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
        CameraImageEntity cameraImage1 = new CameraImageEntity( 0, "aperture X", "fNo Y", "1/100s", "1.0 ");
        imageService.saveImage( cameraImage1 );

        List<CameraImageEntity> imageList1 = imageService.findImages();
        Assert.assertNotNull( imageList1 );
        Assert.assertEquals( imageList1.size(),1 );
        Assert.assertEquals( cameraImage1.getId(), imageList1.get( 0 ).getId() );

     }
}
