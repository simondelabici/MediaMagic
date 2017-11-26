package org.kd1sgr.mediamagic.services;

import com.drew.imaging.ImageMetadataReader;
import com.drew.metadata.Directory;
import com.drew.metadata.Metadata;
import com.drew.metadata.Tag;
import org.kd1sgr.mediamagic.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

@Service
public class MediaImporterService {

   private static final Logger logger = LoggerFactory.getLogger( MediaImporterService.class );

   @Autowired
   private ImageService imageService;

   public MediaImporterService() {
      logger.info( "ImageSenderService constructed: " + this );
   }

   public void scanFileTree( Path rootDirectory ) {

      try {
         Files.walk(rootDirectory)
                 .filter(p -> p.toString().endsWith(".jpg"))
                 .forEach(p -> importToLibrary(p));
      }
      catch (IOException ex)
      {
         logger.error( "Exception scanning file tree\n" + ex.getLocalizedMessage(), ex );
      }
   }

   private void importToLibrary( Path imagePath )
   {
      logger.debug( "START ************************************************************************************" );

      Map<String,String> metaDataMap = this.extractMetadata( imagePath.toFile() );

      for ( String entry : metaDataMap.values() )
      {
         logger.debug( entry );
      }


      String width = metaDataMap.getOrDefault( "Image Width", "Unknown" );
      String height =  metaDataMap.getOrDefault( "Image Height", "Unknown" );
      ResolutionVO resolutionVO = ResolutionVO.of( width , height );
      String aperture = metaDataMap.getOrDefault( "", "Unknown" );
      String fNo= metaDataMap.getOrDefault( "", "Unknown" );
      String shutterSpeed= metaDataMap.getOrDefault( "", "Unknown" );
      String zoom = metaDataMap.getOrDefault( "", "Unknown" );
      String make = metaDataMap.getOrDefault( "Make", "Unknown" );
      String model = metaDataMap.getOrDefault( "Model", "Unknown" );

      Image image;
      if (metaDataMap.size() == 0 )
      {
         image = new OtherImage( imagePath.toFile(), ResolutionVO.UNKOWN, OrientationVO.ROTATE_NONE);
         logger.info( "Importing new image: " + image );
         //imageService.saveOtherImage( (OtherImage) image);
      }
      else
      {
         image = new CameraImage( imagePath.toFile(),
                 resolutionVO,
                 OrientationVO.ROTATE_NONE,
                 CameraVO.of( make, model ),
                 aperture, fNo, shutterSpeed, zoom );

         logger.info( "Importing new camera image: " + image );
         String imageId = imageService.saveCameraImage((CameraImage) image);
         logger.info( "Image was assigned id: " + imageId );
      }
      logger.debug( "END **************************************************************************************" );

   }

   /**
    * Returns a map of all metadata extracted from the image.
    *
    * @return Map the returned metadata map which contains a key for every
    * tag which was present in the image together with the
    * specified value.
    */
   private Map<String, String> extractMetadata( File filename )
   {
      // Attempt to read the metadata. If something goes wrong
      // handle it here: put an error in the log and return an
      // empty map.
      final Metadata metadata;
      final Map<String, String> map = new HashMap<>();

      logger.debug( "Reading metadata from image file: '" + this + "'..." );
      try
      {
         metadata = ImageMetadataReader.readMetadata( filename );
      }
      catch ( IOException | com.drew.imaging.ImageProcessingException ex )
      {
         logger.error( "Failed to read metadata from file: " + filename );
         return map;
      }

      for ( Directory directory : metadata.getDirectories() )
      {
         for ( Tag tag : directory.getTags() )
         {
            logger.debug( "Directory = " + directory.getName() + "; TagName = " + tag.getTagName() + "; TagDescription = " + tag.getDescription() );

            if ( tag.getDescription() != null ) {
               map.put(tag.getTagName().trim(), tag.getDescription().trim());
            }

         }
         if ( directory.hasErrors() )
         {
            for ( String error : directory.getErrors() )
            {
               logger.error( "Directory = " + directory.getName() + " has error:" + error );
            }
         }
      }
      logger.debug( "Reading metadata - DONE" );
      return map;
   }

}

