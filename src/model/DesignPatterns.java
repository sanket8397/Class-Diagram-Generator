package model;

import view.MessageDialog;

import java.util.LinkedList;
import java.util.List;

public class DesignPatterns {
    public String getDesignedPatterns() {
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
