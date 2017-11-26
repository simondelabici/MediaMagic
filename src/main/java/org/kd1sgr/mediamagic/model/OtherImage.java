package org.kd1sgr.mediamagic.model;

import javax.persistence.Entity;
import java.io.File;

@Entity
public class OtherImage extends Image {

    public OtherImage() { }

    public OtherImage( File filename )
    {
       this( filename, ResolutionVO.UNKOWN, OrientationVO.ROTATE_NONE );
    }

    public OtherImage(File filename, ResolutionVO resolutionVO, OrientationVO orientationVO )
    {
       super( filename, resolutionVO, orientationVO);
    }

}


