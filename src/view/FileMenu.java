package view;

import model.*;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.io.*;
import java.util.List;

public class FileMenu extends JMenu {
    public FileMenu(){
        setText("File");
        JMenuItem newFile = new JMenuItem("New");
        JMenuItem saveFile = new JMenuItem("Save");
        JMenuItem loadFile = new JMenuItem("Load");

        saveFile.addActionListener(e -> onSaveClicked());
        loadFile.addActionListener(e -> onLoadClicked());

        add(newFile);
        add(saveFile);
        add(loadFile);
    }

    private void onLoadClicked() {
        ClassSource classSource = ClassSource.getInstance();
        JFileChooser fileLoadDialog = new JFileChooser();

        int loadVal = fileLoadDialog.showSaveDialog(this);
        if (loadVal==JFileChooser.APPROVE_OPTION){
            File loadFile = fileLoadDialog.getSelectedFile();
            try (BufferedReader br = new BufferedReader(new FileReader(loadFile))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] tokens = line.split(",");
                    if (tokens[0].equals("0")){
                        ClassBox currBox = new ClassBox(tokens[1], Integer.parseInt(tokens[2]), Integer.parseInt(tokens[3]));
                        classSource.addClassBox(currBox);
                    } else if (tokens[0].equals("1")){
                        ClassBox fromClass = null;
                        ClassBox toClass = null;
                        for (ClassBox classbox : classSource.getClassBoxes()) {
                            if (classbox.getClassName().equals(tokens[2])) {
                                fromClass = classbox;
                            }
                            if (classbox.getClassName().equals(tokens[3])) {
                                toClass = classbox;
                            }
                        }

                        if (fromClass == null || toClass == null){
                            System.out.println("from class or to class is " +
                                    "null");
                            return;
                        }

                        LoadHandlerArrow arrow = new LoadHandlerArrow();
                        LoadHandlerDiamond diamond = new LoadHandlerDiamond();
                        LoadHandlerTriangle triangle =
                                new LoadHandlerTriangle();

                        arrow.setSuccessor(triangle);
                        triangle.setSuccessor(diamond);
                        arrow.loadConnection(tokens, fromClass, toClass);
                    } else {
                        JDialog dialog = new JDialog();
                        dialog.add(new JLabel("Bad data"));
                    }
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void onSaveClicked() {

        ClassSource source = ClassSource.getInstance();

        List<ClassBox> classBoxes = source.getClassBoxes();
        List<Connection> connections = source.getConnections();

        StringBuilder classesString = new StringBuilder();
        for (ClassBox classbox: classBoxes){
            String currBoxString = "0,";
            currBoxString += classbox.getClassName();
            currBoxString += ",";

            currBoxString += classbox.getRectangle().x;
            currBoxString += ",";

            currBoxString += classbox.getRectangle().y;
            currBoxString += ",";

            currBoxString += classbox.getRectangle().height;
            currBoxString += ",";

            currBoxString += classbox.getRectangle().width;
            currBoxString += "\n";

            classesString.append(currBoxString);
        }

        StringBuilder connectionString = new StringBuilder();
        for (Connection connection: connections){
            String currConnectionString = "1,";

            currConnectionString += connection.getType();
            currConnectionString += ",";

            currConnectionString += connection.getFromClass().getClassName();
            currConnectionString += ",";

            currConnectionString += connection.getToClass().getClassName();
            currConnectionString += "\n";

            connectionString.append(currConnectionString);
        }

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Text Files", "txt"));
        int chooseVal = fileChooser.showSaveDialog(this);
        if (chooseVal == JFileChooser.APPROVE_OPTION) {
            File classesFile = fileChooser.getSelectedFile();
            try{
                BufferedWriter writer = new BufferedWriter(new FileWriter(classesFile));
                writer.append(classesString.substring(0));
                writer.append(connectionString.substring(0));
                writer.close();
            } catch (IOException e){
                e.printStackTrace();
            }
        }
    }
}