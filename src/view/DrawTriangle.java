package view;

import model.Connection;
import model.LinePositions;
import model.Triangle;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Line2D;

/**
 * UI to draw triangle - inheritance
 * Part of chain of responsibility - has a successor
 */
public class DrawTriangle implements DrawConnection {
    private DrawConnection successor;
    @Override
    public void draw(Graphics2D graphics2D, Connection connection, LinePositions position) {
        if (connection instanceof Triangle) {
            AffineTransform at = new AffineTransform();
            int line1_x1 = position.getToX();
            int line1_y1 = position.getToY();

            int line2_x1 = position.getToX() + 5;
            int line2_y1 = position.getToY() + 10;

            int line3_x1 = position.getToX() - 5;
            int line3_y1 = position.getToY() + 10;
            int[] x = {line1_x1, line2_x1, line3_x1};
            int[] y = {line1_y1, line2_y1, line3_y1};
            Line2D line1 = new Line2D.Float(x[0] - 1, y[0] - 1, x[1] + 1, y[1] + 1);
            Line2D line2 = new Line2D.Float(x[1] + 1, y[1] + 1, x[2] - 1, y[2] + 1);
            Line2D line3 = new Line2D.Float(x[2] - 1, y[2] + 1, x[0] - 1, y[0] - 1);

            Polygon polygon = new Polygon(x, y, 3);
            at.rotate(Math.atan2(position.getFromY() - position.getToY(), position.getFromX() - position.getToX()), position.getToX(), position.getToY());
            at.rotate(Math.toRadians(-90), position.getToX(), position.getToY());
            graphics2D.setColor(Color.WHITE);
            graphics2D.draw(at.createTransformedShape(polygon));
            graphics2D.fill(at.createTransformedShape(polygon));
            graphics2D.setColor(Color.BLACK);
            graphics2D.draw(at.createTransformedShape(line1));
            graphics2D.draw(at.createTransformedShape(line2));
            graphics2D.draw(at.createTransformedShape(line3));
        } else {
            successor.draw(graphics2D, connection, position);
        }
    }

    public void setSuccessor(DrawConnection handler) {
        successor = handler;
    }
}
