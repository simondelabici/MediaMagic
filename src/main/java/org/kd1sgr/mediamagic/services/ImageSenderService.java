package org.kd1sgr.mediamagic.services;

import org.kd1sgr.mediamagic.model.Image;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;

@Service
public class ImageSenderService {

   private static final Logger logger = LoggerFactory.getLogger( ImageSenderService.class );

   @Autowired
   private ImageService imageService;

   private ImageSenderService() {
      logger.info( "ImageSenderService constructed: " + this );
   }

   public void sendRawImageDataToOutputStream( Image image, OutputStream os ) throws IOException
   {
      logger.info( "Sending: " + image + ": sending raw image data to output stream" );
      Files.copy( image.getFilename().toPath(), os );
   }

}

