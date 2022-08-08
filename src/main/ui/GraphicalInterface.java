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


    private static final String JSON_USER_REVIEWS = "./data/reviews.json";
    private static final String JSON_ALL_RESTAURANTS = "./data/allReviews.json";

    // Reader and writer files and initialization
    private JsonWriterUser jsonWriterUser;
    private JsonReaderUser jsonReaderUser;
    private JsonWriterAllRestaurants jsonWriterAllRestaurants;
    private JsonReaderAllRestaurants jsonReaderAllRestaurants;

    private User user;
    private RatedRestaurants allLoggedRestaurants;

    private JFrame frameInterface;
    private JList<String> listRestaurants;
    private DefaultListModel<String> modelRestaurants;
    private JList<String> listReviews;
    private DefaultListModel<String> modelReviews;
    private JSplitPane splitPane;

    private ImageIcon image;
    private Image newImage;
    private ImageIcon newImageIcon;

    // EFFECTS: sets up the GUI for restaurant application
    public GraphicalInterface() throws FileNotFoundException {
        super("Restaurant Review");
        initializeJComponents();
        initializePersistence();
        initializeClassObjects();
        initializeFrame();
        initializeMenu();
    }

    // MODIFIES: this
    // EFFECTS: initializes objects in classes and JComponents: model, list, and splitPane
    private void initializeJComponents() {
        modelRestaurants = new DefaultListModel<>();
        modelReviews = new DefaultListModel<>();
        listReviews = new JList<>();
        listRestaurants = new JList<>();
        splitPane = new JSplitPane();
        image = new ImageIcon(getClass().getResource("checkmark.png"));
        newImage = image.getImage().getScaledInstance(50, 50, Image.SCALE_DEFAULT);
        newImageIcon = new ImageIcon(newImage);
    }

    // MODIFIES: user
    // EFFECTS: initializes users that are using the application
    private void initializeClassObjects() {
        allLoggedRestaurants = new RatedRestaurants();
        user = new User("Kevin");
    }

    // MODIFIES: JsonWriterUser, JsonReaderUser, JsonWriterAllRestaurants, JsonReaderAllRestaurants
    // EFFECTS: initializes Json readers and writers in app
    private void initializePersistence() throws FileNotFoundException {
        jsonWriterUser = new JsonWriterUser(JSON_USER_REVIEWS);
        jsonReaderUser = new JsonReaderUser(JSON_USER_REVIEWS);
        jsonWriterAllRestaurants = new JsonWriterAllRestaurants(JSON_ALL_RESTAURANTS);
        jsonReaderAllRestaurants = new JsonReaderAllRestaurants(JSON_ALL_RESTAURANTS);
    }

    // MODIFIES: this
    // EFFECTS: creates menu of actions available in application
    private void initializeMenu() {
        JPanel layout = new JPanel(new BorderLayout());

        JLabel welcomeMessage = getWelcomeMessage();
        JPanel menuButtonLayout = new JPanel();
        menuButtonLayout.setBorder(new EmptyBorder(5, 5, 5, 5));
        JPanel buttonPane = new JPanel(new GridLayout(10, 1, 10, 5));

        viewMyReviews(buttonPane);
        addReview(buttonPane);
        viewTopRestaurants(buttonPane);
        saveReviews(buttonPane);
        loadReviews(buttonPane);

        JButton quitButton = new JButton("Quit");
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        buttonPane.add(quitButton);

        menuButtonLayout.add(buttonPane);

        layout.add(welcomeMessage);
        layout.add(menuButtonLayout, BorderLayout.SOUTH);

        splitPane.setLeftComponent(layout);
        layout.revalidate();
    }

    private void loadReviews(JPanel buttonPane) {
        JButton loadReviewsButton = new JButton("Load Reviews from File");
        LoadReviewsListener loadReviews = new LoadReviewsListener();
        loadReviewsButton.addActionListener(loadReviews);
        buttonPane.add(loadReviewsButton);
    }

    private void saveReviews(JPanel buttonPane) {
        JButton saveReviewsButton = new JButton("Save Reviews to File");
        SaveReviewsListener saveReviews = new SaveReviewsListener();
        saveReviewsButton.addActionListener(saveReviews);
        buttonPane.add(saveReviewsButton);
    }

    private void addReview(JPanel buttonPane) {
        JButton addReviewButton = new JButton("Write a Review");
        AddReviewListener addReview = new AddReviewListener();
        addReviewButton.addActionListener(addReview);
        buttonPane.add(addReviewButton);
    }

    private void viewMyReviews(JPanel buttonPane) {
        JButton viewMyReviewsButton = new JButton("View my Reviews");
        ViewReviewsListener viewReviews = new ViewReviewsListener();
        viewMyReviewsButton.addActionListener(viewReviews);
        buttonPane.add(viewMyReviewsButton);
    }

    // MODIFIES: this
    // EFFECTS: when button clicked will display top restaurants
    private void viewTopRestaurants(JPanel buttonPane) {
        JButton viewTopRestaurantsButton = new JButton("View Top Restaurants");
        ViewTopRestaurantsListener viewTopRestaurants = new ViewTopRestaurantsListener();
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

    // EFFECTS: displays a welcome message for the user
    private JLabel getWelcomeMessage() {
        JLabel welcomeMessage = new JLabel();
        welcomeMessage.setText("<html>Welcome to the Restaurant Review App!<br>How can I help you today, "
                + user.getUserName() + "?</html>");
        frameInterface.add(welcomeMessage);
        return welcomeMessage;
    }

    // MODIFIES: allLoggedRestaurants, user
    // EFFECTS: creates a feedback form with fields to input review
    //          when clicked on submit:
    //          creates and adds a review to user's written reviews
    //          creates and adds a restaurant to allRestaurants list
    class AddReviewListener implements ActionListener {
        private JButton submitButton;
        JTextField restaurantNameTextField = new JTextField();
        JTextField restaurantLocationTextField = new JTextField();
        JTextField ratingTextField = new JTextField();
        JTextField costTextField = new JTextField();
        JTextField reviewCommentTextField = new JTextField();

        JLabel restaurantName = new JLabel("");
        JLabel restaurantLocation = new JLabel("");
        JLabel rating = new JLabel("");
        JLabel cost = new JLabel("");
        JLabel reviewComment = new JLabel("");

        JPanel layout = new JPanel(new GridBagLayout());
        JPanel inputFieldPane = new JPanel(new GridLayout(11, 1));

        public AddReviewListener() {
            submitButton = new JButton("Submit");
        }

        @Override
        public void actionPerformed(ActionEvent e) {


            inputFieldPane.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

            createRestaurantLabelAndInput();
            createRestaurantLocationLabelAndInput();
            createRatingLabelAndInput();
            createCostLabelAndInput();
            createReviewCommentLabelAndInput();


            SubmitButtonListener submitReview = new SubmitButtonListener();
            submitButton.addActionListener(submitReview);

            inputFieldPane.add(submitButton);

            layout.add(inputFieldPane);

            splitPane.setRightComponent(layout);
            layout.revalidate();
        }

        public void createRestaurantLabelAndInput() {
            restaurantName.setText("Input restaurant name:");
            restaurantNameTextField.setPreferredSize(new Dimension(70, 15));
            inputFieldPane.add(restaurantName);
            inputFieldPane.add(restaurantNameTextField);
        }

        public void createRestaurantLocationLabelAndInput() {
            restaurantLocation.setText("Input restaurant location:");
            restaurantLocationTextField.setPreferredSize(new Dimension(70, 15));
            inputFieldPane.add(restaurantLocation);
            inputFieldPane.add(restaurantLocationTextField);
        }

        public void createRatingLabelAndInput() {
            rating.setText("Please provide a rating (out of 5):");
            ratingTextField.setPreferredSize(new Dimension(70, 15));
            inputFieldPane.add(rating);
            inputFieldPane.add(ratingTextField);
        }

        public void createCostLabelAndInput() {
            cost.setText("Please provide a cost (without $ signs):");
            costTextField.setPreferredSize(new Dimension(70, 15));
            inputFieldPane.add(cost);
            inputFieldPane.add(costTextField);
        }

        public void createReviewCommentLabelAndInput() {
            reviewComment.setText("Please provide a comment:");
            reviewComment.setPreferredSize(new Dimension(70, 15));
            inputFieldPane.add(reviewComment);
            inputFieldPane.add(reviewCommentTextField);
        }


        class SubmitButtonListener implements ActionListener {

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

                JPanel container = new JPanel(new GridBagLayout());
                JLabel successfullyAdded = new JLabel("Successfully added");
                JLabel successfulIcon = new JLabel();
                successfulIcon.setIcon(newImageIcon);
                container.add(successfullyAdded);
                container.add(successfulIcon);
                splitPane.setRightComponent(container);
            }
        }
    }

    // EFFECTS: displays top 5 restaurant recommendations based on the average ratings
    class ViewTopRestaurantsListener implements ActionListener {

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

    // EFFECTS: displays all reviews written by a user
    class ViewReviewsListener implements ActionListener {

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

    // EFFECT: reads allReviews.json and reviews.json file and adds appropriate data to classes
    class LoadReviewsListener implements ActionListener {

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

    // MODIFIES: allReviews.json, reviews.json
    // EFFECTS: writes changes to file
    class SaveReviewsListener implements ActionListener {

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
