package org.kd1sgr.mediamagic.model;

import javax.persistence.MappedSuperclass;
import java.io.File;

@MappedSuperclass
public abstract class Image extends Media {

    private OrientationVO orientationVO;
    private final ResolutionVO resolutionVO;

    public Image()
    {
        super( new File("") );
        this.orientationVO = OrientationVO.ROTATE_NONE;
        this.resolutionVO = ResolutionVO.of ("0", "0" );
    }

    public Image(File filename, ResolutionVO resolutionVO, OrientationVO orientationVO )
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

    public void setOrientationVO( OrientationVO newOrientationVO )
    {
        this.orientationVO = newOrientationVO;
    }

    @Override
    public String toString() {
        return "Image{" +
                "super=" + super.toString() +
                ", orientationVO=" + orientationVO +
                ", resolutionVO=" + resolutionVO +
                '}';
    }
}
