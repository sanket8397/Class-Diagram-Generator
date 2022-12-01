package View;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;

public class FileMenu extends JMenu {
    public FileMenu(){
        setText("File");
        JMenuItem newFile = new JMenuItem("New");
        JMenuItem saveFile = new JMenuItem("Save");
        JMenuItem loadFile = new JMenuItem("Load");

        add(newFile);
        add(saveFile);
        add(loadFile);

        loadFile.addActionListener(e -> chooseFile());
    }

    private void chooseFile(){
        JFileChooser j = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        int r = j.showOpenDialog(null);
    }
}
