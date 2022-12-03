package model;

/**
 * Basic line, will be decorated by triangle, diamond and arrow
 */
public class Line extends Connection {
    public Line() {
        setType(ConnectionTypes.LINE.ordinal());
    }
}
