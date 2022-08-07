package ui;

import model.RatedRestaurants;
import model.Restaurant;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Represents the main window for restaurantReviewApp
public class GraphicalInterface extends JFrame {

    // The dimensions of the frame of the application
    public static final int WIDTH = 900;
    public static final int HEIGHT = 700;

    private RatedRestaurants allLoggedRestaurants = new RatedRestaurants();

    private JFrame frameInterface;
    private JList<Restaurant> listRestaurants;
    private DefaultListModel<Restaurant> modelRestaurants = new DefaultListModel<>();
    private GridBagConstraints gbc = new GridBagConstraints();
    private JSplitPane splitPane = new JSplitPane();

    public GraphicalInterface() {
        super("Restaurant Review");
        initializeFrame();
        initializeMenu();
        initializeList();

        splitPane.revalidate();
    }

    private void initializeMenu() {
        JPanel layout = new JPanel(new BorderLayout());

        JLabel welcomeMessage = getWelcomeMessage();
        JPanel menuButtonLayout = new JPanel();
        menuButtonLayout.setBorder(new EmptyBorder(5, 5, 5, 5));
        JPanel buttonPane = new JPanel(new GridLayout(10, 1, 10, 5));
        buttonPane.add(new JButton("View my account"));

        JButton addReviewButton = new JButton("Write a review");
        AddReviewListener addReview = new AddReviewListener(addReviewButton);
        addReviewButton.addActionListener(addReview);
        buttonPane.add(addReviewButton);

        JButton viewTopRestaurantsButton = new JButton("View top restaurants");
        ViewTopRestaurantsListener viewTopRestaurants = new ViewTopRestaurantsListener(viewTopRestaurantsButton);
        addReviewButton.addActionListener(viewTopRestaurants);
        buttonPane.add(viewTopRestaurantsButton);

        buttonPane.add(new JButton("Search restaurant reviews"));
        buttonPane.add(new JButton("Save reviews to file"));
        buttonPane.add(new JButton("Load reviews from file"));
        buttonPane.add(new JButton("Quit"));
        menuButtonLayout.add(buttonPane);

        layout.add(welcomeMessage, BorderLayout.NORTH);
        layout.add(menuButtonLayout, BorderLayout.SOUTH);

        splitPane.setLeftComponent(layout);
        layout.revalidate();
    }

    private void initializeList() {

        System.out.println(allLoggedRestaurants.getTopRestaurants().size());
        for (Restaurant r: allLoggedRestaurants.getTopRestaurants()) {
            modelRestaurants.addElement(r);
        }
        listRestaurants.setModel(modelRestaurants);
        JScrollPane scrollPane = new JScrollPane(listRestaurants);
        splitPane.setRightComponent(scrollPane);
        listRestaurants.revalidate();
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
        frameInterface.add(splitPane);
    }

    private JLabel getWelcomeMessage() {
        JLabel welcomeMessage = new JLabel();
        welcomeMessage.setText("<html>Welcome to the Restaurant Review App!<br>How can I help you today?</html>");
        gbc.gridx = 0;
        gbc.gridy = 0;
        frameInterface.add(welcomeMessage);
        return welcomeMessage;
    }

    class AddReviewListener implements ActionListener {
        private JButton button;

        public AddReviewListener(JButton button) {
            this.button = button;
        }

        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    class ViewTopRestaurantsListener implements ActionListener {
        private JButton button;

        public ViewTopRestaurantsListener(JButton button) {
            this.button = button;
        }

        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }


    public static void main(String[] args) {

        new GraphicalInterface();
    }
}
