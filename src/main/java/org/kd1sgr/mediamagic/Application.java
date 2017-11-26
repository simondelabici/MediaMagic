package org.kd1sgr.mediamagic;

import org.kd1sgr.mediamagic.services.ImageSenderService;
import org.kd1sgr.mediamagic.services.MediaImporterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.nio.file.Paths;

@SpringBootApplication
public class Application
{
    private static final Logger logger = LoggerFactory.getLogger( ImageSenderService.class );

    @Autowired
    MediaImporterService mediaImporterService;

    public static void main(String[] args)
    {
        SpringApplication.run( Application.class, args );
    }


    @EventListener(ApplicationReadyEvent.class)
    public void doSomethingAfterStartup()
    {
       logger.info( "Hello world, I have just started up" );

       logger.info( "Importing test files..." );
       mediaImporterService.scanFileTree( Paths.get( "D:\\suki\\our_photos\\2017" ) );
       logger.info( "Importing test files - done" );

    }
}