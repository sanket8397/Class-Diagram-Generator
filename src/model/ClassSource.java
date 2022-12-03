package model;

import view.ClassBox;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * Blackboard Singleton to hold all the data of the project
 * This is also an observable that is being observed by different classes
 */
public class ClassSource extends Observable {
    private static ClassSource instance;
    private List<ClassBox> classBoxes;
    private List<Connection> connections;
    private String generatedCode;

    private ClassSource() {
        classBoxes = new ArrayList<>();
        connections = new ArrayList<>();
    }

    /**
     * Get the singleton ClassSource instance
     * @return singleton ClassSource instance
     */
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

    public String getGeneratedCode() {
        return generatedCode;
    }

    /**
     * Add the new class box to the classBoxes list,
     * notify observers about the change
     * @param classBox to be added
     */
    public void addClassBox(ClassBox classBox) {
        classBoxes.add(classBox);
        setChanged();
        notifyObservers();
    }

    /**
     * Add the new connection to the connections list,
     * notify observers about the change
     * @param connection to be added
     */
    public void addConnection(Connection connection) {
        connections.add(connection);
        setChanged();
        notifyObservers();
    }

    public void setGeneratedCode(String generatedCode) {
        this.generatedCode = generatedCode;
    }

    /**
     * Clear all data in the source, used for a reset
     * Notify all observers
     */
    public void clearSource() {
        classBoxes.clear();
        connections.clear();
        setChanged();
        notifyObservers();
    }

    /**
     * Notify observers when a change is done after getting the values
     * and updating by reference
     */
    public void updatedByReference() {
        setChanged();
        notifyObservers();
    }
}
