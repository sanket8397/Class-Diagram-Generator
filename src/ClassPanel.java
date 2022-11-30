import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;
import java.util.List;

public class ClassPanel extends JPanel implements MouseListener {

    private static final int WIDTH = 80;
    private static final int HEIGHT = 30;
    private ClassBox fromClass;
    private ClassBox toClass;

    public ClassPanel(){
        setBackground(Color.WHITE);
        addMouseListener(this);
    }

    @Override
    public void paintComponent(Graphics g) {
        Graphics2D graphics2D = (Graphics2D) g;
        ClassSource classSource = ClassSource.getInstance();
        List<ClassBox> classBoxes = classSource.getClassBoxes();
        for (ClassBox classbox: classBoxes) {
            classbox.draw(graphics2D);
        }

        List<Connection> connections = classSource.getConnections();
        for (Connection connection: connections) {
            graphics2D.setColor(Color.black);
            LinePositions positions = new LinePositions();
            positions.setPositions(connection.getFromClass().getRectangle().x,
                    connection.getFromClass().getRectangle().y,
                    connection.getToClass().getRectangle().x,
                    connection.getToClass().getRectangle().y);
            graphics2D.drawLine(positions.getFromX(), positions.getFromY(), positions.getToX(), positions.getToY());


//            This is to draw arrowheads
//            AffineTransform at1 = new AffineTransform();
//            AffineTransform at2 = new AffineTransform();
//            Line2D line1 = new Line2D.Float(positions.getToX(), positions.getToY(), positions.getToX() + 10, positions.getToY() + 10);
//            Line2D line2 = new Line2D.Float(positions.getToX(), positions.getToY(), positions.getToX() + 10, positions.getToY() + 10);
//            at1.rotate(Math.atan2(positions.getFromY() - positions.getToY(), positions.getFromX() - positions.getToX()), positions.getToX(), positions.getToY());
//            at2.rotate(Math.atan2(positions.getFromY() - positions.getToY(), positions.getFromX() - positions.getToX()), positions.getToX(), positions.getToY());
//            at2.rotate(Math.toRadians(-90), positions.getToX(), positions.getToY());
//            graphics2D.draw(at1.createTransformedShape(line1));
//            graphics2D.draw(at2.createTransformedShape(line2));

//            This is for drawing Diamond
//            Rectangle rectangle = new Rectangle(positions.getFromX(), positions.getFromY(), 10, 10);
//            AffineTransform at = new AffineTransform();
//            at.rotate(Math.atan2(positions.getFromY() - positions.getToY(), positions.getFromX() - positions.getToX()),
//                    rectangle.x, rectangle.y);
//            at.rotate(Math.toRadians(135), rectangle.x, rectangle.y);
//            graphics2D.draw(at.createTransformedShape(rectangle));
//            graphics2D.fill(at.createTransformedShape(rectangle));
        }
    }

    private void setStatus(String message){
        StatusBar statusBar = StatusBar.getInstance();
        statusBar.setStatus(message);
    }

    int checkOverlapping(int x, int y) {
        if ((x + WIDTH / 2) > this.getSize().width || (x - WIDTH / 2) < 0 || (y + HEIGHT / 2) > this.getSize().height || (y - HEIGHT / 2) < 0)  {
            setStatus("Cannot create class because overlapping " +
                    "with screen boundaries");
            return 0;
        }
        ClassSource classSource = ClassSource.getInstance();

        for(ClassBox classBox: classSource.getClassBoxes()) {
            int class_x = classBox.getRectangle().x;
            int class_y = classBox.getRectangle().y;
            if (class_x <= x && class_x + WIDTH >= x && class_y <= y && class_y + HEIGHT >= y) {
                if (fromClass == null) {
                    fromClass = classBox;
                    toClass = null;
                    return 1;
                } else {
                    toClass = classBox;
                    String[] connectionOptions = {"Association", "Composition", "Inheritance"};
                    String selection = (String)JOptionPane.showInputDialog(this,
                            "Select Connection Type",
                            "",
                            JOptionPane.PLAIN_MESSAGE,
                            null,
                            connectionOptions, connectionOptions[0]);
                    Connection connection;
                    Line line = new Line();

                    if (selection.equals(connectionOptions[0])) {
                        connection = new Arrow(line);
                    } else if (selection.equals(connectionOptions[1])) {
                        connection = new Diamond(line);
                    } else {
                        connection = new Triangle(line);
                    }

                    System.out.println(selection);
                    connection.setFromClass(fromClass);
                    connection.setToClass(toClass);
                    classSource.addConnection(connection);
                    fromClass = null;
                    toClass = null;
                    return 2;
                }
            }
        }

        int check_top_left_x = x - WIDTH / 2, check_top_left_y = y - HEIGHT / 2;
        int check_bottom_right_x = x + WIDTH / 2, check_bottom_right_y = y + HEIGHT / 2;
        for (ClassBox classBox: classSource.getClassBoxes()) {
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
                return 0;
            }

        }
        return 3;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int box_x = e.getX(), box_y = e.getY();
        int check = checkOverlapping(box_x, box_y);
        if (check == 1) {
            setStatus("Classes for from");
        } else if (check == 2) {
            setStatus("Line is drawn");
        } else if (check == 3) {
            String className = JOptionPane.showInputDialog("Name");
            if (className == null) {
                setStatus("Classname cannot be Null");
                return;
            }
            ClassBox classBox = new ClassBox(className, box_x, box_y);
            ClassSource classSource = ClassSource.getInstance();
            classSource.addClassBox(classBox);
            setStatus("Class " + className + " is created");
        }

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
