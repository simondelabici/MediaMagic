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

   public void scanFileTree( Path rootDirectory ) throws IOException {

      Files.walk( rootDirectory )
              .filter(p -> p.toString().endsWith(".jpg"))
              .forEach( p -> importToLibrary( p ) );
   }

   private void importToLibrary( Path imagePath )
   {
      logger.info( "START ************************************************************************************" );

      Map<String,String> metaDataMap = this.extractMetadata( imagePath.toFile() );

      for ( String entry : metaDataMap.values() )
      {
         logger.info( entry );
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

      ImageMediaEntity image;
      if (metaDataMap.size() == 0 )
      {
         image = new OtherImageEntity( imagePath.toFile(), ResolutionVO.UNKOWN, OrientationVO.ROTATE_NONE);
         logger.info( "Importing new image: " + image );
         imageService.saveOtherImage( (OtherImageEntity) image);
      }
      else
      {
         image = new CameraImageMediaEntity( imagePath.toFile(),
                 resolutionVO,
                 OrientationVO.ROTATE_NONE,
                 CameraVO.of( make, model ),
                 aperture, fNo, shutterSpeed, zoom );

         logger.info( "Importing new camera image: " + image );
         imageService.saveCameraImage((CameraImageMediaEntity) image);
      }
      logger.info( "END **************************************************************************************" );

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

      logger.info( "Reading metadata from image file: '" + this + "'..." );
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
            // TODO - clean up this ugly commenting out
            //Message.INFO( "Directory = " + directory.getName() + "; TagName = " + tag.getTagName() + "; TagDescription = " + tag.getDescription() );
            map.put( tag.getTagName(), tag.getDescription() );
         }
         if ( directory.hasErrors() )
         {
            for ( String error : directory.getErrors() )
            {
               logger.error( "Directory = " + directory.getName() + " has error:" + error );
            }
         }
      }
      logger.info( "Reading metadata - DONE" );
      return map;
   }

}

