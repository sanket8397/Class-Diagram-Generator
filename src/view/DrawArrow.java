package view;

import model.Connection;
import model.LinePositions;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;

/**
 * UI for drawing arrow - association
 * Part of chain of responsibility - tail
 */
public class DrawArrow implements DrawConnection {
    @Override
    public void draw(Graphics2D graphics2D, Connection connection, LinePositions position) {
        AffineTransform at1 = new AffineTransform();
        AffineTransform at2 = new AffineTransform();
        Line2D line1 = new Line2D.Float(position.getToX(), position.getToY(), position.getToX() + 10, position.getToY() + 10);
        Line2D line2 = new Line2D.Float(position.getToX(), position.getToY(), position.getToX() + 10, position.getToY() + 10);
        at1.rotate(Math.atan2(position.getFromY() - position.getToY(), position.getFromX() - position.getToX()), position.getToX(), position.getToY());
        at2.rotate(Math.atan2(position.getFromY() - position.getToY(), position.getFromX() - position.getToX()), position.getToX(), position.getToY());
        at2.rotate(Math.toRadians(-90), position.getToX(), position.getToY());
        graphics2D.draw(at1.createTransformedShape(line1));
        graphics2D.draw(at2.createTransformedShape(line2));
    }
}
