package model;

// A review documents a restaurant, and any comments/ ratings associated with the restaurant. Can edit the review.


public class Review {

    private int reviewIdentifier;
    private Restaurant restaurant;
    private int rating;
    private double cost;
    private String reviewComment;

    // EFFECTS: creates a review object for a restaurant with a given rating and review comment
    public Review(int reviewIdentifier, Restaurant restaurant, int rating, double cost, String reviewComment) {
        this.reviewIdentifier = reviewIdentifier;
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

    public int getReviewIdentifier() {
        return reviewIdentifier;
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
}
