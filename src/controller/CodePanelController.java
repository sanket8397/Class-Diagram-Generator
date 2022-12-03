package controller;

import model.ClassSource;

import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;

/**
 * This class listens to the Text Area in the Code Panel
 * Whenever there is any new addition, change or anything is removed,
 * the generatedCode is updated in the Class Source
 * This is used to generate the class diagrams from user input source code
 */
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

    /**
     * Get the text data from the code panel and update
     * the generated code in Class source
     * @param e document object that holds the text from code panel
     */
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
