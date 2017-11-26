package org.kd1sgr.mediamagic.model;

import net.jcip.annotations.Immutable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.File;

@MappedSuperclass
@Immutable
public abstract class Media {

    private static final Logger logger = LoggerFactory.getLogger( Media.class );

    @Id
    @GeneratedValue
    private Long id;

    private final String filename;

    public Media() { this.filename = null; }

    public Media( File filename )
    {
        logger.info( "Creating image: '" + filename + "'");
        this.filename = filename.toString();
    }

    public Long getId()
    {
        return this.id;
    }

    public File getFilename()
    {
        return new File( this.filename );
    }

    @Override
    public String toString() {
        return "Media{" +
                "id=" + id +
                ", filename=" + filename +
                '}';
    }


}
