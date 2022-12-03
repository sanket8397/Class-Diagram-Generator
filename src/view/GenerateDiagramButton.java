package view;

import controller.GenerateDiagramButtonController;

import javax.swing.*;

/**
 * Button UI for generate diagram - clicking this will generate
 * the diagrams in class panel based on the code written in code panel
 */
public class GenerateDiagramButton extends JButton {
    public GenerateDiagramButton() {
        setText("Generate Diagram");
        addActionListener(new GenerateDiagramButtonController());
    }
}
