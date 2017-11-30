package org.kd1sgr.mediamagic.controllers.rest;

import org.kd1sgr.mediamagic.model.CameraImage;
import org.kd1sgr.mediamagic.model.ImageSize;
import org.kd1sgr.mediamagic.model.OrientationVO;
import org.kd1sgr.mediamagic.services.ImageSenderService;
import org.kd1sgr.mediamagic.services.ImageService;
import org.kd1sgr.mediamagic.services.MediaImporterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.CacheControl;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/resource/library/image")
public class LibraryImageResourceController
{
    private final Logger logger = LoggerFactory.getLogger( LibraryImageResourceController.class );

    @Autowired
    ImageSenderService imageSenderService;

    @Autowired
    ImageService imageService;

    @Autowired
    MediaImporterService mediaImporterService;

    @RequestMapping(value = "/{imageId}", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE )
    public ResponseEntity doGet(@PathVariable("imageId") String imageId,
                                @RequestParam( value = "size", required = false ) String sizeOrNull,
                                @RequestParam( value = "orientation", required = false) String orientationOrNull,
                                HttpServletResponse response ) throws IOException
    {
       final ImageSize imageSize = ( sizeOrNull == null ) || ( ! ImageSize.isKnownValue( sizeOrNull )  ) ?
                 ImageSize.SIZE_DEFAULT : ImageSize.instanceOf( sizeOrNull );

       final OrientationVO orientationVO = ( orientationOrNull == null ) || ( ! OrientationVO.isKnownValue( orientationOrNull ) )?
                 OrientationVO.ROTATE_NONE : OrientationVO.valueOf( orientationOrNull );

       logger.info( "doGet called with path imageId= '" + imageId + "', size='" + imageSize + "', orientation='" + orientationVO + "'" );

       Optional<CameraImage> opt = imageService.getCameraImage( imageId );
       if ( opt.isPresent() )
       {
          final CameraImage image = opt.get();
          final OutputStream os = response.getOutputStream();

          switch ( imageSize )
          {
             case SIZE_RAW:
                imageSenderService.sendRawImageDataToOutputStream( image, os );
                break;

             case SIZE_SLIDE:
                imageSenderService.sendSlideshowImageDataToOutputStream( image, os );
                break;

             case SIZE_THUMBNAIL:
                imageSenderService.sendThumbnailImageDataToOutputStream( image, os );
                break;

             case SIZE_DEFAULT:
             case SIZE_FULL:
                imageSenderService.sendFullSizeImageDataToOutputStream( image, os );
                break;
          }
       }

       // Set the Max Age header to cache for one year.
       final CacheControl cc = CacheControl.maxAge( 365, TimeUnit.DAYS );
       return ResponseEntity.ok().cacheControl( cc ).build();
   }

   @RequestMapping(value = "/{imageId}", method = RequestMethod.PUT, produces = MediaType.TEXT_PLAIN_VALUE )
   public ResponseEntity doPut(@PathVariable("imageId") String imageId,
                               @RequestParam( value = "orientation", required = false) String orientationOrNull ) throws IOException
   {
      final OrientationVO orientationVO = ( orientationOrNull == null ) ||
              ! OrientationVO.isKnownValue( orientationOrNull ) ?
              OrientationVO.ROTATE_NONE : OrientationVO.valueOf( orientationOrNull );

      logger.info( "doPut called with path imageId= '" + imageId + "', orientation='" + orientationVO + "'" );

      final Optional<CameraImage> opt = imageService.getCameraImage( imageId );
      if ( opt.isPresent() )
      {
         CameraImage cameraImage = opt.get();
         cameraImage.setOrientationVO( orientationVO );
         imageService.saveCameraImage( cameraImage );
      }
      logger.info( "doPut saved updated orientation for image: '" + imageId + "'" );
      return ResponseEntity.ok().build();
  }

}

