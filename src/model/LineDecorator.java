package model;

/**
 * Decorator used to decorate a basic Line - represents a connection
 */
public abstract class LineDecorator extends Connection {
    private final Connection connection;

    public LineDecorator(Connection connection) {
        this.connection = connection;
    }
}
