package view;

import model.Connection;
import model.LinePositions;

import java.awt.*;

/**
 * Interface used to draw any kind of connection
 * Part of chain of responsibility
 */
public interface DrawConnection {
    void draw(Graphics2D graphics2D, Connection connection, LinePositions position);
}
