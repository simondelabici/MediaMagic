package org.kd1sgr.mediamagic.model;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.MappedSuperclass;

import java.io.File;

import static org.kd1sgr.mediamagic.model.OrientationVO.ROTATE_NONE;

@MappedSuperclass
public abstract class ImageMediaEntity extends MediaEntity {

    private final OrientationVO orientationVO;
    private final ResolutionVO resolutionVO;

    public ImageMediaEntity()
    {
        super( new File("") );
        this.orientationVO = OrientationVO.ROTATE_NONE;
        this.resolutionVO = ResolutionVO.of ("0", "0" );
    }

    public ImageMediaEntity(File filename, ResolutionVO resolutionVO, OrientationVO orientationVO )
    {
        super( filename );
        this.resolutionVO = resolutionVO;
        this.orientationVO = orientationVO;

    }

    public ResolutionVO getResolutionVO()
    {
        return this.resolutionVO;
    }

    public OrientationVO getOrientationVO() {
        return orientationVO;
    }

    @Override
    public String toString() {
        return "ImageMediaEntity{" +
                "super=" + super.toString() +
                ", orientationVO=" + orientationVO +
                ", resolutionVO=" + resolutionVO +
                '}';
    }
}
