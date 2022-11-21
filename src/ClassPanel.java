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

    private void setStatus(String message){
        StatusBar statusBar = StatusBar.getInstance();
        statusBar.setStatus(message);
    }

    boolean checkOverlapping(int x, int y) {
        if ((x + WIDTH / 2) > this.getSize().width || (x - WIDTH / 2) < 0 || (y + HEIGHT / 2) > this.getSize().height || (y - HEIGHT / 2) < 0)  {
            setStatus("Cannot create class because overlapping " +
                    "with screen boundaries");
            return false;
        }

        int check_top_left_x = x - WIDTH / 2, check_top_left_y = y - HEIGHT / 2;
        int check_bottom_right_x = x + WIDTH / 2, check_bottom_right_y = y + HEIGHT / 2;

        for (ClassBox classBox: classBoxes) {
            Rectangle rectangle = classBox.getRectangle();

            int top_left_x = rectangle.x, top_left_y = rectangle.y;
            int bottom_right_x = rectangle.x + WIDTH, bottom_right_y = rectangle.y + HEIGHT;

            int top = Math.max(check_top_left_y, top_left_y);
            int bottom = Math.min(check_bottom_right_y, bottom_right_y);
            int left = Math.max(check_top_left_x, top_left_x);
            int right = Math.min(check_bottom_right_x, bottom_right_x);

            if (left <= right && top <= bottom) {
                setStatus("Cannot create class because overlapping " +
                        "with other class");
                return false;
            }

        }
        return true;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int box_x = e.getX(), box_y = e.getY();
        if (checkOverlapping(box_x, box_y)) {
            String className = JOptionPane.showInputDialog("Name");
            if (className == null) {
                setStatus("Classname cannot be Null");
                return;
            }
//          String className = "abc";
            ClassBox classBox = new ClassBox(className, box_x, box_y);
            classBoxes.add(classBox);
            setStatus("Class " + className + " is created");
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
