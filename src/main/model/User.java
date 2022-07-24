package model;

// User identifies the person writing a review

import java.util.ArrayList;

public class User {

    private int userID;
    private String userName;
    private ArrayList<Review> myReviews;

    // EFFECTS: creates a User object
    public User(int userID, String userName) {
        this.userID = userID;
        this.userName = userName;
        myReviews = new ArrayList<Review>();
    }

    // REQUIRES: userName is a non-empty string
    // MODIFIES: this
    // EFFECTS: changes the user's name
    public void changeUserName(String userName) {
        this.userName = userName;
    }

    // EFFECTS: returns a list of reviews made by the user
    public ArrayList<Review> getMyReviews() {
        return myReviews;
    }
}
