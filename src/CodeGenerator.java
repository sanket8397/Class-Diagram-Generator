import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CodeGenerator {
    public void generateCode() {
        List<ClassBox> classBoxes = ClassSource.getClassBoxes();
        List<Connection> connections = ClassSource.getConnections();
        Map<ClassBox, List<Connection>> adjacencyMap = new HashMap<>();
        generateAdjacencyMap(adjacencyMap, connections);

        for (ClassBox classBox : classBoxes) {
            List<Connection> adjacentConnections = new ArrayList<>();
            List<String> extensionList = new ArrayList<>();

            if (adjacencyMap.containsKey(classBox)) {
                adjacentConnections = adjacencyMap.get(classBox);
            }

            for (Connection connection : adjacentConnections) {
                //TODO check if type/get type needs to be removed
                // this can be replaced with instanceof
                if (connection instanceof Triangle) {
                    extensionList.add(connection.getToClass().getClassName());
                }
            }

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

            System.out.println(classBox.getClassName() + extensions + " {");
            System.out.println("}\n");
        }
    }

    private void generateAdjacencyMap(Map<ClassBox, List<Connection>> adjacencyMap,
                                     List<Connection> connections) {
        for (Connection connection : connections) {
            ClassBox fromClass = connection.getFromClass();

            if (!adjacencyMap.containsKey(fromClass)) {
                adjacencyMap.put(fromClass, new ArrayList<>());
            }

            adjacencyMap.get(fromClass).add(connection);
        }
    }
}
