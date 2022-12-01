import Model.Connection;

public abstract class LineDecorator extends Connection {
    private final Connection connection;

    public LineDecorator(Connection connection) {
        this.connection = connection;
    }
}
