package org.kd1sgr.mediamagic.model;

public enum OrientationVO {
    ROTATE_NONE,
    ROTATE_CLOCKWISE_90,
    ROTATE_ANTICLOCKWISE_90,
    ROTATE_180;

    public static boolean isKnownValue( String valueCandidate )
    {
        for ( ImageSize value : ImageSize.values() )
        {
            if ( value.name().equals( valueCandidate ) )
            {
                return true;
            }
        }
        return false;
    }

}
