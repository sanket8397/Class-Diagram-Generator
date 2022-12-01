package Controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;

public class HelpMenuController implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        String action = e.getActionCommand();
        if (action.equalsIgnoreCase("Developers")){
            List<String> developersList = new LinkedList<>();
            developersList.add("Sanket Surendra Kapse");
            developersList.add("Soham Prabhakar Patil");
            developersList.add("Rhishabh Suhas Hattarki");
            developersList.add("Anmol More");
            StringBuilder developers = new StringBuilder();
            for (String developer : developersList)
                developers.append(developer).append("\n");

            JOptionPane.showMessageDialog(null, developers.toString(),
                    "Developers", JOptionPane.INFORMATION_MESSAGE);
        }
        else if (action.equalsIgnoreCase("Design Patterns")){
            List<String> designPatternsList = new LinkedList<>();
            designPatternsList.add("Decorator");
            designPatternsList.add("Singleton");
            designPatternsList.add("Observer");
            designPatternsList.add("Chain of Responsibility");
            StringBuilder designPatterns = new StringBuilder();
            for (String designPattern : designPatternsList)
                designPatterns.append(designPattern).append("\n");

            JOptionPane.showMessageDialog(null, designPatterns.toString(),
                    "Design Patterns Used", JOptionPane.INFORMATION_MESSAGE);
        }


    }
}
