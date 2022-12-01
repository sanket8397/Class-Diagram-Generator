import Model.Connection;

public class Diamond extends LineDecorator {
    public Diamond(Connection connection) {
        super(connection);
        setType(ConnectionTypes.DIAMOND.ordinal());
    }
}
