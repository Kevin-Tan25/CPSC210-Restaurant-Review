package model;

// A review documents a restaurant, and any comments/ ratings associated with the restaurant. Can edit the review.


public class Review {

    private Restaurant restaurant;
    private int rating;
    private String reviewComment;

    // EFFECTS: creates a review object for a restaurant with a given rating and review comment
    public Review(Restaurant restaurant, int rating, String reviewComment) {
        this.restaurant = restaurant;
        this.rating = rating;
        this.reviewComment = reviewComment;
    }


//      Fields:
//      Restaurant Name
//      Rating (out of 5)
//      Methods:
//      Edit review
}
