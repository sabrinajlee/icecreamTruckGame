package application;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

/**
* This is the screen to teach the user how to play the game. 
* <p>
* This class uses Java Swing to implement GUI elements.
* Music is original, created by Ariana Feng using Soundtrap
*<p>
*
* @author Lukas Bozinov
* @author Ariana Feng
* CS2212 Spring 2024 term
* Group 48
* Prof. Servos
* Monday April 1, 2024
*/
@SuppressWarnings("serial")
public class TutorialScreen extends JFrame implements ActionListener {

    private JPanel panel = new JPanel();
    private JPanel bgPanel = new JPanel();
    private JPanel tutPanel = new JPanel();

    private JLabel title = new JLabel("Tutorial Instructions", SwingConstants.CENTER);
    private JLabel tutorialInstructions = new JLabel(
            "<html><p>Welcome to \"Ice Cream Truck Tycoon\"! In this exciting game, you get to run your "
                    + "very own ice cream truck and become the ultimate frozen treat mogul. Your goal is to satisfy "
                    + "the sweet cravings of the neighborhood while managing your resources wisely to maximize profits.<br><br>&nbsp;- First, load or create your game profile. "
                    + "Once that's done, it's time to pick your ingredients. Choose from a variety of ice cream essentials to create the perfect menu for your customers.<br><br>"
                    + "&nbsp;- Next, create your signature recipes. Experiment with different combinations of ingredients until you find the perfect mix that will "
                    + "keep your customers coming back for more.<br><br>"
                    + "&nbsp;- Finally, once your recipes are set, it's time to set your prices. Find the sweet spot that attracts customers while ensuring you make a profit.<br><br>"
                    + "With your ingredients, recipes, and prices all set, you're ready to hit the streets and start serving up delicious treats to your eager customers."
                    + "&nbsp;So put on your apron, fire up the freezer, and get ready to become the ultimate ice cream trucker!</p></html>");

    private JLabel topPicture = new JLabel(new ImageIcon("files/SpriteGold1.png"));
    private JLabel bottomPicture = new JLabel(new ImageIcon("files/SpriteGold1.png"));

    private Font startFont = new Font("Calibri", Font.BOLD, 36);
    private Font tutFont = new Font("Calibri", Font.BOLD, 25);
    private Font titleFont = new Font("Calibri", Font.BOLD, 96);
    private JButton execute = new JButton("OK");

    /**
     * This constructor runs everything required in the TitleScreen. This method
     * runs the frameSetup and assembleWindow methods. This method also catches
     * exceptions thrown by these other helper methods.
     */
    public TutorialScreen() {
        try {
            frameSetup();
            assembleWindow();
        } catch (IOException e) {
            System.out.println("Error: IOException, error code T.1");
            e.printStackTrace();

        } catch (Exception e) {
            System.out.println("Error: Unknown exception, error code T.2");
            e.printStackTrace();
        }
    }

    /**
     * This helper method sets up the basics of the JFrame that this class extends.
     * Sets the size of the window, makes it unresizable, and sets titles as well as
     * layout/decorations.
     *
     * @throws IOException
     */
    private void frameSetup() throws IOException {

        setSize(1920, 1080); // set size of the window to 1920x1080
        setLayout(null); // set to default layout (flow layout)
        setTitle("Ice Cream Truck Tycoon - Lukas, Sabrina, Kevin, Matthew, & Ariana"); // set the title of the window
        setResizable(false); // disallow resizing the window

        setIconImage(ImageIO.read(new File("files/icecreamcone.png")));

        setDefaultCloseOperation(TitleScreen.EXIT_ON_CLOSE);
        setVisible(true);
    }

    // assembles basic parts of the window
    private void assembleWindow() {

        panel.setBounds(0, 0, 1920, 1080);
        panel.setLayout(null);
        panel.setBackground(Color.decode("#7CF3A0"));

        add(panel);

        title.setBounds(230, 31, 1440, 100);
        title.setForeground(Color.BLACK);
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setVerticalAlignment(SwingConstants.CENTER);
        title.setFont(titleFont);

        execute.setBounds(745, 800, 400, 100);
        execute.setFont(startFont);
        execute.setBackground(Color.decode("#9FDBFE"));
        execute.setForeground(Color.decode("#1D1128"));
        execute.setFocusPainted(false);
        execute.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));
        execute.addActionListener(this);

        bgPanel.setBounds(125, 125, 1625, 650);
        bgPanel.setLayout(null);
        bgPanel.setBackground(Color.decode("#FDC6D8"));
        bgPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));

        tutPanel.setBounds(50, 50, 1200, 550);
        tutPanel.setLayout(null);
        tutPanel.setBackground(Color.decode("#FDC6D8"));
        tutPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));

        topPicture.setBounds(1300, 17, 300, 300);
        bottomPicture.setBounds(1300, 331, 300, 300);

        panel.add(bgPanel);
        panel.add(title);
        panel.add(execute);
        bgPanel.add(tutPanel);
        bgPanel.add(topPicture);
        bgPanel.add(bottomPicture);

        tutorialInstructions.setBounds(5, 5, 1195, 545);
        tutorialInstructions.setForeground(Color.BLACK);
        tutorialInstructions.setFont(tutFont);

        tutPanel.add(tutorialInstructions);
        tutorialInstructions.repaint();

        panel.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == execute) {
            setVisible(false);
            TitleScreen.panel.requestFocus();
        }

    }
}