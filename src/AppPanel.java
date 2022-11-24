import javax.swing.*;
import java.awt.*;

public class AppPanel extends JPanel {
    public AppPanel(){
//        setLayout(new GridLayout(1,2));
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        CodePanel codePanel = new CodePanel();
        ClassSource classSource = ClassSource.getInstance();
        classSource.addObserver(codePanel);
        ClassPanel classPanel = new ClassPanel();
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 0.3;
        constraints.weighty = 1;
        add(codePanel, constraints);
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weighty = 1;
        constraints.weightx = 0.7;
        add(classPanel, constraints);

    }
}
