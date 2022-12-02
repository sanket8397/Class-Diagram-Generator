import javax.swing.*;
import java.util.LinkedList;
import java.util.List;

public class HelpMenu extends JMenu {
    public HelpMenu(){
        setText("Help");
        JMenuItem developers = new JMenuItem("Developers");
        JMenuItem designPatterns = new JMenuItem("Design Patterns");

        add(developers);
        add(designPatterns);

        developers.addActionListener(e -> showDevelopers());
        designPatterns.addActionListener(e -> showDesignPatterns());
    }

    private void showDevelopers() {
        List<String> developersList = new LinkedList<>();
        developersList.add("Sanket Surendra Kapse");
        developersList.add("Soham Prabhakar Patil");
        developersList.add("Rhishabh Suhas Hattarki");
        developersList.add("Anmol Girish More");
        StringBuilder developers = new StringBuilder();
        for (String developer : developersList)
            developers.append(developer).append("\n");

        JOptionPane.showMessageDialog(this, developers.toString(),
                "Developers", JOptionPane.INFORMATION_MESSAGE);
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

        JOptionPane.showMessageDialog(this, designPatterns.toString(),
                "Design Patterns Used", JOptionPane.INFORMATION_MESSAGE);
    }
}
