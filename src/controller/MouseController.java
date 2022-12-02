package controller;

import model.*;
import view.ClassBox;
import view.ClassPanel;
import view.StatusBar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class MouseController implements MouseListener, MouseMotionListener {

    final private ClassPanel parentPanel;
    private ClassBox fromClass;
    private ClassBox dragClass;
    private static final int WIDTH = 80;
    private static final int HEIGHT = 30;

    public MouseController(ClassPanel parentPanel) {
        this.parentPanel = parentPanel;
    }

    private void setStatus(String message){
        StatusBar statusBar = StatusBar.getInstance();
        statusBar.setStatus(message);
    }

    int checkOverlapping(int x, int y) {
        if ((x + WIDTH / 2) > parentPanel.getSize().width || (x - WIDTH / 2) < 0 ||
                (y + HEIGHT / 2) > parentPanel.getSize().height || (y - HEIGHT / 2) < 0)  {
            setStatus("Cannot create class because overlapping " +
                    "with screen boundaries");
            return 0;
        }
        ClassSource classSource = ClassSource.getInstance();

        for(ClassBox classBox: classSource.getClassBoxes()) {
            int class_x = classBox.getRectangle().x;
            int class_y = classBox.getRectangle().y;
            if (class_x <= x && class_x + WIDTH >= x && class_y <= y && class_y + HEIGHT >= y) {
                ClassBox toClass;
                if (fromClass == null) {
                    fromClass = classBox;
                    return 1;
                } else {
                    toClass = classBox;
                    String[] connectionOptions = {"Association", "Composition", "Inheritance"};
                    String selection = (String) JOptionPane.showInputDialog(parentPanel,
                            "Select model.Connection Type",
                            "",
                            JOptionPane.PLAIN_MESSAGE,
                            null,
                            connectionOptions, connectionOptions[0]);
                    if (selection == null) {
                        fromClass = null;
                        return 2;
                    }
                    Connection connection;
                    Line line = new Line();

                    if (selection.equals(connectionOptions[0])) {
                        connection = new Arrow(line);
                    } else if (selection.equals(connectionOptions[1])) {
                        connection = new Diamond(line);
                    } else {
                        connection = new Triangle(line);
                    }

                    connection.setFromClass(fromClass);
                    connection.setToClass(toClass);
                    classSource.addConnection(connection);
                    fromClass = null;
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
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int box_x = e.getX(), box_y = e.getY();
        int check = checkOverlapping(box_x, box_y);
        if (check == 1) {
            setStatus("Classes for from");
        } else if (check == 2) {
            setStatus("model.Line is drawn");
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

        parentPanel.repaint();
    }

    public ClassBox checkDragClass(int x, int y) {
        ClassSource classSource = ClassSource.getInstance();
        for(ClassBox classBox: classSource.getClassBoxes()){
            int class_x = classBox.getRectangle().x;
            int class_y = classBox.getRectangle().y;
            if (class_x <= x && class_x + WIDTH >= x && class_y <= y && class_y + HEIGHT >= y) {
                return classBox;
            }
        }
        return null;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        ClassBox classBox = checkDragClass(e.getX(), e.getY());
        if(classBox != null) {
            dragClass = classBox;
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Rectangle rectangle = new Rectangle(e.getX(), e.getY(), WIDTH, HEIGHT);
        if (dragClass != null && checkDragClass(e.getX(), e.getY()) != dragClass)
            dragClass.setRectangle(rectangle);
        dragClass = null;
        parentPanel.repaint();
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
