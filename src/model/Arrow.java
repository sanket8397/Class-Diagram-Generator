package model;

/**
 * LineDecorator to show association
 */
public class Arrow extends LineDecorator {
    public Arrow(Connection connection) {
        super(connection);
        setType(ConnectionTypes.ARROW.ordinal());
    }
}
