import javax.swing.*;
import java.awt.*;

public class Main extends JFrame {

    public Main(){
        setTitle("Assignment 3 and 4");
        JMenuBar menuBar = new JMenuBar();
        FileMenu fileMenu = new FileMenu();
        HelpMenu helpMenu = new HelpMenu();
        menuBar.add(fileMenu);
        menuBar.add(helpMenu);
        setJMenuBar(menuBar);
        AppPanel appPanel = new AppPanel();
        StatusBar statusBar = new StatusBar();
        setLayout(new BorderLayout());
        add(appPanel, BorderLayout.CENTER);
        add(statusBar, BorderLayout.SOUTH);
        setResizable(false);
        setExtendedState(MAXIMIZED_BOTH);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    public static void main(String[] args) {
        JFrame window = new Main();
//        window.setSize(500, 500);
        window.setVisible(true);
        window.setExtendedState(MAXIMIZED_BOTH);
        window.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
