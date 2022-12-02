package model;

public class LinePositions {

    private int fromX;
    private int fromY;
    private int toX;
    private int toY;
    private int WIDTH = 80;
    private int HEIGHT = 30;
    public LinePositions() {
    }

    public void setPositions(int fromRectX, int fromRectY, int toRectX, int toRectY) {
        int xDiff = fromRectX - toRectX;
        int yDiff = fromRectY - toRectY;
        if (Math.abs(xDiff) - Math.abs(yDiff) > 0) {
            if (xDiff < 0) {
                fromX = fromRectX + WIDTH;
                fromY = fromRectY + HEIGHT / 2;
                toX = toRectX;
                toY = toRectY + HEIGHT / 2;
            } else {
                fromX = fromRectX;
                fromY = fromRectY + HEIGHT / 2;
                toX = toRectX + WIDTH;
                toY = toRectY + HEIGHT / 2;
            }
        } else {
            if (yDiff < 0) {
                fromX = fromRectX + WIDTH / 2;
                fromY = fromRectY + HEIGHT;
                toX = toRectX + WIDTH / 2;
                toY = toRectY;
            } else {
                fromX = fromRectX + WIDTH / 2;
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
