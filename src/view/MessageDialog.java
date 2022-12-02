package view;

import javax.swing.*;

public class MessageDialog extends JOptionPane {
    public MessageDialog(String title, String message) {
        showMessageDialog(null, message,
                title, JOptionPane.INFORMATION_MESSAGE);
    }

}