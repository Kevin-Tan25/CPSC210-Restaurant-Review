package ui;

import model.RatedRestaurants;
import model.Restaurant;
import model.Review;
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
    public static final int WIDTH = 600;
    public static final int HEIGHT = 500;

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
    private JList<String> listRestaurants = new JList<String>();
    private DefaultListModel<String> modelRestaurants = new DefaultListModel<String>();
    private JList<String> listReviews = new JList<String>();
    private DefaultListModel<String> modelReviews = new DefaultListModel<String>();
    private JSplitPane splitPane = new JSplitPane();

    public GraphicalInterface() throws FileNotFoundException {
        super("Restaurant Review");
        initializePersistence();
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

        JButton viewMyReviewsButton = new JButton("View my reviews");
        ViewReviewsListener viewReviews = new ViewReviewsListener(viewMyReviewsButton);
        viewMyReviewsButton.addActionListener(viewReviews);
        buttonPane.add(viewMyReviewsButton);

        JButton addReviewButton = new JButton("Write a review");
        AddReviewListener addReview = new AddReviewListener(addReviewButton);
        addReviewButton.addActionListener(addReview);
        buttonPane.add(addReviewButton);

        topRestaurantsButton(buttonPane);

        JButton saveReviewsButton = new JButton("Save reviews to file");
        SaveReviewsListener saveReviews = new SaveReviewsListener(saveReviewsButton);
        saveReviewsButton.addActionListener(saveReviews);
        buttonPane.add(saveReviewsButton);

        JButton loadReviewsButton = new JButton("Load reviews from file");
        LoadReviewsListener loadReviews = new LoadReviewsListener(loadReviewsButton);
        loadReviewsButton.addActionListener(loadReviews);
        buttonPane.add(loadReviewsButton);

        buttonPane.add(new JButton("Quit"));
        menuButtonLayout.add(buttonPane);

        layout.add(welcomeMessage);
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
        frameInterface.setLayout(new BorderLayout());
        frameInterface.setMinimumSize(new Dimension(WIDTH, HEIGHT));
        splitPane.setSize(new Dimension(WIDTH, HEIGHT));
        frameInterface.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameInterface.setLocationRelativeTo(null);
        frameInterface.setVisible(true);
        frameInterface.setBackground(new Color(180, 180, 180));
        frameInterface.add(splitPane, BorderLayout.CENTER);

        JLabel selectOption = new JLabel("Please select an action to perform.");
        splitPane.setRightComponent(selectOption);
    }

    private JLabel getWelcomeMessage() {
        JLabel welcomeMessage = new JLabel();
        welcomeMessage.setText("<html>Welcome to the Restaurant Review App!<br>How can I help you today?</html>");
        frameInterface.add(welcomeMessage);
        return welcomeMessage;
    }

    class AddReviewListener implements ActionListener {
        private JButton button;
        JTextField restaurantNameTextField;
        JTextField restaurantLocationTextField;
        JTextField ratingTextField;
        JTextField costTextField;
        JTextField reviewCommentTextField;

        public AddReviewListener(JButton button) {
            this.button = button;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            JPanel layout = new JPanel(new GridBagLayout());
            JPanel inputFieldPane = new JPanel(new GridLayout(11, 1));

            inputFieldPane.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

            JLabel restaurantName = new JLabel("Input restaurant name:");
            restaurantNameTextField = new JTextField();
            restaurantNameTextField.setPreferredSize(new Dimension(70, 15));

            JLabel restaurantLocation = new JLabel("Input restaurant location:");
            restaurantLocationTextField = new JTextField();
            restaurantLocationTextField.setPreferredSize(new Dimension(70, 15));

            JLabel rating = new JLabel("Please provide a rating (out of 5):");
            ratingTextField = new JTextField();
            ratingTextField.setPreferredSize(new Dimension(70, 15));

            JLabel cost = new JLabel("Please provide a cost (without $ signs):");
            costTextField = new JTextField();
            costTextField.setPreferredSize(new Dimension(70, 15));

            JLabel reviewComment = new JLabel("Please provide a comment:");
            reviewCommentTextField = new JTextField();
            reviewComment.setPreferredSize(new Dimension(70, 15));

            JButton submitButton = new JButton("Submit");
            SubmitButtonListener submitReview = new SubmitButtonListener(submitButton);
            submitButton.addActionListener(submitReview);



            inputFieldPane.add(restaurantName);
            inputFieldPane.add(restaurantNameTextField);
            inputFieldPane.add(restaurantLocation);
            inputFieldPane.add(restaurantLocationTextField);
            inputFieldPane.add(rating);
            inputFieldPane.add(ratingTextField);
            inputFieldPane.add(cost);
            inputFieldPane.add(costTextField);
            inputFieldPane.add(reviewComment);
            inputFieldPane.add(reviewCommentTextField);
            inputFieldPane.add(submitButton);

            layout.add(inputFieldPane);

            splitPane.setRightComponent(layout);
            layout.revalidate();
        }

        class SubmitButtonListener implements ActionListener {
            private JButton button;

            public SubmitButtonListener(JButton button) {
                this.button = button;
            }

            @Override
            public void actionPerformed(ActionEvent e) {
                String restaurantName = restaurantNameTextField.getText();
                String restaurantLocation = restaurantLocationTextField.getText();
                int rating = Integer.parseInt(ratingTextField.getText());
                double cost = Double.parseDouble(costTextField.getText());
                String reviewComment = reviewCommentTextField.getText();
                Restaurant restaurant = new Restaurant(restaurantName, restaurantLocation);
                Review review = new Review(restaurant,rating,cost,reviewComment);
                restaurant.addReview(review);
                user.addReview(review);
                allLoggedRestaurants.addRestaurant(restaurant);
                JLabel successfullyAdded = new JLabel("Successfully added!");
                splitPane.setRightComponent(successfullyAdded);
            }
        }
    }

    class ViewTopRestaurantsListener implements ActionListener {
        private JButton button;

        public ViewTopRestaurantsListener(JButton button) {
            this.button = button;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            modelRestaurants = new DefaultListModel<>();
            JPanel layout = new JPanel(new BorderLayout());
            JLabel topFiveRestaurants = new JLabel("Here are your top 5 restaurant recommendations:");

            if (allLoggedRestaurants.getTopRestaurants().size() == 0) {
                JLabel noRestaurantsDisplay = new JLabel("No restaurant reviews yet.");
                layout.add(noRestaurantsDisplay, BorderLayout.CENTER);
            } else {
                layout.add(topFiveRestaurants, BorderLayout.NORTH);
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

    class ViewReviewsListener implements ActionListener {
        private JButton button;

        public ViewReviewsListener(JButton button) {
            this.button = button;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            modelReviews = new DefaultListModel<>();
            JPanel layout = new JPanel(new BorderLayout());
            JLabel yourReviewsLabel = new JLabel("Here are your reviews:");

            if (user.getMyReviews().size() == 0) {
                JLabel noRestaurantsDisplay = new JLabel("You have no reviews yet.");
                layout.add(noRestaurantsDisplay, BorderLayout.CENTER);
            } else {
                layout.add(yourReviewsLabel, BorderLayout.NORTH);
                for (int i = 0; i < user.getMyReviews().size(); i++) {
                    addUserReviewsToList(i);
                }
                listReviews.setModel(modelReviews);
                JScrollPane scrollPane = new JScrollPane(listReviews);
                layout.add(scrollPane);
            }
            splitPane.setRightComponent(layout);
            layout.revalidate();
        }

        private void addUserReviewsToList(int i) {
            Review userReview = user.getMyReviews().get(i);
            modelReviews.addElement(userReview.toString());
        }
    }

    class LoadReviewsListener implements ActionListener {
        private JButton button;

        public LoadReviewsListener(JButton button) {
            this.button = button;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            modelReviews = new DefaultListModel<>();
            JLabel loadReviewMessage = new JLabel("");
            try {
                user = jsonReaderUser.read();
                allLoggedRestaurants = jsonReaderAllRestaurants.read();
                loadReviewMessage = new JLabel("Loaded restaurant reviews: " + JSON_USER_REVIEWS);
            } catch (IOException exception) {
                loadReviewMessage = new JLabel("Unable to load reviews: " + JSON_USER_REVIEWS);
            } finally {
                splitPane.setRightComponent(loadReviewMessage);
                loadReviewMessage.revalidate();
            }
        }
    }

    class SaveReviewsListener implements ActionListener {
        private JButton button;

        public SaveReviewsListener(JButton button) {
            this.button = button;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            JLabel saveReviewMessage = new JLabel("");
            try {
                jsonWriterUser.open();
                jsonWriterUser.write(user);
                jsonWriterUser.close();
                jsonWriterAllRestaurants.open();
                jsonWriterAllRestaurants.write(allLoggedRestaurants);
                jsonWriterAllRestaurants.close();
                saveReviewMessage = new JLabel("Saved restaurant reviews: " + JSON_USER_REVIEWS);
            } catch (FileNotFoundException exception) {
                saveReviewMessage = new JLabel("Unable to write to file: " + JSON_USER_REVIEWS);
            } finally {
                splitPane.setRightComponent(saveReviewMessage);
                saveReviewMessage.revalidate();
            }
        }
    }
}
