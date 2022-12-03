package view;

import controller.MouseController;
import model.*;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * UI for the class diagram part
 * Shows all the class boxes and connections
 * Part of Observer pattern - observes the class source
 * It will update whenever data in class source is updated
 */
public class ClassPanel extends JPanel implements Observer {

    public ClassPanel(){
        setBackground(Color.WHITE);
        addMouseListener(new MouseController(this));
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

        DrawLine drawLine = new DrawLine();
        DrawDiamond drawDiamond = new DrawDiamond();
        DrawArrow drawArrow = new DrawArrow();
        DrawTriangle drawTriangle = new DrawTriangle();

        drawLine.setSuccessor(drawDiamond);
        drawDiamond.setSuccessor(drawTriangle);
        drawTriangle.setSuccessor(drawArrow);

        for (Connection connection: connections) {
            graphics2D.setColor(Color.black);
            LinePositions positions = new LinePositions();
            positions.setPositions(connection.getFromClass().getRectangle().x,
                    connection.getFromClass().getRectangle().y,
                    connection.getToClass().getRectangle().x,
                    connection.getToClass().getRectangle().y);
            drawLine.draw(graphics2D, connection, positions);
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        repaint();
    }
}
