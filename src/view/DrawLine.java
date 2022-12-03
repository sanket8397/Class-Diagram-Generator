package view;

import model.Connection;
import model.LinePositions;

import java.awt.*;

/**
 * UI to draw line
 * Part of chain of responsibility - has a successor
 */
public class DrawLine implements DrawConnection {
    private DrawConnection successor;
    @Override
    public void draw(Graphics2D graphics2D, Connection connection, LinePositions position) {
        graphics2D.drawLine(position.getFromX(), position.getFromY(), position.getToX(), position.getToY());
        successor.draw(graphics2D, connection, position);
    }

    public void setSuccessor(DrawConnection handler) {
        successor = handler;
    }
}
