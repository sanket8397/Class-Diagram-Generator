package view;

import controller.GenerateDiagramButtonController;

import javax.swing.*;

public class GenerateDiagramButton extends JButton {
    public GenerateDiagramButton() {
        setText("Generate Diagram");

        System.out.println();
        addActionListener(new GenerateDiagramButtonController());
    }
}
