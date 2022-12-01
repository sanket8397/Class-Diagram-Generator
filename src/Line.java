import Model.Connection;

public class Line extends Connection {
    public Line() {
        setType(ConnectionTypes.LINE.ordinal());
    }
}
