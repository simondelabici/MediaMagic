package org.kd1sgr.mediamagic.model;

import javax.persistence.Entity;
import java.io.File;

@Entity
public class CameraImageMediaEntity extends ImageMediaEntity {

    private CameraVO camera;
    private String aperture;
    private String fNo;
    private String shutterSpeed;
    private String zoom;

    public CameraImageMediaEntity() { }

    @Override
    public String toString() {
        return "CameraImageMediaEntity{" +
                "super=" + super.toString() +
                ", camera=" + camera +
                ", aperture='" + aperture + '\'' +
                ", fNo='" + fNo + '\'' +
                ", shutterSpeed='" + shutterSpeed + '\'' +
                ", zoom='" + zoom + '\'' +
                '}';
    }

    public CameraImageMediaEntity( File filename,
                                   ResolutionVO resolutionVO,
                                   OrientationVO orientationVO,
                                   CameraVO camera,
                                   String aperture, String fNo, String shutterSpeed, String zoom )
    {
        super( filename, resolutionVO, orientationVO );
        this.camera = camera;
        this.aperture = aperture;
        this.fNo = fNo;
        this.shutterSpeed = shutterSpeed;
        this.zoom = zoom;
    }

    public CameraVO getCamera() {
        return camera;
    }

    public String getAperture() {
        return aperture;
    }

    public String getfNo() {
        return fNo;
    }

    public String getShutterSpeed() {
        return shutterSpeed;
    }

    public String getZoom() {
        return zoom;
    }
}


