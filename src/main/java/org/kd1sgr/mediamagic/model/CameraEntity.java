package org.kd1sgr.mediamagic.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class CameraEntity extends MediaEntity {

    @Id
    @GeneratedValue
    private int id;

    private String make;
    private String model;

    public CameraEntity() {}

    public CameraEntity( int id, String make, String model )
    {
        this.id = id;
        this.make = make;
        this.model = model;
    }

}
