import javax.swing.*;

public class FileMenu extends JMenu {
    public FileMenu(){
        setText("File");
        JMenuItem newFile = new JMenuItem("New");
        JMenuItem saveFile = new JMenuItem("Save");
        JMenuItem loadFile = new JMenuItem("Load");

        add(newFile);
        add(saveFile);
        add(loadFile);
    }
}
