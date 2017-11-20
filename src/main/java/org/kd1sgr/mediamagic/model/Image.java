package org.kd1sgr.mediamagic.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.File;

@Entity
public class Image {

    @Id
    @GeneratedValue
    private Long id;

    private File filename;

    public Image() {}

    public Image( File filename )
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
