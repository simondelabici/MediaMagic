package org.kd1sgr.mediamagic.model;

public abstract class ResolutionVO {

    private int x;
    private int y;

    public ResolutionVO() {}

    public ResolutionVO( int x, int y )
    {
        this.x = x;
        this.y = y;
    }

    public int getX()
    {
        return this.x;
    }

    public int getY()
    {
        return this.y;
    }

}
