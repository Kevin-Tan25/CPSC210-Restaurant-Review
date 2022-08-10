package model;

// User identifies the person writing a review

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;

public class User implements Writable {

    private String userName;
    private ArrayList<Review> myReviews;
    private ArrayList<User> myFriends;

    // EFFECTS: creates a User object
    public User(String userName) {
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
        EventLog.getInstance().logEvent(new Event("A review for: " + r.getRestaurant().getName() + "\n"
                + "rating: " + r.getRating() + "\n"
                + "cost: " + r.getCost() + "\n"
                + "and review comment: " + r.getReviewComment() + "\n"
                + "has been added to " + userName + "'s reviews"));
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

    public String getUserName() {
        return userName;
    }

    public ArrayList<Review> getMyReviews() {
        return myReviews;
    }

    public ArrayList<User> getMyFriends() {
        return myFriends;
    }

    // EFFECTS: returns the amount of reviews that a person wrote
    public int getNumReviews() {
        return myReviews.size();
    }

    // EFFECTS: returns string representation of user: based off teller app
    @Override
    public String toString() {
        return "[Name = " + userName + "| Number of Reviews = " + getNumReviews() + "]";
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", userName);
        json.put("reviews", reviewsToJson());
        return json;
    }

    // EFFECTS: returns reviews in this user as a JSON array
    private JSONArray reviewsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Review r : myReviews) {
            jsonArray.put(r.toJson());
        }

        return jsonArray;
    }
}
