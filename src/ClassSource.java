import java.util.ArrayList;
import java.util.List;

public class ClassSource {
    private static List<ClassBox> classBoxes;
    private static List<Connection> connections;

    private ClassSource() {
    }

    public static List<ClassBox> getClassBoxes() {
        if (classBoxes == null) {
            classBoxes = new ArrayList<>();
        }

        return classBoxes;
    }

    public static List<Connection> getConnections() {
        if (connections == null) {
            connections = new ArrayList<>();
        }

        return connections;
    }

    public void setClassSource(List<ClassBox> classSource) {
        this.classBoxes = classSource;
    }

    public void setConnections(List<Connection> connections) {
        this.connections = connections;
    }

}
