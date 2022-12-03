package view;

import javax.swing.*;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

/**
 * UI to show the status bar at the bottom of the screen
 * Uses singleton pattern
 */
public class StatusBar extends JLabel {
    private static StatusBar instance;
    private StatusBar(){
        setLayout(new FlowLayout(FlowLayout.LEFT));
        setBorder(new CompoundBorder(new LineBorder(Color.DARK_GRAY),
                new EmptyBorder(4, 4, 4, 4)));
        setStatus("Status Bar");
    }

    public static StatusBar getInstance() {
        if (instance == null) {
            instance = new StatusBar();
        }
        return instance;
    }

    public void setStatus(String message) {
        setText(" " + message);
    }

}
