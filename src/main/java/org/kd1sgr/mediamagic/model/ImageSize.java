package org.kd1sgr.mediamagic.model;

import org.apache.commons.lang3.Validate;

public enum ImageSize {
    SIZE_DEFAULT("DEFAULT"),
    SIZE_RAW("RAW"),
    SIZE_FULL("FULL"),
    SIZE_SLIDE("SLIDE"),
    SIZE_THUMBNAIL("THUMBNAIL");

    private final String shortName;

    ImageSize( String shortName )
    {
        this.shortName = shortName;
    }

    public static boolean isKnownValue( String valueCandidate )
    {
        for ( ImageSize value : ImageSize.values() )
        {
            if ( value.getShortName().equals( valueCandidate ) )
            {
                return true;
            }
        }
        return false;
    }

    public static ImageSize instanceOf( String target )
    {
        Validate.notNull( target );
        Validate.notBlank( target );

        for ( ImageSize value : ImageSize.values() )
        {
            if ( value.getShortName().equals( target ) )
            {
                return value;
            }
        }
        throw new IllegalArgumentException( "Invalid argument : '" + target + "'" );
    }

    private String getShortName()
    {
        return this.shortName;
    }
}