package model;

import view.ClassBox;

import java.util.ArrayList;
import java.util.List;

/**
 * Class used to generate class diagrams based on the current
 * or user written code in the code panel
 * It accesses the blackboard class source's generated code,
 * parses it to generate the class boxes and connections data
 * which in turn is used to generate class diagram
 */
public class ReverseCodeGenerator {

    private List<ClassBox> classBoxes = new ArrayList<>();
    private List<Connection> connections = new ArrayList<>();
    private int placementCounter = 0;

    /**
     * Retrieves the code string from class source,
     * parses it to generate the class boxes and connections
     * Sequentially reads lines to get class name,
     * extensions, compositions, associations
     * Generates new classes and connections wherever necessary
     */
    public void parseCode() {
        ClassSource classSource = ClassSource.getInstance();
        String code = classSource.getGeneratedCode();

        try {
            String[] codeSplit = code.split("\n");
            int cLen = codeSplit.length;

            for (int i = 0; i < cLen; i++) {
                String line = codeSplit[i].trim();

                if (line.startsWith("class")) {
                    String[] lineSplit = line.split("\\s+");
                    ClassBox classBox = classExists(lineSplit[1]);

                    if (classBox == null) {
                        classBox = createClassBox(lineSplit[1]);
                    }

                    if (lineSplit[2].equals("extends")) {
                        createInheritances(lineSplit, 3, classBox);
                    }

                    line = codeSplit[++i].trim();

                    while (!line.startsWith("method") && !line.startsWith("}")) {
                        createComposition(line, classBox);
                        line = codeSplit[++i].trim();
                    }

                    if (line.startsWith("method")) {
                        line = codeSplit[++i].trim();

                        while (!line.startsWith("}")) {
                            createAssociation(line, classBox);
                            line = codeSplit[++i].trim();
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Error in parsing code");
            e.printStackTrace();
        }

        updateClassSource(classBoxes, connections);
    }

    /**
     * Create a new class box with the given name
     * @param className of new class
     * @return created class box
     */
    private ClassBox createClassBox(String className) {
        className = className.replace(",", "");
        ClassBox classBox = new ClassBox(className, 100, 100 + placementCounter);
        placementCounter += 40;
        classBoxes.add(classBox);
        return classBox;
    }

    /**
     * Parse the line[] provided starting from the start idx
     * Get all the inheritances, create new classes if necessary
     * Add the connections
     * @param lineSplit where inheritances are provided
     * @param startIdx from where parsing should start
     * @param fromClass class from which connection starts
     */
    private void createInheritances(String[] lineSplit, int startIdx, ClassBox fromClass) {
        for (int i = startIdx; i < lineSplit.length; i++) {
            String parent = lineSplit[i];
            if (parent.equals("{")) {
                break;
            }
            ClassBox parentClassBox = classExists(parent);

            if (parentClassBox == null) {
                parentClassBox = createClassBox(parent);
            }

            Line line = new Line();
            Triangle triangle = new Triangle(line);
            triangle.setFromClass(fromClass);
            triangle.setToClass(parentClassBox);
            connections.add(triangle);
        }
    }

    /**
     * Check if class exists in list of classes in the attributes,
     * if not check in the blackboard class source
     * @param className to be searched
     * @return found class box or null if not present
     */
    private ClassBox classExists(String className) {
        for (ClassBox classBox : classBoxes) {
            if (classBox.getClassName().equals(className)) {
                return classBox;
            }
        }

        for (ClassBox classBox : ClassSource.getInstance().getClassBoxes()) {
            if (classBox.getClassName().equals(className)) {
                classBoxes.add(classBox);
                return classBox;
            }
        }

        return null;
    }

    /**
     * Create composition connection from class given to class name provided
     * @param toClassName name of to class
     * @param fromClass class box of from class
     */
    private void createComposition(String toClassName, ClassBox fromClass) {
        Line line = new Line();
        Diamond diamond = new Diamond(line);
        diamond.setFromClass(fromClass);
        ClassBox toClass = classExists(toClassName);

        if (toClass == null) {
            toClass = createClassBox(toClassName);
        }

        diamond.setToClass(toClass);
        connections.add(diamond);
    }

    /**
     * Create association connection from class given to class name provided
     * @param toClassName name of to class
     * @param fromClass class box of from class
     */
    private void createAssociation(String toClassName, ClassBox fromClass) {
        Line line = new Line();
        Arrow arrow = new Arrow(line);
        arrow.setFromClass(fromClass);
        ClassBox toClass = classExists(toClassName);

        if (toClass == null) {
            toClass = createClassBox(toClassName);
        }

        arrow.setToClass(toClass);
        connections.add(arrow);
    }

    /**
     * Update the blackboard class source based on generated data
     * @param classBoxes to be updated
     * @param connections to be updated
     */
    private void updateClassSource(List<ClassBox> classBoxes, List<Connection> connections) {
        ClassSource classSource = ClassSource.getInstance();
        classSource.clearSource();

        for (ClassBox classBox : classBoxes) {
            classSource.addClassBox(classBox);
        }

        for (Connection connection : connections) {
            classSource.addConnection(connection);
        }
    }
}
