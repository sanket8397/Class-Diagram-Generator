package view;

import controller.FileController;
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

        FileController fileController = new FileController(this);
        saveFile.addActionListener(fileController);
        loadFile.addActionListener(fileController);

        add(newFile);
        add(saveFile);
        add(loadFile);
    }
}