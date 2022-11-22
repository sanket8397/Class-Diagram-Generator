import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class ConnectionTest {
    private static final int WIDTH = 80;
    private static final int HEIGHT = 30;

    @Test
    public void connectionTest() {
        Connection line = new Line();
        Connection aggregation = new Diamond(line);
        assertEquals(ConnectionTypes.DIAMOND.ordinal(), aggregation.getType());
    }

    @Test
    public void connectionTest_from_to() {
        Connection line = new Line();
        Connection aggregation = new Diamond(line);
        aggregation.setToClass(new ClassBox("to", 10, 10));
        assertEquals(new Rectangle(10 - (WIDTH / 2), 10 - (HEIGHT / 2), WIDTH, HEIGHT),
                aggregation.getToClass().getRectangle());
    }
}
