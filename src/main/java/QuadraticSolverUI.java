import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class QuadraticSolverUI extends JFrame {

    public static void main(String[] args) {
        new QuadraticSolverUI().setVisible(true);
    }

    private final JLabel labelA;
    private final JLabel labelB;
    private final JLabel labelC;
    private final JLabel zeroesLabel;

    private final JButton exitButton;
    private final JButton solveButton;

    private final JTextField fieldA;
    private final JTextField fieldB;
    private final JTextField fieldC;

    public QuadraticSolverUI() {

        this.exitButton = new JButton();
        this.solveButton = new JButton();
        this.labelA = new JLabel();
        this.labelB = new JLabel();
        this.labelC = new JLabel();
        this.zeroesLabel = new JLabel();
        this.fieldA = new JTextField();
        this.fieldB = new JTextField();
        this.fieldC = new JTextField();


        setSize(400, 400);
        setResizable(true);
        getContentPane().setSize(400, 400);
        setTitle("Quadratic Solver");
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        labelA.setBounds(100, 100, 20, 25);
        labelA.setText("A: ");

        labelB.setBounds(100, 130, 20, 25);
        labelB.setText("B: ");

        labelC.setBounds(100, 160, 20, 25);
        labelC.setText("C: ");

        zeroesLabel.setBounds(100, 200, 200, 25);
        zeroesLabel.setText("[Zeroes Here]");

        fieldA.setBounds(120, 100, 100, 25);
        fieldA.setText("[A Term Here]");
        fieldA.addMouseListener(new JTextFieldMouseListenerImpl());

        fieldB.setBounds(120, 130, 100, 25);
        fieldB.setText("[B Term Here]");
        fieldB.addMouseListener(new JTextFieldMouseListenerImpl());

        fieldC.setBounds(120, 160, 100, 25);
        fieldC.setText("[C Term Here]");
        fieldC.addMouseListener(new JTextFieldMouseListenerImpl());

        solveButton.setBounds(250, 130, 100, 25);
        solveButton.setText("Solve");
        solveButton.addActionListener(this::solveButtonActionListener);

        exitButton.setBounds(275, 325, 100, 25);
        exitButton.setText("Exit");
        exitButton.addActionListener(this::exitButtonActionListener);

        add(exitButton);
        add(solveButton);
        add(labelA);
        add(labelB);
        add(labelC);
        add(zeroesLabel);
        add(fieldA);
        add(fieldB);
        add(fieldC);
    }

    private void solveButtonActionListener(ActionEvent evt) {

        String text;

        try {

            double a = Double.parseDouble(fieldA.getText());
            double b = Double.parseDouble(fieldB.getText());
            double c = Double.parseDouble(fieldC.getText());

            double discriminant = b * b - 4.0d * a * c;
            double zero = (b + Math.sqrt(discriminant)) / (2.0d * a);

            if (discriminant == 0.0f) {

                text = String.format("Zero: %.2f", zero);

            } else {

                double zero2 = (b - Math.sqrt(discriminant)) / (2.0d * a);
                text = String.format("Zeroes: %.2f, %.2f", zero, zero2);
            }

        } catch (NumberFormatException e) {

            text = "Invalid Input";

        }

        zeroesLabel.setText(text);

    }

    private void exitButtonActionListener(ActionEvent evt) {
        this.dispose();
    }


    private static class JTextFieldMouseListenerImpl implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {}

        @Override
        public void mousePressed(MouseEvent e) {

            if (!(e.getComponent() instanceof JTextField)) return;
            ((JTextField) e.getComponent()).setText("");
        }

        @Override
        public void mouseReleased(MouseEvent e) {}

        @Override
        public void mouseEntered(MouseEvent e) {}

        @Override
        public void mouseExited(MouseEvent e) {}
    }

}
