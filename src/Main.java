import javax.swing.*;

public class Main extends JFrame {

    public Main(){
        JMenuBar menuBar = new JMenuBar();
        FileMenu fileMenu = new FileMenu();
        HelpMenu helpMenu = new HelpMenu();
        menuBar.add(fileMenu);
        menuBar.add(helpMenu);
        setJMenuBar(menuBar);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    public static void main(String[] args) {
        JFrame window = new Main();
        window.setSize(500, 500);
        window.setVisible(true);
    }
}
