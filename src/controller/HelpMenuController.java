package controller;

import model.DesignPatterns;
import model.Developers;
import view.MessageDialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

public class HelpMenuController implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equalsIgnoreCase("Developers")) {
            Developers developers = new Developers();
            String message = developers.getDevelopers();
            new MessageDialog("Developers", message);
        } else if (e.getActionCommand().equalsIgnoreCase("Design Patterns")) {
            DesignPatterns designPatterns = new DesignPatterns();
            String message = designPatterns.getDesignedPatterns();
            new MessageDialog("Design Patterns", message);
        }
    }
}
