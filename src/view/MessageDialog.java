package view;

import javax.swing.*;

/**
 * UI to display any given message
 */
public class MessageDialog extends JOptionPane {
    public MessageDialog(String title, String message) {
        showMessageDialog(null, message,
                title, JOptionPane.INFORMATION_MESSAGE);
    }
}