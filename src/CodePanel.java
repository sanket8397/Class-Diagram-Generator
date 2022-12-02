import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

public class CodePanel extends JPanel implements Observer {
    private CodeGenerator codeGenerator;
    private JTextArea codeTextArea;

    public CodePanel(){
        setBackground(Color.LIGHT_GRAY);
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.fill = GridBagConstraints.BOTH;
        constraints.weightx = 1.0;
        constraints.weighty = 1.0;
        codeTextArea = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(codeTextArea);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        add(scrollPane, constraints);
        final String RESIZE_DELAY = "                                                  \n";
        codeTextArea.setText(RESIZE_DELAY);
        codeGenerator = new CodeGenerator();
    }

    @Override
    public void update(Observable o, Object arg) {
        String generatedCode = codeGenerator.generateCode();
        codeTextArea.setText(generatedCode);
        repaint();
    }
}
