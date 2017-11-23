package org.kd1sgr.mediamagic.model;

import javax.persistence.Entity;

@Entity
public class CameraImageEntity extends ImageEntity {

    private int cameraId;
    private String aperture;
    private String fNo;
    private String shutterSpeed;
    private String zoom;

    public CameraImageEntity() { }

    public CameraImageEntity( int cameraId, String aperture, String fNo, String shutterSpeed, String zoom )
    {
        this.cameraId = cameraId;
        this.aperture = aperture;
        this.fNo = fNo;
        this.shutterSpeed = shutterSpeed;
        this.zoom = zoom;
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


