package model;

import view.ClassBox;

import java.util.ArrayList;
import java.util.List;

public class ReverseCodeGenerator {

    private List<ClassBox> classBoxes = new ArrayList<>();
    private List<Connection> connections = new ArrayList<>();

    public void parseCode() {
        String code = ClassSource.getInstance().getGeneratedCode();
        System.out.println("Generated code: \n" + code);

        try {
            String[] codeSplit = code.split("\n");
            int cLen = codeSplit.length;

            for (int i = 0; i < cLen; i++) {
                String line = codeSplit[i].trim();

                if (line.startsWith("class")) {
                    String[] lineSplit = line.split("\\s+");
                    ClassBox classBox = createClassBox(lineSplit[1]);

                    if (lineSplit[2].equals("extends")) {
                        createInheritances(lineSplit, 3, classBox);
                    }

                    line = codeSplit[++i].trim();

                    while (!line.startsWith("method()") && !line.startsWith("}")) {
                        createAggregation(line, classBox);
                        line = codeSplit[++i].trim();
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("Error in parsing code");
        }

        updateClassSource(classBoxes, connections);
    }

    private ClassBox createClassBox(String className) {
        className = className.replace(",", "");
        System.out.println("Created class: " + className);
        ClassBox classBox = new ClassBox(className, 100, 100);
        classBoxes.add(classBox);
        return classBox;
    }

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

    private ClassBox classExists(String className) {
        for (ClassBox classBox : classBoxes) {
            if (classBox.getClassName().equals(className)) {
                return classBox;
            }
        }

        return null;
    }

    private void createAggregation(String toClassName, ClassBox fromClass) {
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

    private void updateClassSource(List<ClassBox> classBoxes, List<Connection> connections) {
        ClassSource classSource = ClassSource.getInstance();
        classSource.clearSource();

        for (ClassBox classBox : classBoxes) {
            classSource.addClassBox(classBox);
        }

        for (Connection connection : connections) {
            classSource.addConnection(connection);
        }

        printSource();
    }

    //TODO DELETE
    private void printSource() {
        System.out.println("Source: ---------------");
        for (ClassBox classBox : classBoxes) {
            System.out.println(classBox.getClassName());
        }
        for (Connection connection : connections) {
            System.out.println(connection.getFromClass().getClassName() + "->" +
                    connection.getToClass().getClassName() + " | " +
                    connection.getType());
        }
    }
}
