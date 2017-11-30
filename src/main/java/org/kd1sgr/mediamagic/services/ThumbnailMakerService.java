package org.kd1sgr.mediamagic.services;

import net.coobird.thumbnailator.Thumbnails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;

@Service
public class ThumbnailMakerService {

   private static final Logger logger = LoggerFactory.getLogger( ThumbnailMakerService.class );

   public ThumbnailMakerService() {}

   private Path targetDirectory;

   public ThumbnailMakerService( Path targetDirectory )
   {
      this.targetDirectory = targetDirectory;
   }

   public File makeThumb( File inputFile ) throws IOException
   {
      File outputFile = new File( changeExtension ( inputFile.toString(), ".thumb.jpg" ) );
      final int thumbWidth = 200;

      File targetFile = targetDirectory.resolve( outputFile.toPath() ).toFile();
      if ( ! targetFile.exists() )
      {
         compressImage(inputFile, outputFile, thumbWidth);
      }
      return outputFile;
   }

   public File makeSlide( File inputFile ) throws IOException
   {
      File outputFile = new File( changeExtension ( inputFile.toString(), ".slide.jpg" ) );
      final int slideWidth = 1900;
      File targetFile = targetDirectory.resolve( outputFile.toPath() ).toFile();
      if ( ! targetFile.exists() )
      {
         compressImage(inputFile, outputFile, slideWidth  );
      }
      return outputFile;
   }

   /**
    * Compresses the image data in the specified input file to the given width and
    * saves it to an output file in tha target directory in JPEG format.
    *
    * @param inputFile the input file
    * @param outputFile the output file
    * @param imageWidth the required width
    *
    * @throws IOException if anything unexpected happens in the translation process.
    */
   private void compressImage( File inputFile, File outputFile, int imageWidth ) throws IOException
   {
      logger.info( "compressing: '" + inputFile + "' to: '" + outputFile  + "'. Width is: " + imageWidth);

      File targetFile = targetDirectory.resolve( outputFile.toPath() ).toFile();

      Thumbnails.of( inputFile )
              .useExifOrientation( false )
              .width( imageWidth )
              .outputFormat( "jpg" )
              .toFile( targetFile );
   }

   /**
    * Changes an existing filename to a new filename with different extension.
    * <p>
    * example: x = changeExtension( "data.txt", ".java" )
    * will assign "data.java" to x.
    *
    * @param originalName the filename to be translated
    * @param newExtension the new externsion eg .jpg, .ext
    *
    * @return the transformed string.
    */
   private static String changeExtension( String originalName, String newExtension )
   {
      int lastDot = originalName.lastIndexOf( "." );
      if ( lastDot != -1 )
      {
         return originalName.substring( 0, lastDot ) + newExtension;
      }
      else
      {
         return originalName + newExtension;
      }
   }

}

