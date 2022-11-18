import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

public class StatusBar extends JPanel {
    public StatusBar(){
        setLayout(new FlowLayout(FlowLayout.LEFT));
        setBorder(new CompoundBorder(new LineBorder(Color.DARK_GRAY),
                new EmptyBorder(4, 4, 4, 4)));
        JLabel status = new JLabel("Status Bar");
        add(status);
    }
}
