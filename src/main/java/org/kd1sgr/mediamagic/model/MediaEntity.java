package org.kd1sgr.mediamagic.model;

import net.jcip.annotations.Immutable;
import org.kd1sgr.mediamagic.services.MediaImporterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.*;
import java.io.ByteArrayOutputStream;
import java.io.File;

@MappedSuperclass
@Immutable
public abstract class MediaEntity {

    private static final Logger logger = LoggerFactory.getLogger( MediaEntity.class );

    @Id
    @GeneratedValue
    private Long id;

    private final File filename;

    public MediaEntity() { this.filename = null; }

    public MediaEntity(File filename )
    {
        logger.info( "Importing image: " + filename );
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

    @Override
    public String toString() {
        return "MediaEntity{" +
                "id=" + id +
                ", filename=" + filename +
                '}';
    }


}
