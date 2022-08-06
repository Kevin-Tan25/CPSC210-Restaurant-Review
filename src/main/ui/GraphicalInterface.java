package ui;

import javax.swing.*;
import java.awt.*;

// Represents the main window for restaurantReviewApp
public class GraphicalInterface extends JFrame {

    // The dimensions of the frame of the application
    public static final int WIDTH = 1000;
    public static final int HEIGHT = 700;

    private JFrame frameInterface;
    private MenuComponent menu;

    public GraphicalInterface() {
        super("Restaurant Review");
        initializeGraphics();
//        displayWelcomeMessage();
    }

    // EFFECTS: Greets the User
    private void displayWelcomeMessage() {
        JLabel welcomeMessage = new JLabel();
        welcomeMessage.setText("Welcome to the Restaurant Review App! \n How can I help you today?");
        frameInterface.add(welcomeMessage);
    }

    // MODIFIES: this
    // EFFECTS: creates the JFrame for the restaurant app
    private void initializeGraphics() {

        JLabel welcomeMessage = new JLabel();
        welcomeMessage.setText("Welcome to the Restaurant Review App! How can I help you today?");
        welcomeMessage.setBounds(0,0, 500, 50);

        frameInterface = new JFrame();
        frameInterface.setLayout(new BorderLayout());
        frameInterface.setMinimumSize(new Dimension(WIDTH, HEIGHT));



//        welcomeMessage = new WelcomeMessage();
//        menu = new MenuComponent();

        frameInterface.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameInterface.setLocationRelativeTo(null);
        frameInterface.setVisible(true);
        frameInterface.add(welcomeMessage);
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.

        new GraphicalInterface();
    }
}
