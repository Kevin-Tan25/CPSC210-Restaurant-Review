package ui;

import model.RatedRestaurants;
import model.Restaurant;
import model.User;
import persistence.JsonReaderAllRestaurants;
import persistence.JsonReaderUser;
import persistence.JsonWriterAllRestaurants;
import persistence.JsonWriterUser;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

// Represents the main window for restaurantReviewApp
public class GraphicalInterface extends JFrame {

    // The dimensions of the frame of the application
    public static final int WIDTH = 900;
    public static final int HEIGHT = 700;

    // Reader and writer files and initialization
    private static final String JSON_USER_REVIEWS = "./data/reviews.json";
    private static final String JSON_ALL_RESTAURANTS = "./data/allReviews.json";
    private JsonWriterUser jsonWriterUser;
    private JsonReaderUser jsonReaderUser;
    private JsonWriterAllRestaurants jsonWriterAllRestaurants;
    private JsonReaderAllRestaurants jsonReaderAllRestaurants;

    private User user;
    private RatedRestaurants allLoggedRestaurants = new RatedRestaurants();

    private JFrame frameInterface;
    private JList listRestaurants = new JList();
    private DefaultListModel modelRestaurants = new DefaultListModel();
    private GridBagConstraints gbc = new GridBagConstraints();
    private JSplitPane splitPane = new JSplitPane();

    public GraphicalInterface() {
        super("Restaurant Review");
        try {
            initializePersistence();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to run application: file not found");
        }
        initializeUsers();
        initializeFrame();
        initializeMenu();

        splitPane.revalidate();
    }

    private void initializeUsers() {
        user = new User("Kevin");
    }

    private void initializePersistence() throws FileNotFoundException {
        jsonWriterUser = new JsonWriterUser(JSON_USER_REVIEWS);
        jsonReaderUser = new JsonReaderUser(JSON_USER_REVIEWS);
        jsonWriterAllRestaurants = new JsonWriterAllRestaurants(JSON_ALL_RESTAURANTS);
        jsonReaderAllRestaurants = new JsonReaderAllRestaurants(JSON_ALL_RESTAURANTS);
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

        topRestaurantsButton(buttonPane);

        buttonPane.add(new JButton("Search restaurant reviews"));

//        JButton saveReviewsButton = new JButton("Save reviews to file");
//        SaveReviewsListener saveReviews = new SaveReviewsListener(saveReviewsButton);
//        saveReviewsButton.addActionListener(saveReviews);
//        buttonPane.add(saveReviewsButton);

        JButton loadReviewsButton = new JButton("Load reviews from file");
        LoadReviewsListener loadReviews = new LoadReviewsListener(loadReviewsButton);
        loadReviewsButton.addActionListener(loadReviews);
        buttonPane.add(loadReviewsButton);

        buttonPane.add(new JButton("Quit"));
        menuButtonLayout.add(buttonPane);

        layout.add(welcomeMessage, BorderLayout.NORTH);
        layout.add(menuButtonLayout, BorderLayout.SOUTH);

        splitPane.setLeftComponent(layout);
        layout.revalidate();
    }

    private void topRestaurantsButton(JPanel buttonPane) {
        JButton viewTopRestaurantsButton = new JButton("View top restaurants");
        ViewTopRestaurantsListener viewTopRestaurants = new ViewTopRestaurantsListener(viewTopRestaurantsButton);
        viewTopRestaurantsButton.addActionListener(viewTopRestaurants);
        buttonPane.add(viewTopRestaurantsButton);
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

        JLabel selectOption = new JLabel("Please select an action to perform.");
        splitPane.setRightComponent(selectOption);
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
            JPanel layout = new JPanel(new BorderLayout());
            JLabel topFiveRestaurants = new JLabel("Here are your top 5 restaurant recommendations:");
            layout.add(topFiveRestaurants, BorderLayout.NORTH);
            if (allLoggedRestaurants.getTopRestaurants().size() == 0) {
                JLabel noRestaurantsDisplay = new JLabel("No restaurant reviews yet.");
                layout.add(noRestaurantsDisplay);
            } else {
                if (allLoggedRestaurants.getTopRestaurants().size() <= 5) {
                    for (int i = 0; i < allLoggedRestaurants.getTopRestaurants().size(); i++) {
                        addTopRestaurantsToList(i);
                    }
                } else {
                    for (int i = 0; i < 5; i++) {
                        addTopRestaurantsToList(i);
                    }
                }
                listRestaurants.setModel(modelRestaurants);
                JScrollPane scrollPane = new JScrollPane(listRestaurants);
                layout.add(scrollPane);
            }
            splitPane.setRightComponent(layout);
            layout.revalidate();
        }

        private void addTopRestaurantsToList(int i) {
            Restaurant topRestaurant = allLoggedRestaurants.getTopRestaurants().get(i);
            modelRestaurants.addElement("<html>" + topRestaurant
                    + "<br>The average rating is:" + topRestaurant.getAverageRating()
                    + "<br>The average cost is: $" + topRestaurant.getAverageCost()
                    + "<br>---");
        }
    }

    class LoadReviewsListener implements ActionListener {
        private JButton button;

        public LoadReviewsListener(JButton button) {
            this.button = button;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            JLabel loadReviewMessage = new JLabel("");
            try {
                user = jsonReaderUser.read();
                allLoggedRestaurants = jsonReaderAllRestaurants.read();
                loadReviewMessage = new JLabel("Loaded restaurant reviews!");
            } catch (IOException exception) {
                System.out.println("Unable to read from file: " + JSON_USER_REVIEWS);
                loadReviewMessage = new JLabel("Unable to load reviews.");
            } finally {
                splitPane.setRightComponent(loadReviewMessage);
                loadReviewMessage.revalidate();
            }
        }
    }


    public static void main(String[] args) {
        new GraphicalInterface();
    }
}
