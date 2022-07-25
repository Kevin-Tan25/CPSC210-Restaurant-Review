package ui;

import model.Restaurant;
import model.Review;
import model.User;

import java.util.Scanner;

// Based off Teller app UI package TELLER APP class

public class RestaurantReviewApp {

    private User user;
    private Scanner input;
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
//        } else if (command.equals("w")) {
//            writeReview();
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
        rev1 = new Review(1, r1, 4, 3.99, "Good and cheap");
        rev2 = new Review(2, r2, 3, 5.99, "Not bad");
        user = new User(1,"Kevin");
        user.addReview(rev1);
        user.addReview(rev2);
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\ta -> view account");
//        System.out.println("\tw -> withdraw");
//        System.out.println("\tt -> transfer");
        System.out.println("\tq -> quit");
    }

    // EFFECTS: lets user view a list of their reviews, their username, or their friends
    private void viewMyAccount() {
        String selection = "";
        System.out.println("Hello, " + user.getUserName() + "!\n");
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

    // TODO: add a MANAGE friend functionality, learn how to format not in array form
    // EFFECTS: prints a list of friends
    private void manageFriends() {
        for (int i = 0; i < user.getMyReviews().size(); i++) {
            System.out.println(user.getMyReviews().get(i));
        }
//        for (Review r: user.getMyReviews()) {
//            System.out.println(r + "\n");
//        }
    }

    // TODO: add a MANAGE todo functionality
    // EFFECTS: prints a list of reviews written by the user
    private void displayReview() {
        System.out.println(user.getMyReviews());
    }
}
