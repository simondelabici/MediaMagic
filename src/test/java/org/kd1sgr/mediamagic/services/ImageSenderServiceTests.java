package org.kd1sgr.mediamagic.services;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kd1sgr.mediamagic.model.CameraImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@Component
public class ImageSenderServiceTests {

    @Autowired
    private MediaImporterService mediaImporterService;

    @Autowired ImageSenderService imageSenderService;

    @Autowired ImageService imageService;

    @Test
    public void testSendRawImage() throws IOException
    {
        //mediaImporterService.scanFileTree( Paths.get( "src/test/rest/org/kd1sgr/mediamagic/services" ) );
        mediaImporterService.scanFileTree( Paths.get( "D:\\suki\\our_photos\\2017" ) );

        List<CameraImage> list = imageService.findCameraImages();

        Assert.assertNotNull( list );
        Assert.assertTrue( list.size() > 0 );
        CameraImage cameraImage = list.get( 0 );

        ByteArrayOutputStream os = new ByteArrayOutputStream();
        imageSenderService.sendRawImageDataToOutputStream( cameraImage, os );
        Assert.assertTrue( os.size() > 0 );
    }
}
