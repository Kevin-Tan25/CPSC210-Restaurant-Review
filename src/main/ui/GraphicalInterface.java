package ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
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

        JPanel layoutWelcome = new JPanel(new GridBagLayout());
        layoutWelcome.setBorder(new EmptyBorder(5, 5, 5, 5));
        JLabel welcomeMessage = new JLabel();
        welcomeMessage.setText("Welcome to the Restaurant Review App! How can I help you today?");
//        welcomeMessage.setBounds(10,0, 500, 50);
        layoutWelcome.add(welcomeMessage);

        JPanel layout = new JPanel(new GridBagLayout());
        layout.setBorder(new EmptyBorder(5, 5, 5, 5));
        JPanel buttonPane = new JPanel(new GridLayout(10, 1, 10, 5));
        buttonPane.add(new JButton("View my account"));
        buttonPane.add(new JButton("Write a review"));
        buttonPane.add(new JButton("View top restaurants"));
        buttonPane.add(new JButton("Search restaurant reviews"));
        buttonPane.add(new JButton("Save reviews to file"));
        buttonPane.add(new JButton("Load reviews from file"));
        buttonPane.add(new JButton("Quit"));
        layout.add(buttonPane);

        frameInterface = new JFrame();
        frameInterface.setLayout(new BorderLayout());
        frameInterface.setMinimumSize(new Dimension(WIDTH, HEIGHT));

//        welcomeMessage = new WelcomeMessage();
//        menu = new MenuComponent();

        frameInterface.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameInterface.setLocationRelativeTo(null);
        frameInterface.setVisible(true);
        frameInterface.add(welcomeMessage, BorderLayout.NORTH);
        frameInterface.add(layout, BorderLayout.WEST);
        frameInterface.setBackground(new Color(180, 180, 180));

        layoutWelcome.revalidate();
        layout.revalidate();
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.

        new GraphicalInterface();
    }
}
