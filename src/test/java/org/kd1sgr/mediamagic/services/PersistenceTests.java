package org.kd1sgr.mediamagic.services;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kd1sgr.mediamagic.model.Image;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
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
        Image image1 = new Image( new File( "some file 1" ) );
        imageService.saveImage( image1 );

        List<Image> imageList1 = imageService.findImages();
        Assert.assertNotNull( imageList1 );
        Assert.assertEquals( imageList1.size(),1 );
        Assert.assertEquals( image1.getId(), imageList1.get( 0 ).getId() );

        Image image2 = new Image( new File( "some file 2" ) );
        imageService.saveImage( image2 );

        List<Image> imageList2 = imageService.findImages();
        Assert.assertNotNull( imageList2 );
        Assert.assertEquals( imageList2.size(),2 );
        Assert.assertEquals( image1.getFilename(), imageList2.get( 0 ).getFilename() );
    }
}
