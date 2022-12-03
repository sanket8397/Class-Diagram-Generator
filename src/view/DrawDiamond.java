package view;

import model.Connection;
import model.Diamond;
import model.LinePositions;

import java.awt.*;
import java.awt.geom.AffineTransform;

/**
 * UI to draw diamond - composition
 * Part of chain of responsibility - has a successor
 */
public class DrawDiamond implements DrawConnection{
    private DrawConnection successor;
    @Override
    public void draw(Graphics2D graphics2D, Connection connection, LinePositions position) {
        if (connection instanceof Diamond) {
            AffineTransform at = new AffineTransform();
            int line1_x1 = position.getFromX();
            int line1_y1 = position.getFromY();

            int line2_x1 = position.getFromX() + 5;
            int line2_y1 = position.getFromY() + 10;

            int line3_x1 = position.getFromX();
            int line3_y1 = position.getFromY() + 20;

            int line4_x1 = position.getFromX() - 5;
            int line4_y1 = position.getFromY() + 10;

            int[] x = {line1_x1, line2_x1, line3_x1, line4_x1};
            int[] y = {line1_y1, line2_y1, line3_y1, line4_y1};

            Polygon polygon = new Polygon(x, y, 4);
            at.rotate(Math.atan2(position.getFromY() - position.getToY(), position.getFromX() - position.getToX()), position.getFromX(), position.getFromY());
            at.rotate(Math.toRadians(90), position.getFromX(), position.getFromY());
            graphics2D.setColor(Color.BLACK);
            graphics2D.draw(at.createTransformedShape(polygon));
            graphics2D.fill(at.createTransformedShape(polygon));
        } else {
            successor.draw(graphics2D, connection, position);
        }
    }

    public void setSuccessor(DrawConnection handler) {
        successor = handler;
    }
}
