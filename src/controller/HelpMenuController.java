package controller;

import view.MessageDialog;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

public class HelpMenuController implements ActionListener {
    private void showDevelopers() {
        List<String> developersList = new LinkedList<>();
        developersList.add("Sanket Surendra Kapse");
        developersList.add("Soham Prabhakar Patil");
        developersList.add("Rhishabh Suhas Hattarki");
        developersList.add("Anmol Girish More");
        StringBuilder developers = new StringBuilder();
        for (String developer : developersList)
            developers.append(developer).append("\n");

        new MessageDialog("Developers", developers.toString());
    }

    private void showDesignPatterns() {
        List<String> designPatternsList = new LinkedList<>();
        designPatternsList.add("Decorator");
        designPatternsList.add("Singleton");
        designPatternsList.add("Observer");
        designPatternsList.add("Chain of Responsibility");
        StringBuilder designPatterns = new StringBuilder();
        for (String designPattern : designPatternsList)
            designPatterns.append(designPattern).append("\n");

        new MessageDialog("Design Patterns", designPatterns.toString());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equalsIgnoreCase("Developers")) {
            showDevelopers();
        } else if (e.getActionCommand().equalsIgnoreCase("Design Patterns")) {
            showDesignPatterns();
        }
    }
}
