package model;

import view.ClassBox;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

public class ClassSource extends Observable {
    private static ClassSource instance;
    private List<ClassBox> classBoxes;
    private List<Connection> connections;
    private String generatedCode;

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

    public String getGeneratedCode() {
        return generatedCode;
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

    public void setGeneratedCode(String generatedCode) {
        this.generatedCode = generatedCode;
    }

    public void clearSource() {
        classBoxes.clear();
        connections.clear();
        setChanged();
        notifyObservers();
    }

    public void updatedByReference() {
        setChanged();
        notifyObservers();
    }
}
