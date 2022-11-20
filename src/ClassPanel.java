import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class ClassPanel extends JPanel implements MouseListener {
    ArrayList<ClassBox> classBoxes = new ArrayList<>();
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

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println(e.getX() + "---"+ e.getY());
        ClassBox classBox = new ClassBox(e.getX(), e.getY());
        classBoxes.add(classBox);
        repaint();
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
