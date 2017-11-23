package org.kd1sgr.mediamagic.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.File;

public abstract class MediaEntity {

    @Id
    @GeneratedValue
    private Long id;

    private File filename;

    public MediaEntity() {}

    public MediaEntity(File filename )
    {
        this.filename = filename;
    }

    public Long getId()
    {
        return this.id;
    }

    public File getFilename()
    {
        return this.filename;
    }

}
