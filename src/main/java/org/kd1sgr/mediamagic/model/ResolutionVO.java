package org.kd1sgr.mediamagic.model;

import net.jcip.annotations.Immutable;

import javax.persistence.Embeddable;

@Immutable
@Embeddable
public class ResolutionVO {

    private final String x;
    private final String y;

    private ResolutionVO() { this.x = ""; this.y = ""; }

    public static final ResolutionVO UNKOWN = new ResolutionVO( "Unknown", "Unknown" );

    private ResolutionVO(String x, String y )
    {
        this.x = x;
        this.y = y;
    }

    public static ResolutionVO of( String x, String y ) { return new ResolutionVO( x, y ); }

    public String getX()
    {
        return this.x;
    }
    public String getY()
    {
        return this.y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ResolutionVO that = (ResolutionVO) o;

        if (x != null ? !x.equals(that.x) : that.x != null) return false;
        return y != null ? y.equals(that.y) : that.y == null;
    }

    @Override
    public int hashCode() {
        int result = x != null ? x.hashCode() : 0;
        result = 31 * result + (y != null ? y.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return x.replaceAll("[^0-9]", "") + " x " + y.replaceAll("[^0-9]", "");
    }
}
