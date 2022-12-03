package model;

/**
 * Line Decorator to represent composition
 */
public class Diamond extends LineDecorator {
    public Diamond(Connection connection) {
        super(connection);
        setType(ConnectionTypes.DIAMOND.ordinal());
    }
}
