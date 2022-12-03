package controller;

import model.ReverseCodeGenerator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This is the listener for the generate diagram button
 * This will run the reverse code generator to update class diagram accordingly
 */
public class GenerateDiagramButtonController implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        ReverseCodeGenerator reverseCodeGenerator = new ReverseCodeGenerator();
        reverseCodeGenerator.parseCode();
    }
}
