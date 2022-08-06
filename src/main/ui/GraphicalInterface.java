package ui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

// Represents the main window for restaurantReviewApp
public class GraphicalInterface extends JFrame {

    // The dimensions of the frame of the application
    public static final int WIDTH = 700;
    public static final int HEIGHT = 700;

    private JFrame frameInterface;
    private GridBagConstraints gbc = new GridBagConstraints();

    public GraphicalInterface() {
        super("Restaurant Review");
        initializeFrame();
    }

    // MODIFIES: this
    // EFFECTS: creates the JFrame for the restaurant app
    private void initializeFrame() {

        frameInterface = new JFrame();
        frameInterface.setLayout(new GridBagLayout());
        frameInterface.setMinimumSize(new Dimension(WIDTH, HEIGHT));
        frameInterface.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameInterface.setLocationRelativeTo(null);
        frameInterface.setVisible(true);
        frameInterface.setBackground(new Color(180, 180, 180));

        JLabel welcomeMessage = getWelcomeMessage();

        JPanel buttonPane = getMenuPanel();

        buttonPane.revalidate();
        welcomeMessage.revalidate();
    }

    private JPanel getMenuPanel() {
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
        layout.add(buttonPane, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        frameInterface.add(layout, gbc);
        return buttonPane;
    }

    private JLabel getWelcomeMessage() {
        JLabel welcomeMessage = new JLabel();
        welcomeMessage.setText("<html>Welcome to the Restaurant Review App!<br>How can I help you today?</html>");
        gbc.gridx = 0;
        gbc.gridy = 0;
        frameInterface.add(welcomeMessage);
        return welcomeMessage;
    }

    public static void main(String[] args) {

        new GraphicalInterface();
    }
}
