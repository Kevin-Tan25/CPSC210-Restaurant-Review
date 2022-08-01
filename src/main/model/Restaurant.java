package model;

// Restaurant documents the name and location of a restaurant

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class Restaurant implements Writable {

    private String name;
    private String location;
    private ArrayList<Review> reviews;
    private int totalRating;
    private double totalCost;
    private int numReviews;

    // REQUIRES: both Strings are non-empty
    // EFFECTS: creates a restaurant object with specified name and location
    public Restaurant(String name, String location) {
        this.name = name;
        this.location = location;
        reviews = new ArrayList<Review>();
        numReviews = 0;
        totalRating = 0;
        totalCost = 0;
    }

    // REQUIRES: name is not an empty string
    // MODIFIES: this
    // EFFECTS: changes the restaurant's name
    public void editRestaurantName(String name) {
        this.name = name;
    }

    // REQUIRES: not duplicate reviews
    // MODIFIES: this
    // EFFECTS: adds a review to the restaurant
    public void addReview(Review r) {
        reviews.add(r);
        numReviews++;
        totalRating += r.getRating();
        totalCost += r.getCost();
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public ArrayList<Review> getReviews() {
        return reviews;
    }

    public int getAverageRating() {
        return totalRating / numReviews;
    }

    public double getAverageCost() {
        return totalCost / numReviews;
    }

    public int getNumReviews() {
        return numReviews;
    }

    // EFFECTS: returns string representation of user: based off teller app
    @Override
    public String toString() {
        return "[Restaurant Name = " + name + "]";
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("restaurantName", name);
        json.put("address", location);
        json.put("reviews", reviewToJson());
        return json;
    }

    // EFFECTS: returns all reviews in restaurant as a JSON array
    private JSONArray reviewToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Review r : reviews) {
            jsonArray.put(r.toJson());
        }

        return jsonArray;
    }
}
