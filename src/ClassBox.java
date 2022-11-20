import java.awt.*;

public class ClassBox {
    private Rectangle rectangle;
    private String className;
    public ClassBox(int x, int y) {
        rectangle = new Rectangle(x, y, 40, 20);
    }

    public void draw(Graphics graphics) {
        graphics.drawRect(rectangle.x, rectangle.y, rectangle.width, rectangle.height);
    }
}
