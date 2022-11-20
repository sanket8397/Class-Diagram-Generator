import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class ClassPanel extends JPanel implements MouseListener {
    ArrayList<ClassBox> classBoxes = new ArrayList<>();

    private static final int WIDTH = 80;
    private static final int HEIGHT = 30;

    public ClassPanel(){
        setBackground(Color.WHITE);
        addMouseListener(this);
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D graphics2D = (Graphics2D) g;
        for (ClassBox classbox: classBoxes) {
            classbox.draw(graphics2D);
        }
    }

    boolean checkOverlapping(int x, int y) {
        return true;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int box_x = e.getX(), box_y = e.getY();
        System.out.println(e.getX() + "---"+ e.getY());
//        String className = JOptionPane.showInputDialog("Name");
        String className = "abc";
        if (checkOverlapping(box_x, box_y)) {
            ClassBox classBox = new ClassBox(className, box_x, box_y);
            classBoxes.add(classBox);
            repaint();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
