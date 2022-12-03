package model;

/**
 * LineDecorator to show inheritance
 */
public class Triangle extends LineDecorator {
    public Triangle(Connection connection) {
        super(connection);
        setType(ConnectionTypes.TRIANGLE.ordinal());
    }
}
