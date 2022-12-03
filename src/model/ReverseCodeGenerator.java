package model;

import view.ClassBox;

import java.util.ArrayList;
import java.util.List;

public class ReverseCodeGenerator {

    private List<ClassBox> classBoxes = new ArrayList<>();
    private List<Connection> connections = new ArrayList<>();
    private int placementCounter = 0;

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

    private ClassBox createClassBox(String className) {
        className = className.replace(",", "");
        ClassBox classBox = new ClassBox(className, 100, 100 + placementCounter);
        placementCounter += 40;
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

        for (ClassBox classBox : ClassSource.getInstance().getClassBoxes()) {
            if (classBox.getClassName().equals(className)) {
                classBoxes.add(classBox);
                return classBox;
            }
        }

        return null;
    }

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
