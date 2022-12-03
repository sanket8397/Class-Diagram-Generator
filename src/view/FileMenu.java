package view;

import controller.FileController;

import javax.swing.*;

/**
 * UI to display the file menu containing - new, save, load
 */
public class FileMenu extends JMenu {
    public FileMenu(){
        setText("File");
        JMenuItem newFile = new JMenuItem("New");
        JMenuItem saveFile = new JMenuItem("Save");
        JMenuItem loadFile = new JMenuItem("Load");

        FileController fileController = new FileController(this);
        newFile.addActionListener(fileController);
        saveFile.addActionListener(fileController);
        loadFile.addActionListener(fileController);

        add(newFile);
        add(saveFile);
        add(loadFile);
    }
}