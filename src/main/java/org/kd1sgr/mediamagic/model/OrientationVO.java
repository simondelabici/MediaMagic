package org.kd1sgr.mediamagic.model;

import org.apache.commons.lang3.Validate;

public enum OrientationVO {

    ROTATE_NONE( 0 ),
    ROTATE_CLOCKWISE_90( Math.PI/2),
    ROTATE_ANTICLOCKWISE_90( -Math.PI/2 ),
    ROTATE_180( Math.PI );

    private final double radians;

    OrientationVO( double radians )
    {
        this.radians = radians;
    }

    public static boolean isKnownValue( String valueCandidate )
    {
        Validate.notNull(valueCandidate);
        Validate.notBlank(valueCandidate);

        for ( OrientationVO values : OrientationVO.values() )
        {
            if ( values.toString().equals( valueCandidate ) )
            {
                return true;
            }
        }
        return false;
    }

    public double getDegrees()
    {
        return Math.toDegrees( radians );
    }

}
