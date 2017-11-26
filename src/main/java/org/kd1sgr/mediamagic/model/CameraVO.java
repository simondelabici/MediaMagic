package org.kd1sgr.mediamagic.model;


import net.jcip.annotations.Immutable;

import javax.persistence.Embeddable;

@Immutable
@Embeddable
public class CameraVO {

    private final String make;
    private final String model;

    public static final CameraVO UNKOWN = new CameraVO( "Unknown", "Unknown" );
    public static CameraVO of( String make, String model ) { return new CameraVO( make, model ); }

    private CameraVO() { this.make = ""; this.model= ""; }

    public String getMake() {
        return make;
    }

    public String getModel() {
        return model;
    }

    public CameraVO(String make, String model )
    {
        this.make = make;
        this.model = model;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CameraVO cameraVO = (CameraVO) o;

        if (make != null ? !make.equals(cameraVO.make) : cameraVO.make != null) return false;
        return model != null ? model.equals(cameraVO.model) : cameraVO.model == null;
    }

    @Override
    public int hashCode() {
        int result = make != null ? make.hashCode() : 0;
        result = 31 * result + (model != null ? model.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "CameraVO{" +
                "make='" + make + '\'' +
                ", model='" + model + '\'' +
                '}';
    }
}
