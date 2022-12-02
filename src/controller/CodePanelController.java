package controller;

import model.ClassSource;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

public class CodePanelController implements DocumentListener {

    @Override
    public void insertUpdate(DocumentEvent e) {
        updateSourceCode(e);
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        updateSourceCode(e);
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
        updateSourceCode(e);
    }

    private void updateSourceCode(DocumentEvent e) {
        try {
            Document document = e.getDocument();
            int len = document.getLength();
            ClassSource.getInstance().setGeneratedCode(document.getText(0, len));
        } catch (BadLocationException ex) {
            throw new RuntimeException(ex);
        }
    }
}
