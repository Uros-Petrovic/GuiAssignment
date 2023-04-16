import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class NumberCheckerUI extends JFrame {

    public static void main(String[] args) {
        new NumberCheckerUI().setVisible(true);
    }

    private final JLabel resultLabel;
    private final JTextField inputField;
    private final JButton verifyButton;
    private final JButton exitButton;

    public NumberCheckerUI() {

        setSize(400, 400);
        setResizable(false);
        getContentPane().setSize(400, 400);

        setTitle("Number Checker");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.resultLabel = new JLabel();
        this.inputField = new JTextField();
        this.verifyButton = new JButton();
        this.exitButton = new JButton();
        setLayout(null);

        inputField.setText("Input Here");
        inputField.setBounds(100, 100, 200, 25);
        inputField.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {}

            @Override
            public void mousePressed(MouseEvent e) {
                inputFieldClickListener(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}
        });

        resultLabel.setText("Result Here");
        resultLabel.setBounds(225, 175, 100, 25);

        exitButton.setBounds(275, 325, 100, 25);
        exitButton.setText("Exit");
        exitButton.addActionListener(this::exitButtonActionListener);

        verifyButton.setBounds(100, 175, 100, 25);
        verifyButton.setText("Verify");
        verifyButton.addActionListener(this::verifyButtonActionListener);

        add(exitButton);
        add(verifyButton);
        add(resultLabel);
        add(inputField);
    }

    private void exitButtonActionListener(ActionEvent evt) {
        this.dispose();
    }

    private void inputFieldClickListener(MouseEvent evt) {
        inputField.setText("");
    }

    private void verifyButtonActionListener(ActionEvent evt) {
        String value = inputField.getText();

        try {

            int x = Integer.parseInt(value);
            resultLabel.setText("Integer Value: " + x);

        } catch (NumberFormatException e) {

            resultLabel.setText("Value not an Int!");

        }
    }

}
