package controller;

import model.*;
import view.FileMenu;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

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
    private void onSaveClicked() throws IOException {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileFilter(new FileNameExtensionFilter("Text Files", "txt"));
        int chooseVal = fileChooser.showSaveDialog(parentPanel);
        if (chooseVal == JFileChooser.APPROVE_OPTION) {
            File classesFile = fileChooser.getSelectedFile();
            FileHandler fileHandler = new FileHandler();
            fileHandler.saveFile(classesFile);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equalsIgnoreCase("Save")) {
            try {
                onSaveClicked();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        } else if (e.getActionCommand().equalsIgnoreCase("Load")) {
            onLoadClicked();
        } else if (e.getActionCommand().equalsIgnoreCase("New")) {
            onNewClicked();
        }
    }
}
