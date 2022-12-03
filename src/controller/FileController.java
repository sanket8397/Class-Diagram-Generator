package controller;

import model.*;
import view.ClassBox;
import view.FileMenu;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.List;

/**
 * This connects the actions done in the File Menu - New, Save, Load
 * to its functionality of resetting the file, saving the file
 * and loading, respectively
 */
public class FileController implements ActionListener {
    final private FileMenu parentPanel;

    public FileController(FileMenu fileMenu) {
        this.parentPanel = fileMenu;
    }

    /**
     * Clear the source data from class source when new is clicked
     */
    private void onNewClicked() {
        ClassSource.getInstance().clearSource();
    }

    /**
     * When load is clicked, open the load window, when file is selected,
     * parse the file data to load the class source
     */
    private void onLoadClicked() {
        JFileChooser fileLoadDialog = new JFileChooser();
        int loadVal = fileLoadDialog.showOpenDialog(parentPanel);
        if (loadVal==JFileChooser.APPROVE_OPTION){
            File loadFile = fileLoadDialog.getSelectedFile();
            try {
                FileHandler fileHandler = new FileHandler();
                fileHandler.loadFile(loadFile);
            } catch (Exception e) {
                JDialog dialog = new JDialog();
                dialog.add(new JLabel("Bad data"));
            }
        }
    }

    /**
     * When save is clicked, the save window takes the file name,
     * the class source is encoded into the file for later retrieval
     */
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
        int chooseVal = fileChooser.showSaveDialog(parentPanel);
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equalsIgnoreCase("Save")) {
            onSaveClicked();
        } else if (e.getActionCommand().equalsIgnoreCase("Load")) {
            onLoadClicked();
        } else if (e.getActionCommand().equalsIgnoreCase("New")) {
            onNewClicked();
        }
    }
}
