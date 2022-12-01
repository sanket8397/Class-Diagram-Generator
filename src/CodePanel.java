import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class CodePanel extends JTextArea implements Observer {
    private CodeGenerator codeGenerator;

    public CodePanel(){
        setBackground(Color.LIGHT_GRAY);
        final String RESIZE_DELAY = "                                                  \n";
        setText(RESIZE_DELAY);
        codeGenerator = new CodeGenerator();
    }

    @Override
    public void update(Observable o, Object arg) {
        String generatedCode = codeGenerator.generateCode();
        setText(generatedCode);
        repaint();
    }
}
