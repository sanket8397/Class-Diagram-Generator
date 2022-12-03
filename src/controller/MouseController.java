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

/**
 * Observes mouse actions and performs actions like drag and drop, class and relation
 * creation, and vicinity checking.
 */
public class MouseController implements MouseListener, MouseMotionListener {

    final private ClassPanel parentPanel;
    private ClassBox fromClass;
    private ClassBox dragClass;
    private static final int WIDTH = 80;
    private static final int HEIGHT = 30;

    public MouseController(ClassPanel parentPanel) {
        this.parentPanel = parentPanel;
    }

    /**
     * Set the message in status bar located at the bottom on the screen
     * @param message to be set
     */
    private void setStatus(String message){
        StatusBar statusBar = StatusBar.getInstance();
        statusBar.setStatus(message);
    }

    /**
     * Check the boundaries of the class being created,
     * whether it overflows from panel
     * @param x coordinate
     * @param y coordinate
     * @return boolean
     */
    private boolean checkBoundaries(int x, int y) {
        if ((x + WIDTH / 2) > parentPanel.getSize().width || (x - WIDTH / 2) < 0 ||
                (y + HEIGHT / 2) > parentPanel.getSize().height || (y - HEIGHT / 2) < 0)  {
            setStatus("Cannot create class because overlapping " +
                    "with screen boundaries");
            return true;
        }
        return false;
    }

    /**
     * Check for crossing classes, whether it's in other class' vicinity
     * @param class_x x coordinate of first class
     * @param class_y y coordinate of first class
     * @param x of next class
     * @param y of next class
     * @return boolean
     */
    private boolean checkCrossingClasses(int class_x, int class_y, int x, int y) {
        return class_x <= x && class_x + WIDTH >= x && class_y <= y && class_y + HEIGHT >= y;
    }

    /**
     * Check if class with given name already exists in source
     * @param className of the class to be checked
     * @return boolean
     */
    private boolean checkClassNameExists(String className) {
        ClassSource classSource = ClassSource.getInstance();
        if (className.equals(""))
            return true;
        for(ClassBox classBox: classSource.getClassBoxes()) {
            if (classBox.getClassName().equals(className)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check if classes are overlapping with given x, y coordinates
     * @param x coordinate
     * @param y coordinate
     * @param classSource source object
     * @return boolean
     */
    private boolean checkClassOverlapping(int x, int y, ClassSource classSource) {
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
                return true;
            }
        }
        return false;
    }

    /**
     * Draw the connection to the given class box
     * @param classSource source object
     * @param classBox to which connection is to be drawn
     * @return boolean
     */
    private boolean drawConnection(ClassSource classSource, ClassBox classBox) {
        String[] connectionOptions = {"Association", "Composition", "Inheritance"};
        String selection = (String) JOptionPane.showInputDialog(parentPanel,
                "Select Connection Type",
                "",
                JOptionPane.PLAIN_MESSAGE,
                null,
                connectionOptions, connectionOptions[0]);
        if (selection == null) {
            fromClass = null;
            return false;
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
        connection.setToClass(classBox);
        classSource.addConnection(connection);
        fromClass = null;
        return true;
    }

    /**
     * Check if there are any blockers for given x, y coordinates
     * @param x coordinate
     * @param y coordinate
     * @return int
     */
    private int checkBlockers(int x, int y) {
        if (checkBoundaries(x, y))
            return 0;
        ClassSource classSource = ClassSource.getInstance();
        for(ClassBox classBox: classSource.getClassBoxes()) {
            int class_x = classBox.getRectangle().x;
            int class_y = classBox.getRectangle().y;
            if (checkCrossingClasses(class_x, class_y, x, y)) {
                if (fromClass == null) {
                    fromClass = classBox;
                    return 1;
                } else {
                    if (drawConnection(classSource, classBox)) {
                        return 2;
                    }
                }
            }
        }
        if (checkClassOverlapping(x, y, classSource))
            return 0;
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
        int check = checkBlockers(box_x, box_y);
        if (check == 0) {
            setStatus("Cannot overlap with other class' area and boundaries");
        } else if (check == 1) {
            setStatus("Class selected for connection");
        } else if (check == 2) {
            setStatus("Line is drawn");
        } else if (check == 3) {
            String className = JOptionPane.showInputDialog("Name");
            if (className == null || checkClassNameExists(className)) {
                setStatus("Classname cannot be Duplicate or null");
                return;
            }
            ClassBox classBox = new ClassBox(className, box_x, box_y);
            ClassSource classSource = ClassSource.getInstance();
            classSource.addClassBox(classBox);
            setStatus("Class " + className + " is created");
        } else {
            setStatus("Something is wrong! For sure... ;)");
        }
    }

    /**
     * Validate class that is being dragged to a safe location
     * @param x coordinate
     * @param y coordinate
     * @return ClassBox
     */
    public ClassBox checkDragClass(int x, int y) {
        ClassSource classSource = ClassSource.getInstance();
        for(ClassBox classBox: classSource.getClassBoxes()){
            int class_x = classBox.getRectangle().x;
            int class_y = classBox.getRectangle().y;
            if (checkCrossingClasses(class_x, class_y, x, y)) {
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
        if (dragClass != null && checkDragClass(e.getX(), e.getY()) != dragClass && !checkBoundaries(e.getX() + WIDTH / 2, e.getY() + HEIGHT / 2) && !checkClassOverlapping(e.getX(), e.getY(), ClassSource.getInstance())) {
            dragClass.setRectangle(rectangle);
            setStatus("Class dragged to other location");
        } else {
            setStatus("Not able to drag to that location");
        }
        dragClass = null;
        ClassSource.getInstance().updatedByReference();
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
