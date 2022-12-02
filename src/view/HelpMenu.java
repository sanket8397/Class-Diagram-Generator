package view;

import controller.HelpMenuController;

import javax.swing.*;
public class HelpMenu extends JMenu {
    public HelpMenu(){
        setText("Help");
        JMenuItem developers = new JMenuItem("Developers");
        JMenuItem designPatterns = new JMenuItem("Design Patterns");

        add(developers);
        add(designPatterns);

        HelpMenuController helpMenuController = new HelpMenuController();
        developers.addActionListener(helpMenuController);
        designPatterns.addActionListener(helpMenuController);
    }


}
