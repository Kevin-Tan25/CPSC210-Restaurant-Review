package ui;

import model.RatedRestaurantsName;
import model.Restaurant;
import model.Review;
import model.User;

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
    private ArrayList<Restaurant> allLoggedRestaurants;
    private RatedRestaurantsName allLoggedRestaurantsName;
    private Restaurant r1;
    private Restaurant r2;
    private Review rev1;
    private Review rev2;

    // EFFECTS: runs the Restaurant Review application
    public RestaurantReviewApp() {
        runRestaurantReview();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runRestaurantReview() {
        boolean keepGoing = true;
        String command = null;

        init();
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
//        } else if (command.equals("t")) {
//            viewTopRestaurants();
        } else {
            System.out.println("Selection not valid...");
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes user with existing reviews
    private void init() {
        r1 = new Restaurant("McDonald's", "5728 University Blvd");
        r2 = new Restaurant("Burger King", "1234 University Blvd");
        allLoggedRestaurantsName = new RatedRestaurantsName();
        allLoggedRestaurantsName.addRestaurant(r1.getName());
        allLoggedRestaurantsName.addRestaurant(r2.getName());
        allLoggedRestaurants = new ArrayList<Restaurant>();
        allLoggedRestaurants.add(r1);
        allLoggedRestaurants.add(r2);
        rev1 = new Review(r1, 4, 3.99, "Good and cheap");
        rev2 = new Review(r2, 3, 5.99, "Not bad");
        user = new User("Kevin");
        user.addReview(rev1);
        user.addReview(rev2);
        testUser1 = new User("Joe");
        testUser2 = new User("Stephen");
        testUser3 = new User("Jose");
        user.addFriend(testUser1);
        user.addFriend(testUser2);
        user.addFriend(testUser3);

        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nHow can I help you today " + user.getUserName() + "?");
        System.out.println("\nSelect from:");
        System.out.println("\ta -> view my account");
        System.out.println("\tw -> write a review");
//        System.out.println("\tt -> transfer");
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

        System.out.println(selection);

//        System.out.println(allLoggedRestaurantsName);
//        System.out.println(allLoggedRestaurantsName.getRatedRestaurants().contains(selection));
//        System.out.println(allLoggedRestaurantsName.isRatedRestaurant(selection));

        if (allLoggedRestaurantsName.isRatedRestaurant(selection)) {
            writeExistingReview(selection);
        } else if (selection.equals("n")) {
//            writeReviewForNewRestaurant();
        } else if (selection.equals("b")) {
            // returns back to menu
        } else {
            System.out.println("Selection not valid");
        }
    }

    // EFFECTS: adds a review for an existing restaurant
    private void writeExistingReview(String selection) {
        int indexOfSelection = allLoggedRestaurantsName.getRatedRestaurants().indexOf(selection);
        Restaurant resto = allLoggedRestaurants.get(indexOfSelection);
        System.out.println("Please provide a rating (out of 5): \n");
        int rating = input.nextInt();
        System.out.println("Please provide a cost (without $ signs): \n");
        double cost = input.nextDouble();
        System.out.println("Please provide a comment: \n");
        String reviewComment = input.next();
        Review rev = new Review(resto,rating,cost,reviewComment);
        resto.addReview(rev);
        user.addReview(rev);
    }

    // TODO: add a MANAGE friend functionality
    // EFFECTS: prints a list of friends
    private void manageFriends() {
        for (int i = 0; i < user.getMyFriends().size(); i++) {
            System.out.println(user.getMyFriends().get(i));
        }
        System.out.println();
    }

    // TODO: add a MANAGE todo functionality
    // EFFECTS: prints a list of reviews written by the user
    private void displayReview() {
        for (int i = 0; i < user.getMyReviews().size(); i++) {
            System.out.println(user.getMyReviews().get(i));
        }
    }
}
