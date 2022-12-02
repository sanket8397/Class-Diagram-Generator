package model;

import model.ConnectionTypes;
import model.LineDecorator;

public class Triangle extends LineDecorator {
    public Triangle(Connection connection) {
        super(connection);
        setType(ConnectionTypes.TRIANGLE.ordinal());
    }
}
