package model;

// User identifies the person writing a review

import java.util.ArrayList;

public class User {

    private int userID;
    private String userName;
    private ArrayList<Review> myReviews;
    private ArrayList<User> myFriends;

    // EFFECTS: creates a User object
    public User(int userID, String userName) {
        this.userID = userID;
        this.userName = userName;
        myReviews = new ArrayList<Review>();
        myFriends = new ArrayList<User>();
    }

    // REQUIRES: userName is a non-empty string
    // MODIFIES: this
    // EFFECTS: changes the user's name
    public void changeUserName(String userName) {
        this.userName = userName;
    }

    // MODIFIES: this
    // EFFECTS: adds a review to list of review
    public void addReview(Review r) {
        myReviews.add(r);
    }

    // MODIFIES: this
    // EFFECTS: adds a friend to a list of friends
    public void addFriend(User u) {
        myFriends.add(u);
    }

    // MODIFIES: this
    // EFFECTS: delete friend at specified #
    public void deleteFriend(int friendPosition) {
        myFriends.remove(friendPosition - 1);
    }

    public int getUserID() {
        return userID;
    }

    public String getUserName() {
        return userName;
    }

    public ArrayList<Review> getMyReviews() {
        return myReviews;
    }

    public ArrayList<User> getMyFriends() {
        return myFriends;
    }
}