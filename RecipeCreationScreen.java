package application;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.util.Dictionary;
import java.util.Hashtable;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import javax.swing.event.*;

/**
* This is the screen for recipe creation. It has all the selection options for the number<b>
* of ingredients in one cone of icecream. This has an effect on whether customers will buy <b>
* icecream and how many icecreams can be made. The player can also adjust the price of one <b>
* unit of icecream. <b>
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
public class RecipeCreationScreen extends JFrame implements ActionListener, ChangeListener {

    private JPanel panel = new JPanel();
    private JPanel recipePanel = new JPanel();

    private JLabel title = new JLabel("Create Your Recipe & Select a Price", SwingConstants.CENTER);
    private JLabel infoLabel = new JLabel(
            "<html><center>Pick how much of each ingredient you'd like in your ice cream recipe & set a price!<center/><html>");

    private Font startFont = new Font("Calibri", Font.BOLD, 36);
    private Font cashAndIngredientFont = new Font("Calibri", Font.BOLD, 48);
    private Font titleFont = new Font("Calibri", Font.BOLD, 96);

    private JButton playGameButton = new JButton("<html><center>Play Game<center/><html/>");

    private double nCreamInRecipe = 0, nVanillaInRecipe = 0, nSugarInRecipe = 0, priceOfCone = 0;

    private JLabel creamLabel = new JLabel("<html>Cream:&nbsp;<html/>" + String.format("%.1f", 2.0));
    private JLabel vanillaLabel = new JLabel("<html>Vanilla:&nbsp;<html/>" + String.format("%.1f", 2.0));
    private JLabel sugarLabel = new JLabel("<html>Sugar:&nbsp;<html/>" + String.format("%.1f", 2.0));
    private JLabel priceLabel = new JLabel("<html>Price:&nbsp;<html/>" + String.format("%.1f", 5.0));

    private JSlider creamSlider = new JSlider(SwingConstants.HORIZONTAL, 0, 40, 20);
    private JSlider vanillaSlider = new JSlider(SwingConstants.HORIZONTAL, 0, 40, 20);
    private JSlider sugarSlider = new JSlider(SwingConstants.HORIZONTAL, 0, 40, 20);
    private JSlider priceSlider = new JSlider(SwingConstants.HORIZONTAL, 0, 100, 50);
    private Player currentPlayer;
    private Clip sound1;
    /**
     * This constructor runs everything required in the RecipeCreationScreen. This
     * method runs the frameSetup and assembleWindow methods. This method also
     * catches exceptions thrown by these other helper methods.
     */
    public RecipeCreationScreen(Player currentPlayer) {
        try {
                this.currentPlayer = currentPlayer;
                currentPlayer.recipe.setCreamMes(2);
                currentPlayer.recipe.setSugarMes(2);
                currentPlayer.recipe.setVanillaMes(2);
                currentPlayer.recipe.setConePrice(5);

            frameSetup();
            assembleWindow();
            playMusic();
            recipePanelSetup();
            addSliders();
        } catch (IOException e) {
            System.out.println("Error: IOException, error code 5.1");
            e.printStackTrace();

        } catch (Exception e) {
            System.out.println("Error: Unknown exception, error code 5.2");
            e.printStackTrace();
        }
    }


    private void playMusic() {
        try {
            // create a new input stream and grab the file from the sounds folder
            AudioInputStream audio = AudioSystem
                    .getAudioInputStream(new File("files/MainGamePlayScreenMusic.wav").getAbsoluteFile());
            sound1 = AudioSystem.getClip();

            sound1.open(audio);
            sound1.start();
            sound1.loop(Clip.LOOP_CONTINUOUSLY);


        } catch (Exception ex) { // print in console if the clip doesn't work for whatever reason
            System.out.println("Error playing sound.");
            ex.printStackTrace();
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

        playGameButton.setBounds(745, 800, 400, 100);
        playGameButton.setFont(startFont);
        playGameButton.setBackground(Color.decode("#9FDBFE"));
        playGameButton.setForeground(Color.decode("#1D1128"));
        playGameButton.setFocusPainted(false);
        playGameButton.setHorizontalAlignment(SwingConstants.CENTER);
        playGameButton.setVerticalAlignment(SwingConstants.CENTER);
        playGameButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));
        playGameButton.addActionListener(this);

        recipePanel.setBounds(150, 125, 1600, 650);
        recipePanel.setLayout(null);
        recipePanel.setBackground(Color.decode("#FDC6D8"));
        recipePanel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));

        panel.add(recipePanel);
        panel.add(title);
        panel.add(playGameButton);

        panel.setVisible(true);
    }

    private void recipePanelSetup() {

        infoLabel.setBounds(0, 7, 1600, 100);
        infoLabel.setForeground(Color.BLACK);
        infoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        infoLabel.setVerticalAlignment(SwingConstants.CENTER);
        infoLabel.setFont(startFont);
        recipePanel.add(infoLabel);

        creamLabel.setBounds(0, 110, 300, 100);
        creamLabel.setFont(cashAndIngredientFont);
        creamLabel.setForeground(Color.BLACK);
        creamLabel.setHorizontalAlignment(SwingConstants.CENTER);
        creamLabel.setVerticalAlignment(SwingConstants.CENTER);
        recipePanel.add(creamLabel);

        vanillaLabel.setBounds(0, 250, 300, 100);
        vanillaLabel.setFont(cashAndIngredientFont);
        vanillaLabel.setForeground(Color.BLACK);
        vanillaLabel.setHorizontalAlignment(SwingConstants.CENTER);
        vanillaLabel.setVerticalAlignment(SwingConstants.CENTER);
        recipePanel.add(vanillaLabel);

        sugarLabel.setBounds(0, 390, 300, 100);
        sugarLabel.setFont(cashAndIngredientFont);
        sugarLabel.setForeground(Color.BLACK);
        sugarLabel.setHorizontalAlignment(SwingConstants.CENTER);
        sugarLabel.setVerticalAlignment(SwingConstants.CENTER);
        recipePanel.add(sugarLabel);

        priceLabel.setBounds(0, 530, 300, 100);
        priceLabel.setFont(cashAndIngredientFont);
        priceLabel.setForeground(Color.BLACK);
        priceLabel.setHorizontalAlignment(SwingConstants.CENTER);
        priceLabel.setVerticalAlignment(SwingConstants.CENTER);
        recipePanel.add(priceLabel);

    }

    @SuppressWarnings("rawtypes")
    private void addSliders() {

        @SuppressWarnings("unchecked")
        Dictionary<Integer, JLabel> labelTable = new Hashtable();

        for (double i = 0.0; i <= 100.0; i += 5.0) {
            String s = Double.toString(i / 10.0);
            labelTable.put((int) i, new JLabel(s));
        }

        creamSlider.setBounds(350, 110, 1085, 100);
        creamSlider.setFont(titleFont);
        creamSlider.setBackground(Color.decode("#9FDBFE"));
        creamSlider.setForeground(Color.decode("#1D1128"));
        creamSlider.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));
        creamSlider.addChangeListener(this);
        creamSlider.setLabelTable(labelTable);
        creamSlider.setPaintLabels(true);
        creamSlider.setMajorTickSpacing(1);
        creamSlider.setPaintTicks(true);
        recipePanel.add(creamSlider);

        vanillaSlider.setBounds(350, 250, 1085, 100);
        vanillaSlider.setFont(startFont);
        vanillaSlider.setBackground(Color.decode("#9FDBFE"));
        vanillaSlider.setForeground(Color.decode("#1D1128"));
        vanillaSlider.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));
        vanillaSlider.addChangeListener(this);
        vanillaSlider.setLabelTable(labelTable);
        vanillaSlider.setPaintLabels(true);
        vanillaSlider.setMajorTickSpacing(1);
        vanillaSlider.setPaintTicks(true);
        recipePanel.add(vanillaSlider);

        sugarSlider.setBounds(350, 390, 1085, 100);
        sugarSlider.setFont(startFont);
        sugarSlider.setBackground(Color.decode("#9FDBFE"));
        sugarSlider.setForeground(Color.decode("#1D1128"));
        sugarSlider.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));
        sugarSlider.addChangeListener(this);
        sugarSlider.setLabelTable(labelTable);
        sugarSlider.setPaintLabels(true);
        sugarSlider.setMajorTickSpacing(1);
        sugarSlider.setPaintTicks(true);
        recipePanel.add(sugarSlider);

        priceSlider.setBounds(350, 530, 1085, 100);
        priceSlider.setFont(startFont);
        priceSlider.setBackground(Color.decode("#9FDBFE"));
        priceSlider.setForeground(Color.decode("#1D1128"));
        priceSlider.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2, true));
        priceSlider.addChangeListener(this);
        priceSlider.setLabelTable(labelTable);
        priceSlider.setPaintLabels(true);
        priceSlider.setMajorTickSpacing(1);
        priceSlider.setPaintTicks(true);
        recipePanel.add(priceSlider);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == playGameButton) {
            setVisible(false);
            sound1.stop();
            sound1.close();
            new MainGamePlayClass(currentPlayer);
            new MainGameplayScreen(currentPlayer);

        }

    }

    @Override
    public void stateChanged(ChangeEvent e) {
        /** the recipe belonging to the player of the current save/load profile*/
        RecipeCreation pRecipe = currentPlayer.recipe;

        if (e.getSource() == creamSlider) {
            nCreamInRecipe = creamSlider.getValue();
            pRecipe.setCreamMes(nCreamInRecipe);
            creamLabel.setText("<html>Cream:&nbsp;<html/>" + String.format("%.1f", nCreamInRecipe / 10));
            pRecipe.setCreamMes(nCreamInRecipe / 10);

        } else if (e.getSource() == vanillaSlider) {
            nVanillaInRecipe = vanillaSlider.getValue();
            pRecipe.setVanillaMes(nVanillaInRecipe);
            vanillaLabel.setText("<html>Vanilla:&nbsp;<html/>" + String.format("%.1f", nVanillaInRecipe / 10));

            pRecipe.setSugarMes(nVanillaInRecipe / 10);

        } else if (e.getSource() == sugarSlider) {
            nSugarInRecipe = sugarSlider.getValue();
            pRecipe.setSugarMes(nSugarInRecipe);
            sugarLabel.setText("<html>Sugar:&nbsp;<html/>" + String.format("%.1f", nSugarInRecipe / 10));

            pRecipe.setVanillaMes(nSugarInRecipe / 10);

        } else if (e.getSource() == priceSlider) {
            priceOfCone = priceSlider.getValue();
            priceLabel.setText("<html>Price:&nbsp;<html/>" + String.format("%.1f", priceOfCone / 10));
            pRecipe.setConePrice(priceOfCone / 10);
        }

    }
}