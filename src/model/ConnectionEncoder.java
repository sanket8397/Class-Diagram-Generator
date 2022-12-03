package model;

import java.util.List;

/**
 * Encoder to encode connection list from class source
 */
public class ConnectionEncoder implements Encoder {
    @Override
    public String encode() {
        List<Connection> connections = ClassSource.getInstance().getConnections();
        StringBuilder connectionString = new StringBuilder();

        for (Connection connection: connections){
            String currConnectionString = "1,";

            currConnectionString += connection.getType();
            currConnectionString += ",";

            currConnectionString += connection.getFromClass().getClassName();
            currConnectionString += ",";

            currConnectionString += connection.getToClass().getClassName();
            currConnectionString += "\n";

            connectionString.append(currConnectionString);
        }

        return connectionString.toString();
    }
}
