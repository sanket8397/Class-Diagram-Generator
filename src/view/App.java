package view;

import javax.swing.*;
import java.awt.*;

/**
 * Application JFrame to run the GUI
 */
public class App extends JFrame {
    public App(){
        setTitle("Assignment 4 and 5");
        JMenuBar menuBar = new JMenuBar();
        FileMenu fileMenu = new FileMenu();
        HelpMenu helpMenu = new HelpMenu();
        GenerateDiagramButton generateDiagramButton = new GenerateDiagramButton();
        menuBar.add(fileMenu);
        menuBar.add(helpMenu);
        menuBar.add(generateDiagramButton);
        setJMenuBar(menuBar);
        AppPanel appPanel = new AppPanel();
        StatusBar statusBar = StatusBar.getInstance();
        setLayout(new BorderLayout());
        add(appPanel, BorderLayout.CENTER);
        add(statusBar, BorderLayout.SOUTH);
        setResizable(false);
        setExtendedState(MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
}
