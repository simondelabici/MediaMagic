package org.kd1sgr.mediamagic.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.File;

@Entity
public class OtherImageEntity extends ImageMediaEntity {

    public OtherImageEntity() { }

    public OtherImageEntity(File filename, ResolutionVO resolutionVO, OrientationVO orientationVO )
    {
       super( filename, resolutionVO, orientationVO);
    }

}


