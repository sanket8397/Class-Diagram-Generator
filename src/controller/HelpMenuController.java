package controller;

import model.HelpMenuData;
import view.MessageDialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This is a listener for the help menu to show project details
 * like developer list and design patterns
 */
public class HelpMenuController implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        HelpMenuData helpMenuData = new HelpMenuData();

        if (e.getActionCommand().equalsIgnoreCase("Developers")) {
            new MessageDialog("Developers", helpMenuData.getDevelopers());
        } else if (e.getActionCommand().equalsIgnoreCase("Design Patterns")) {
            new MessageDialog("Design Patterns", helpMenuData.getDesignPatterns());
        }
    }
}
