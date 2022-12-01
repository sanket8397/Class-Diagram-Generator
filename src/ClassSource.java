import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class ClassSource extends Observable {
    private static ClassSource instance;
    private List<ClassBox> classBoxes;
    private List<Connection> connections;

    private ClassSource() {
        classBoxes = new ArrayList<>();
        connections = new ArrayList<>();
    }

    public static ClassSource getInstance() {
        if (instance == null) {
            instance = new ClassSource();
        }
        return instance;
    }

    public List<ClassBox> getClassBoxes() {
        return classBoxes;
    }

    public List<Connection> getConnections() {
        return connections;
    }

    public void addClassBox(ClassBox classBox) {
        classBoxes.add(classBox);
        setChanged();
        notifyObservers();
    }

    public void addConnection(Connection connection) {
        connections.add(connection);
        setChanged();
        notifyObservers();
    }
}