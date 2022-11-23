import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CodeGenerator {

    List<ClassBox> classBoxes;
    List<Connection> connections;
    Map<ClassBox, List<Connection>> adjacencyMap;

    public String generateCode() {
        refresh();
        generateAdjacencyMap();
        StringBuilder codeBuilder = new StringBuilder();

        for (ClassBox classBox : classBoxes) {
            List<Connection> adjacentConnections = getAdjacentConnections(classBox);
            List<String> extensionList = getExtensionList(adjacentConnections);
            String extensions = getExtensions(extensionList);
            List<String> associationList = getAssociationList(adjacentConnections);
            String associations = getAssociations(associationList);

            codeBuilder.append(classBox.getClassName() + extensions + " {\n");
            codeBuilder.append(associations);
            codeBuilder.append("}\n\n");
        }

        return codeBuilder.toString();
    }

    private void refresh() {
        classBoxes = ClassSource.getClassBoxes();
        connections = ClassSource.getConnections();
        adjacencyMap = new HashMap<>();
    }

    private void generateAdjacencyMap() {
        for (Connection connection : connections) {
            ClassBox fromClass = connection.getFromClass();

            if (!adjacencyMap.containsKey(fromClass)) {
                adjacencyMap.put(fromClass, new ArrayList<>());
            }

            adjacencyMap.get(fromClass).add(connection);
        }
    }

    private List<Connection> getAdjacentConnections(ClassBox classBox) {
        List<Connection> adjacentConnections = new ArrayList<>();

        if (adjacencyMap.containsKey(classBox)) {
            adjacentConnections = adjacencyMap.get(classBox);
        }

        return adjacentConnections;
    }

    private List<String> getExtensionList(List<Connection> adjacentConnections) {
        List<String> extensionList = new ArrayList<>();

        for (Connection connection : adjacentConnections) {
            //TODO check if type/get type needs to be removed
            // this can be replaced with instanceof
            if (connection instanceof Triangle) {
                extensionList.add(connection.getToClass().getClassName());
            }
        }

        return extensionList;
    }

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

    private List<String> getAssociationList(List<Connection> adjacentConnections) {
        List<String> associationList = new ArrayList<>();

        for (Connection connection : adjacentConnections) {
            if (connection instanceof Arrow) {
                associationList.add(connection.getToClass().getClassName());
            }
        }

        return associationList;
    }

    private String getAssociations(List<String> associationList) {
        if (associationList.isEmpty()) {
            return "";
        }

        StringBuilder associationBuilder = new StringBuilder("\tmethod() {\n");

        for (String association : associationList) {
            associationBuilder.append("\t\t" + association + "\n");
        }

        associationBuilder.append("\t}\n");
        return associationBuilder.toString();
    }
}
