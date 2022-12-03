package model;

import view.ClassBox;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Generate code from the class box and connection data in class source
 */
public class CodeGenerator {

    List<ClassBox> classBoxes;
    List<Connection> connections;
    Map<ClassBox, List<Connection>> adjacencyMap;
    private final String TAB = "    ";

    /**
     * Refresh existing class attributes (clear them)
     * Generate an adjacency map from the class box and connection data
     * to represent the diagram in the form of a graph
     * Go through the data, build classes, inheritances,
     * compositions and associations in order
     * @return generated code string
     */
    public String generateCode() {
        refresh();
        generateAdjacencyMap();
        final String RESIZE_DELAY = "                                                  \n";
        StringBuilder codeBuilder = new StringBuilder(RESIZE_DELAY);

        for (ClassBox classBox : classBoxes) {
            List<Connection> adjacentConnections = getAdjacentConnections(classBox);
            List<String> extensionList = getExtensionList(adjacentConnections);
            String extensions = getExtensions(extensionList);
            List<String> compositionList = getCompositionList(adjacentConnections);
            String compositions = getCompositions(compositionList);
            List<String> associationList = getAssociationList(adjacentConnections);
            String associations = getAssociations(associationList);

            codeBuilder.append("class ");
            codeBuilder.append(classBox.getClassName()).append(extensions).append(" {\n");
            codeBuilder.append(compositions);
            codeBuilder.append(associations);
            codeBuilder.append("}\n\n");
        }

        String generatedCode = codeBuilder.toString();
        ClassSource.getInstance().setGeneratedCode(generatedCode);
        return generatedCode;
    }

    /**
     * Reset existing class attributes
     */
    private void refresh() {
        ClassSource classSource = ClassSource.getInstance();
        classBoxes = classSource.getClassBoxes();
        connections = classSource.getConnections();
        adjacencyMap = new HashMap<>();
    }

    /**
     * Generate an adjacency map from the class box and connection data
     * to represent the diagram in the form of a graph
     */
    private void generateAdjacencyMap() {
        for (Connection connection : connections) {
            ClassBox fromClass = connection.getFromClass();

            if (!adjacencyMap.containsKey(fromClass)) {
                adjacencyMap.put(fromClass, new ArrayList<>());
            }

            adjacencyMap.get(fromClass).add(connection);
        }
    }

    /**
     * Get connections that are adjacent to the class box provided
     * @param classBox to which adjacent connections are to be found
     * @return the list of all adjacent connections
     */
    private List<Connection> getAdjacentConnections(ClassBox classBox) {
        List<Connection> adjacentConnections = new ArrayList<>();

        if (adjacencyMap.containsKey(classBox)) {
            adjacentConnections = adjacencyMap.get(classBox);
        }

        return adjacentConnections;
    }

    /**
     * Get the list of all the extensions - inheritances in the adjacent connections
     * @param adjacentConnections of a class box
     * @return list of extension names
     */
    private List<String> getExtensionList(List<Connection> adjacentConnections) {
        List<String> extensionList = new ArrayList<>();

        for (Connection connection : adjacentConnections) {
            if (connection instanceof Triangle) {
                extensionList.add(connection.getToClass().getClassName());
            }
        }

        return extensionList;
    }

    /**
     * Return a concatenated code ready string of all extensions
     * of the class
     * @param extensionList of a class
     * @return concatenated code ready string of all extensions
     */
    private String getExtensions(List<String> extensionList) {
        String extensions = "";

        if (!extensionList.isEmpty()) {
            StringBuilder extensionBuilder = new StringBuilder(" extends ");

            for (String extension : extensionList) {
                extensionBuilder.append(extension);
                extensionBuilder.append(", ");
            }

            extensionBuilder.setLength(extensionBuilder.length() - 2);
            extensions = extensionBuilder.toString();
        }

        return extensions;
    }

    /**
     * Get the list of all the compositions in the adjacent connections
     * @param adjacentConnections of the class
     * @return list of composition names
     */
    private List<String> getCompositionList(List<Connection> adjacentConnections) {
        List<String> compositionList = new ArrayList<>();

        for (Connection connection : adjacentConnections) {
            if (connection instanceof Diamond) {
                compositionList.add(connection.getToClass().getClassName());
            }
        }

        return compositionList;
    }

    /**
     * Return a concatenated code ready string of all compositions
     * of the class
     * @param compositionList of a class
     * @return concatenated code ready string of all compositions
     */
    private String getCompositions(List<String> compositionList) {
        StringBuilder compositionBuilder = new StringBuilder();

        for (String composition : compositionList) {
            compositionBuilder.append(TAB).append(composition).append("\n");
        }

        return compositionBuilder.toString();
    }

    /**
     * Get the list of all the associations in the adjacent connections
     * @param adjacentConnections of the class
     * @return list of association names
     */
    private List<String> getAssociationList(List<Connection> adjacentConnections) {
        List<String> associationList = new ArrayList<>();

        for (Connection connection : adjacentConnections) {
            if (connection instanceof Arrow) {
                associationList.add(connection.getToClass().getClassName());
            }
        }

        return associationList;
    }

    /**
     * Return a concatenated code ready string of all associations
     * of the class
     * @param associationList of a class
     * @return concatenated code ready string of all associations
     */
    private String getAssociations(List<String> associationList) {
        if (associationList.isEmpty()) {
            return "";
        }

        StringBuilder associationBuilder = new StringBuilder(TAB + "method() {\n");

        for (String association : associationList) {
            associationBuilder.append(TAB + TAB).append(association).append("\n");
        }

        associationBuilder.append(TAB + "}\n");
        return associationBuilder.toString();
    }
}
