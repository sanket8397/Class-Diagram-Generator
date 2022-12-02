package view;

import javax.swing.*;
import java.awt.*;

public class App extends JFrame {
    public App(){
        setTitle("Assignment 4 and 5");
        JMenuBar menuBar = new JMenuBar();
        FileMenu fileMenu = new FileMenu();
        HelpMenu helpMenu = new HelpMenu();
        menuBar.add(fileMenu);
        menuBar.add(helpMenu);
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
