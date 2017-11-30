package org.kd1sgr.mediamagic.services;

import net.coobird.thumbnailator.Thumbnails;
import org.kd1sgr.mediamagic.model.Image;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

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
      logger.info("Sending raw image: " + image );
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
      logger.info("Sending full size image: " + image );

      Thumbnails.of(image.getFilename())
              .useExifOrientation(false)
              .rotate(image.getOrientationVO().getDegrees())
              .height( 1500 )
              .outputFormat("jpg")
              .toOutputStream(os);
   }

   public void sendSlideshowImageDataToOutputStream( Image image, OutputStream os ) throws IOException
   {
      logger.info("Sending slide of image: " + image );
      File inputFile = image.getFilename();
      Path thumbnailDirectory = Paths.get( "target/thumbnails" );
      ThumbnailMakerService thumbnailMakerService = new ThumbnailMakerService( thumbnailDirectory );
      File outputFile = thumbnailMakerService.makeSlide( inputFile );
      Files.copy( outputFile.toPath(), os );
   }

   public void sendThumbnailImageDataToOutputStream( Image image, OutputStream os ) throws IOException
   {
      logger.info("Sending thumbnail of image: " + image );
      File inputFile = image.getFilename();
      Path thumbnailDirectory = Paths.get( "target/thumbnails" );
      ThumbnailMakerService thumbnailMakerService = new ThumbnailMakerService( thumbnailDirectory );
      File outputFile = thumbnailMakerService.makeThumb( inputFile );
      Files.copy( outputFile.toPath(), os );
   }





}

