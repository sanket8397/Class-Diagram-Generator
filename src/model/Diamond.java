package model;

import model.ConnectionTypes;

public class Diamond extends LineDecorator {
    public Diamond(Connection connection) {
        super(connection);
        setType(ConnectionTypes.DIAMOND.ordinal());
    }
}
