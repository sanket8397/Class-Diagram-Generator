package model;

/**
 * Class used to position the lines (connections) smartly
 * according the other class in the connection
 */
public class LinePositions {

    private int fromX;
    private int fromY;
    private int toX;
    private int toY;
    final private int WIDTH = 80;
    final private int HEIGHT = 30;
    public LinePositions() {
    }

    /**
     * Set position of the line according to the other class
     * line will start on right side of box, if other class is on right and so on
     * @param fromRectX coordinate
     * @param fromRectY coordinate
     * @param toRectX coordinate
     * @param toRectY coordinate
     */
    public void setPositions(int fromRectX, int fromRectY, int toRectX, int toRectY) {
        int xDiff = fromRectX - toRectX;
        int yDiff = fromRectY - toRectY;
        if (Math.abs(xDiff) - Math.abs(yDiff) > 0) {
            if (xDiff < 0) {
                fromX = fromRectX + WIDTH;
                fromY = fromRectY + HEIGHT / 2;
                toX = toRectX;
            } else {
                fromX = fromRectX;
                fromY = fromRectY + HEIGHT / 2;
                toX = toRectX + WIDTH;
            }
            toY = toRectY + HEIGHT / 2;
        } else {
            fromX = fromRectX + WIDTH / 2;
            if (yDiff < 0) {
                fromY = fromRectY + HEIGHT;
                toX = toRectX + WIDTH / 2;
                toY = toRectY;
            } else {
                fromY = fromRectY;
                toX = toRectX + WIDTH / 2;
                toY = toRectY + HEIGHT;
            }
        }
    }

    public int getFromX() {
        return fromX;
    }

    public int getFromY() {
        return fromY;
    }

    public int getToX() {
        return toX;
    }

    public int getToY() {
        return toY;
    }

}
