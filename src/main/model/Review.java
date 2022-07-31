package model;

// A review documents a restaurant, and any comments/ ratings associated with the restaurant. Can edit the review.


import org.json.JSONObject;
import persistence.Writable;

public class Review implements Writable {

    private Restaurant restaurant;
    private int rating;
    private double cost;
    private String reviewComment;

    // EFFECTS: creates a review object for a restaurant with a given rating and review comment
    public Review(Restaurant restaurant, int rating, double cost, String reviewComment) {
        this.restaurant = restaurant;
        this.rating = rating;
        this.cost = cost;
        this.reviewComment = reviewComment;
    }

    // SPECIFIES: rating is a number between 1 and 5
    // MODIFIES: this
    // EFFECTS: updates rating for company to the new rating
    public void changeRating(int rating) {
        this.rating = rating;
    }

    // SPECIFIES: rating is a number between 1 and 5
    // MODIFIES: this
    // EFFECTS: updates rating for company to the new rating
    public void changeReview(String reviewComment) {
        this.reviewComment = reviewComment;
    }

    // SPECIFIES: rating is a number between 1 and 5
    // MODIFIES: this
    // EFFECTS: updates rating for company to the new rating
    public void changeCost(double cost) {
        this.cost = cost;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }

    public int getRating() {
        return rating;
    }

    public double getCost() {
        return cost;
    }

    public String getReviewComment() {
        return reviewComment;
    }

    // EFFECTS: returns a string representation of Review: taken from TELLER app example
    @Override
    public String toString() {
        return "[Name = " + restaurant.getName() + "| Rating = " + rating + "| Comment = " + reviewComment + "]";
    }


    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("restaurantName", restaurant.getName());
        json.put("address", restaurant.getLocations());
        json.put("rating", rating);
        json.put("cost", cost);
        json.put("comment", reviewComment);
        return json;
    }
}
