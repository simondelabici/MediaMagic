package org.kd1sgr.mediamagic.services;

import net.coobird.thumbnailator.Thumbnails;
import org.kd1sgr.mediamagic.model.Image;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;

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

   /**
    * Sends the normal full size image data to the specified output stream with the configured orientation.
    *
    * @param os the output stream on which to send the data.
    *
    * @throws IOException if the operation failed, for example the image file could
    *  not be read or the operation to write the data to the output stream fails.
    */
   public void sendFullSizeImageDataToOutputStream( Image image, OutputStream os ) throws IOException
   {
      logger.info("processing: " + image + ": sending full size image to output stream");

      Thumbnails.of(image.getFilename())
              .useExifOrientation(false)
              .rotate(image.getOrientationVO().getDegrees())
              .height(1500)
              .outputFormat("jpg")
              .toOutputStream(os);
   }

}

