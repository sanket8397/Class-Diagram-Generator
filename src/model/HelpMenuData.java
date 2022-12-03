package model;

import java.util.LinkedList;
import java.util.List;

/**
 * Data for the help menu
 */
public class HelpMenuData {
    /**
     * Get the list of developers working on the project
     */
    public String getDevelopers() {
        List<String> developersList = new LinkedList<>();
        developersList.add("Sanket Surendra Kapse");
        developersList.add("Soham Prabhakar Patil");
        developersList.add("Rhishabh Suhas Hattarki");
        developersList.add("Anmol Girish More");
        StringBuilder developers = new StringBuilder();
        for (String developer : developersList)
            developers.append(developer).append("\n");

        return developers.toString();
    }

    /**
     * Get the design patterns used in the project
     */
    public String getDesignPatterns() {
        List<String> designPatternsList = new LinkedList<>();
        designPatternsList.add("Decorator");
        designPatternsList.add("Singleton");
        designPatternsList.add("Observer");
        designPatternsList.add("Chain of Responsibility");
        StringBuilder designPatterns = new StringBuilder();
        for (String designPattern : designPatternsList)
            designPatterns.append(designPattern).append("\n");

        return designPatterns.toString();
    }
}
