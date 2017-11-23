package org.kd1sgr.mediamagic.model;

import javax.persistence.Entity;

import static org.kd1sgr.mediamagic.model.OrientationVO.ROTATE_NONE;

public abstract class ImageEntity extends MediaEntity {

    private OrientationVO orientationVO;
    private ResolutionVO resolutionVO;

    public ImageEntity() {}

    public ImageEntity( ResolutionVO resolutionVO )
    {
        this( resolutionVO, ROTATE_NONE );
    }

    public ImageEntity( ResolutionVO resolutionVO, OrientationVO orientationVO )
    {
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
}
