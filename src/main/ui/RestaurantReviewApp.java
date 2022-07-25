package ui;

import model.*;

import java.util.ArrayList;
import java.util.Scanner;

// Based off Teller app UI package TELLER APP class

public class RestaurantReviewApp {

    private User user;
    private User testUser1;
    private User testUser2;
    private User testUser3;
    private Scanner input;
//    A list of all restaurants that have received reviews
    private RatedRestaurants allLoggedRestaurants;
    private RatedRestaurantsName allLoggedRestaurantsName;
    private Restaurant r1;
    private Restaurant r2;
    private Restaurant r3;
    private Restaurant r4;
    private Restaurant r5;
    private Restaurant r6;
    private Review rev1;
    private Review rev2;
    private Review rev3;
    private Review rev4;
    private Review rev5;
    private Review rev6;

    // EFFECTS: runs the Restaurant Review application
    public RestaurantReviewApp() {
        runRestaurantReview();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runRestaurantReview() {
        boolean keepGoing = true;
        String command = null;

        initRestaurants();
        initRestaurantLists();
        init();
        initUsers();
        System.out.println("Welcome to the Restaurant review App,");

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("\nThank you for reviewing these restaurants!");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("a")) {
            viewMyAccount();
        } else if (command.equals("w")) {
            writeReview();
        } else if (command.equals("t")) {
            viewTopRestaurants();
        } else if (command.equals("s")) {
            searchRestaurantReviews();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes user with existing reviews
    private void init() {
        rev1 = new Review(r1, 4, 3.99, "Good and cheap");
        rev2 = new Review(r2, 3, 5.99, "Not bad");
        rev3 = new Review(r3, 5, 8.45, "Lots of peanuts");
        rev4 = new Review(r4, 4, 6.78, "Bomb poutine!");
        rev5 = new Review(r5, 5, 46.78, "Best sushi in town");
        rev6 = new Review(r6, 2, 11.00, "Food kinda mid");
        r1.addReview(rev1);
        r2.addReview(rev2);
        r3.addReview(rev3);
        r4.addReview(rev4);
        r5.addReview(rev5);
        r6.addReview(rev6);
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    private void initRestaurants() {
        r1 = new Restaurant("McDonald's", "5728 University Blvd");
        r2 = new Restaurant("Burger King", "1234 University Blvd");
        r3 = new Restaurant("Five Guys", "4823 Thompson Ave");
        r4 = new Restaurant("A&W", "1498 Fisherman Dr");
        r5 = new Restaurant("Miku", "4012 Robson St");
        r6 = new Restaurant("My Home Cuisine", "1984 University Blvd");
    }

    private void initRestaurantLists() {
        allLoggedRestaurantsName = new RatedRestaurantsName();
        allLoggedRestaurantsName.addRestaurant(r1.getName());
        allLoggedRestaurantsName.addRestaurant(r2.getName());
        allLoggedRestaurantsName.addRestaurant(r3.getName());
        allLoggedRestaurantsName.addRestaurant(r4.getName());
        allLoggedRestaurantsName.addRestaurant(r5.getName());
        allLoggedRestaurantsName.addRestaurant(r6.getName());
        allLoggedRestaurants = new RatedRestaurants();
        allLoggedRestaurants.addRestaurant(r1);
        allLoggedRestaurants.addRestaurant(r2);
        allLoggedRestaurants.addRestaurant(r3);
        allLoggedRestaurants.addRestaurant(r4);
        allLoggedRestaurants.addRestaurant(r5);
        allLoggedRestaurants.addRestaurant(r6);
    }

    private void initUsers() {
        user = new User("Kevin");
        user.addReview(rev1);
        user.addReview(rev2);
        user.addReview(rev3);
        user.addReview(rev4);
        testUser1 = new User("Joe");
        testUser1.addReview(rev5);
        testUser1.addReview(rev6);
        testUser2 = new User("Stephen");
        testUser3 = new User("Jose");
        testUser3.addReview(rev5);
        user.addFriend(testUser1);
        user.addFriend(testUser2);
        user.addFriend(testUser3);
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nHow can I help you today " + user.getUserName() + "?");
        System.out.println("\nSelect from:");
        System.out.println("\ta -> view my account");
        System.out.println("\tw -> write a review");
        System.out.println("\tt -> view top restaurants");
        System.out.println("\ts -> search restaurant reviews");
        System.out.println("\tq -> quit");
    }

    // EFFECTS: lets user view a list of their reviews, their username, or their friends
    private void viewMyAccount() {
        String selection = "";
        System.out.println("\tm -> manage friends");
        System.out.println("\tr -> view all reviews");
        System.out.println("\tb -> return to home");
        selection = input.next();
        selection = selection.toLowerCase();

        if (selection.equals("m")) {
            manageFriends();
        } else if (selection.equals("r")) {
            displayReview();
        } else if (selection.equals("b")) {
            // returns back to menu
        } else {
            System.out.println("Selection not valid");
        }
    }

    // EFFECTS: lets user write a review
    private void writeReview() {
        String selection = "";
        System.out.println("Which restaurant would you like to write a review for?\n");
        for (int i = 0; i < allLoggedRestaurantsName.getRatedRestaurants().size(); i++) {
            System.out.println("\t" + allLoggedRestaurantsName.getRatedRestaurants().get(i));
        }
        System.out.println("\tn -> write review for new restaurant");
        System.out.println("\tb -> return to home");
        selection = input.next();
        selection = selection.toLowerCase();
        if (allLoggedRestaurantsName.isRatedRestaurant(selection)) {
            writeExistingReview(selection);
        } else if (selection.equals("n")) {
            writeReviewForNewRestaurant();
        } else if (selection.equals("b")) {
            // returns back to menu
        } else {
            System.out.println("Selection not valid");
        }
    }

    // EFFECTS: views top 5 top rated restaurants based on average rating
    private void viewTopRestaurants() {
        if (allLoggedRestaurants.getTopFiveRated().size() <= 5) {
            for (int i = 0; i < allLoggedRestaurants.getTopFiveRated().size(); i++) {
                Restaurant topRestaurant = allLoggedRestaurants.getTopFiveRated().get(i);
                System.out.println(topRestaurant);
                System.out.println("The average rating is: " + topRestaurant.getAverageRating());
                System.out.println("The average cost is: $" + topRestaurant.getAverageCost() + "\n");
            }
        } else {
            for (int i = 0; i < 5; i++) {
                Restaurant topRestaurant = allLoggedRestaurants.getTopFiveRated().get(i);
                System.out.println(topRestaurant);
                System.out.println("The average rating is: " + topRestaurant.getAverageRating());
                System.out.println("The average cost is: $" + topRestaurant.getAverageCost() + "\n");
            }
        }
    }

    // EFFECTS: lets user search a restaurant with review and see all reviews about it
    private void searchRestaurantReviews() {
        System.out.println("Here are some restaurants that have reviews:");
        for (int i = 0; i < allLoggedRestaurantsName.getRatedRestaurants().size(); i++) {
            System.out.println("\t" + allLoggedRestaurantsName.getRatedRestaurants().get(i));
        }
        System.out.println("Which restaurant would you like to view?");
        String selection = input.next();
        selection = selection.toLowerCase();
        int indexOfSelection = allLoggedRestaurantsName.getRatedRestaurants().indexOf(selection);
        Restaurant restaurant = allLoggedRestaurants.getRatedRestaurants().get(indexOfSelection);
        System.out.println(restaurant.getName() + ": " + restaurant.getNumReviews() + " review(s)");
        for (int i = 0; i < restaurant.getReviews().size(); i++) {
            System.out.println(restaurant.getReviews().get(i));
        }
    }

    // EFFECTS: adds a review for an existing restaurant
    private void writeExistingReview(String selection) {
        int indexOfSelection = allLoggedRestaurantsName.getRatedRestaurants().indexOf(selection);
        Restaurant restaurant = allLoggedRestaurants.getRatedRestaurants().get(indexOfSelection);
        System.out.println("Please provide a rating (out of 5): ");
        int rating = input.nextInt();
        System.out.println("Please provide a cost (without $ signs): ");
        double cost = input.nextDouble();
        System.out.println("Please provide a comment: ");
        String reviewComment = input.next();
        Review review = new Review(restaurant,rating,cost,reviewComment);
        restaurant.addReview(review);
        user.addReview(review);
        System.out.println("Successfully added!");
    }

    // EFFECTS: creates a new restaurant and adds a review for the restaurant
    private void writeReviewForNewRestaurant() {
        System.out.println("Please provide the restaurant's name:");
        String restaurantName = input.next();
        System.out.println("Please the restaurant's location:");
        String restaurantLocation = input.next();
        Restaurant restaurant = new Restaurant(restaurantName, restaurantLocation);
        System.out.println("\nNow time to write a review.");
        System.out.println("Please provide a rating (out of 5): ");
        int rating = input.nextInt();
        System.out.println("Please provide a cost (without $ signs): ");
        double cost = input.nextDouble();
        System.out.println("Please provide a comment: ");
        String reviewComment = input.next();
        Review review = new Review(restaurant,rating,cost,reviewComment);
        restaurant.addReview(review);
        user.addReview(review);
        allLoggedRestaurants.addRestaurant(restaurant);
        allLoggedRestaurantsName.addRestaurant(restaurantName);
        System.out.println("Successfully added!");
    }

    // EFFECTS: prints a list of friends
    private void manageFriends() {
        for (int i = 0; i < user.getMyFriends().size(); i++) {
            System.out.println(user.getMyFriends().get(i));
        }
        System.out.println();
    }

    // EFFECTS: prints a list of reviews written by the user
    private void displayReview() {
        for (int i = 0; i < user.getMyReviews().size(); i++) {
            System.out.println(user.getMyReviews().get(i));
        }
    }
}
