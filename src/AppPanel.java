import javax.swing.*;
import java.awt.*;

public class AppPanel extends JPanel {
    public AppPanel(){
        setLayout(new GridLayout(1,2));
        CodePanel codePanel = new CodePanel();
        ClassPanel classPanel = new ClassPanel();
        add(codePanel);
        add(classPanel);
    }
}
