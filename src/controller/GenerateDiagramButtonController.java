package controller;

import model.ReverseCodeGenerator;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GenerateDiagramButtonController implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        ReverseCodeGenerator reverseCodeGenerator = new ReverseCodeGenerator();
        reverseCodeGenerator.parseCode();
    }
}
