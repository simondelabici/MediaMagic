package org.kd1sgr.mediamagic.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.nio.file.Paths;

@RunWith(SpringRunner.class)
@SpringBootTest
@Component
public class MediaImporterServiceTests {

    @Autowired
    private MediaImporterService mediaImporterService;

    @Test
    public void testScanFileTree() throws IOException
    {
        mediaImporterService.scanFileTree( Paths.get( "src/test/resources/org/kd1sgr/mediamagic/services" ) );
    }
}
