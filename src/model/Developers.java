package model;

import java.util.LinkedList;
import java.util.List;

public class Developers {
    public String getDevelopers(){
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
}
