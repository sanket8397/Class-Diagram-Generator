package view;

import model.ClassSource;

import javax.swing.*;
import java.awt.*;

/**
 * Main application panel in the App
 * Adds the observers to the observable class source
 */
public class AppPanel extends JPanel {
    public AppPanel(){
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        CodePanel codePanel = new CodePanel();
        ClassSource classSource = ClassSource.getInstance();
        classSource.addObserver(codePanel);
        ClassPanel classPanel = new ClassPanel();
        classSource.addObserver(classPanel);
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
