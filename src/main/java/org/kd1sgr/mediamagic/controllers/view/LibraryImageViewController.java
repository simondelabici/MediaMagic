package org.kd1sgr.mediamagic.controllers.view;

import net.jcip.annotations.Immutable;
import org.apache.commons.lang3.Validate;
import org.kd1sgr.mediamagic.model.CameraImage;
import org.kd1sgr.mediamagic.model.OrientationVO;
import org.kd1sgr.mediamagic.services.ImageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
@RequestMapping("/view/library/image")
@Immutable
public class LibraryImageViewController
{
   private final Logger logger = LoggerFactory.getLogger( LibraryImageViewController.class );

   @Autowired
   private ImageService imageService;

   @RequestMapping(value = "/by_date/{fourDigitYear}/{twoDigitMonth}/{index}", produces = MediaType.TEXT_HTML_VALUE )
   public String doGetByDate(@PathVariable( value = "fourDigitYear" ) int year,
                             @PathVariable( value = "twoDigitMonth" ) int month,
                             @PathVariable( value = "index") int index,
                             Model viewModel )
   {
      Validate.isTrue( year <= 9999, "Not a valid year: '%04d'", year );
      Validate.isTrue( month <= 12, "Not a valid month: '%02d'", month );
      Validate.isTrue( index >= 0, "image index was negative: ", index );

      logger.info( "Returning library image at index: " + index  + " from year/month: '" + year + "-" + month + "'" );
      final YearMonth yearMonth = YearMonth.of( year, month );
      final String banner = yearMonth.format( DateTimeFormatter.ofPattern( "MMMM yyyy" ) );
      viewModel.addAttribute( "banner", banner );
      viewModel.addAttribute( "year", year );
      viewModel.addAttribute( "month", month );
      viewModel.addAttribute( "orientation", OrientationVO.ROTATE_CLOCKWISE_90.toString() );

      List<CameraImage> list = imageService.findCameraImagesByYearAndMonth( yearMonth );

      if ( index > 0 )
      {
         final int previousImageIndex = index - 1;
         final CameraImage previousImage = list.get(previousImageIndex);
         viewModel.addAttribute("previousImageIndex", previousImageIndex);
         viewModel.addAttribute("previousImageId", previousImage.getId());
      }

      if ( index < list.size() )
      {
         final CameraImage currentImage = list.get(index);
         viewModel.addAttribute("currentImageId", currentImage.getId());
         viewModel.addAttribute("currentImageFilename", currentImage.getFilename().toString());
         viewModel.addAttribute("cameraMaker", currentImage.getCamera().getMake());
         viewModel.addAttribute("cameraModel", currentImage.getCamera().getModel());
         viewModel.addAttribute("imageResolution", currentImage.getResolutionVO().toString());
      }
      // Add the next image information (if the current image is not the last image in the sequence)
      if ( index < list.size() - 1 )
      {
         final int nextImageIndex = index + 1;
         final CameraImage nextImage = list.get( nextImageIndex );
         viewModel.addAttribute("nextImageIndex", nextImageIndex);
         viewModel.addAttribute("nextImageId", nextImage.getId());
      }

      return "LibraryImageView";
   }

} 

