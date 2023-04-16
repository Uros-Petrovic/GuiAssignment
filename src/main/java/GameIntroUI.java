import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.concurrent.ThreadLocalRandom;

public class GameIntroUI extends JFrame {

    private final JPanel optionMenu;
    private final JPanel mainMenu;
    private final JPanel gameMenu;

    private final JSlider maxSlider;
    private final JSlider minSlider;
    private final JButton mainMenuButton1;
    private final JLabel maxLabel;
    private final JLabel minLabel;

    private final JButton quitButton;
    private final JButton playButton;
    private final JButton optionsButton;

    private final JButton checkButton;
    private final JButton resetButton;
    private final JTextField numberField;
    private final JLabel infoLabel;
    private final JButton mainMenuButton2;

    public int gameInt = -1;
    public boolean playing = false;

    public static void main(String[] args) {

        new GameIntroUI().setVisible(true);

    }

    public GameIntroUI() {

        setSize(400, 400);
        setResizable(true);
        setTitle("Number Guessing Game");
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
                rescaleComponents(e.getComponent().getSize());
            }
        });

        mainMenu = new JPanel();
        optionMenu = new JPanel();
        gameMenu = new JPanel();

        gameMenu.setLayout(null);
        mainMenu.setLayout(null);
        optionMenu.setLayout(null);

        maxLabel = new JLabel();
        minLabel = new JLabel();

        optionsButton = new JButton();
        optionsButton.setText("Settings");
        optionsButton.addActionListener(e -> optionButtonActionListener());

        checkButton = new JButton();
        checkButton.addActionListener(e -> checkButtonActionListener());
        checkButton.setText("Check");

        resetButton = new JButton();
        resetButton.addActionListener(e -> resetButtonActionListener());
        resetButton.setText("Reset");

        numberField = new JTextField();
        numberField.setText("[Guess Here]");
        numberField.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {}

            @Override
            public void mousePressed(MouseEvent e) {
                numberFieldClickListener(e);
            }

            @Override
            public void mouseReleased(MouseEvent e) {}

            @Override
            public void mouseEntered(MouseEvent e) {}

            @Override
            public void mouseExited(MouseEvent e) {}
        });

        infoLabel = new JLabel();
        infoLabel.setText("Type a Guess and press 'check'!");

        maxSlider = new JSlider();
        maxSlider.setMinimum(1);
        maxSlider.setMaximum(100);
        maxSlider.setValue(10);

        minSlider = new JSlider();
        minSlider.setMinimum(0);
        minSlider.setMaximum(99);
        minSlider.setValue(0);

        maxSlider.addChangeListener(e -> {

            if (minSlider.getValue() >= maxSlider.getValue()) {
                minSlider.setValue(maxSlider.getValue() - 1);
            }

            maxLabel.setText("Maximum Value - " + maxSlider.getValue());
        });

        minSlider.addChangeListener(e -> {

            if (minSlider.getValue() >= maxSlider.getValue()) {
                maxSlider.setValue(minSlider.getValue() + 1);
            }

            minLabel.setText("Minimum Value - " + minSlider.getValue());

        });

        maxLabel.setText("Maximum Value - " + maxSlider.getValue());
        minLabel.setText("Minimum Value - " + minSlider.getValue());

        mainMenuButton1 = new JButton();
        mainMenuButton1.setText("Main Menu");
        mainMenuButton1.addActionListener(e -> mainMenuButtonActionListener());

        mainMenuButton2 = new JButton();
        mainMenuButton2.setText("Main Menu");
        mainMenuButton2.addActionListener(e -> mainMenuButtonActionListener());

        quitButton = new JButton();
        quitButton.setText("Quit");
        quitButton.addActionListener(e -> this.dispose());

        playButton = new JButton();
        playButton.setText("Play Game");
        playButton.addActionListener(e -> playButtonActionListener());

        mainMenu.add(playButton);
        mainMenu.add(quitButton);
        mainMenu.add(optionsButton);

        optionMenu.add(maxLabel);
        optionMenu.add(maxSlider);
        optionMenu.add(minLabel);
        optionMenu.add(minSlider);
        optionMenu.add(mainMenuButton1);

        gameMenu.add(numberField);
        gameMenu.add(infoLabel);
        gameMenu.add(checkButton);
        gameMenu.add(resetButton);
        gameMenu.add(mainMenuButton2);

        showMainMenu();
    }

    public void beginGame() {

        numberField.setText("[Guess Here]");
        infoLabel.setText("Type a Guess and press 'check'!");
        gameInt = ThreadLocalRandom.current().nextInt(minSlider.getValue(), maxSlider.getValue());
        playing = true;

    }

    private void numberFieldClickListener(MouseEvent e) {
        numberField.setText("");
    }

    private void resetButtonActionListener() {
        beginGame();
    }

    private void checkButtonActionListener() {

        if (!playing) return;

        String input = numberField.getText();

        int guess;

        try {

            guess = Integer.parseInt(input);

        } catch (NumberFormatException e) {

            infoLabel.setText("Invalid Value!");
            return;
        }

        if (guess > gameInt) {
            infoLabel.setText("Lower!");
        } else if (guess < gameInt) {
            infoLabel.setText("Higher!");
        } else {
            playing = false;
            infoLabel.setText("You got the answer: " + gameInt);
        }

    }

    public void rescaleComponents(Dimension d) {

        int height = d.height;
        int width = d.width;

        int obw = (int) ((float)width * 0.25f);
        int obh = (int) ((float)height / 32.0f);
        optionsButton.setBounds((width - obw) / 2, (height - obh) / 2, obw, obh);


        int sfxw = (int) ((float)width * 0.5f);
        int sfxh = 20;
        maxSlider.setBounds((width - sfxw) / 2, (height - sfxh) / 2, sfxw, sfxh);
        maxLabel.setBounds((width - sfxw) / 2, (height - sfxh) / 2 - 20, sfxw, sfxh);

        int musw = (int) ((float)width * 0.5f);
        int mush = 20;
        minSlider.setBounds((width - musw) / 2, (height - mush) / 2 - 40, musw, mush);
        minLabel.setBounds((width - musw) / 2, (height - mush) / 2 - 20 - 40, musw, mush);

        int mbw = (int) ((float)width * 0.25f);
        int mbh = (int) ((float)height / 32.0f);
        mainMenuButton1.setBounds(width / 32, height / 32, mbw, mbh);
        mainMenuButton2.setBounds(width / 32, height / 32, mbw, mbh);

        int qbw = (int) ((float)width * 0.25f);
        int qbh = (int) ((float)height / 32.0f);
        quitButton.setBounds((width - qbw) / 2, (height - qbh) / 2 + 2 * qbh, qbw, qbh);

        int pbw = (int) ((float)width * 0.25f);
        int pbh = (int) ((float)height / 32.0f);
        playButton.setBounds((width - pbw) / 2, (height - pbh) / 2 - 2 * pbh, pbw, pbh);

        int rbw = (int) ((float)width * 0.125f);
        int rbh = (int) ((float)height / 32.0f);
        resetButton.setBounds(width / 2 + width / 32, height / 2, rbw, rbh);

        int cbw = (int) ((float)width * 0.125f);
        int cbh = (int) ((float)height / 32.0f);
        checkButton.setBounds(width / 2 -  6 * width / 32, height / 2, cbw, cbh);

        int ilw = (int) ((float)width * 0.125f);
        int ilh = (int) ((float)height / 32.0f);
        infoLabel.setBounds(width / 2 -  6 * width / 32, height / 2 + ilh, ilw, ilh);

        int nfw = (int) ((float)width * 0.125f);
        int nfh = (int) ((float)height / 32.0f);
        numberField.setBounds(width / 2 -  6 * width / 32, height / 2 - nfh - height / 32, nfw, nfh);

    }

    public void hideAll() {

        for (Component c : this.getContentPane().getComponents()) {

            c.setVisible(false);

        }

    }

    private void playButtonActionListener() {
        showGameMenu();
    }

    private void mainMenuButtonActionListener() {
        showMainMenu();
    }

    private void optionButtonActionListener() {
        showOptionsMenu();
    }

    public void showGameMenu() {

        setContentPane(gameMenu);
        validate();
        repaint();

        beginGame();

    }

    public void showMainMenu() {

        setContentPane(mainMenu);
        validate();
        repaint();

    }

    public void showOptionsMenu() {

        setContentPane(optionMenu);
        validate();
        repaint();

    }

}
