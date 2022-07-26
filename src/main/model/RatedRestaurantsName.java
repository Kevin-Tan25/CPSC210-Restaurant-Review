package model;

// A list of restaurants that have received a rating

import java.util.ArrayList;

public class RatedRestaurantsName {

    private ArrayList<String> ratedRestaurants;

    // EFFECTS: creates an empty rated restaurants object
    public RatedRestaurantsName() {
        ratedRestaurants = new ArrayList<String>();
    }

    // REQUIRES: restaurant is not repeated in the list, string is not empty
    // MODIFIES: this
    // EFFECTS: adds the restaurant to the list
    public void addRestaurant(String restaurant) {
        ratedRestaurants.add(restaurant.toLowerCase());
    }

    // REQUIRES: list is not empty, string is not empty, target restaurant exists in list
    // MODIFIES: this
    // EFFECTS: remove the restaurant from the list
    public void deleteRestaurant(String restaurant) {
        ratedRestaurants.remove(restaurant.toLowerCase());
    }

    // REQUIRES: restaurant is not repeated in the list, strings are not empty
    // MODIFIES: this
    // EFFECTS: changes the name of the target restaurant to new restaurant
    public void editRestaurantName(String targetRestaurant, String newRestaurant) {
        ratedRestaurants.set(ratedRestaurants.indexOf(targetRestaurant.toLowerCase()), newRestaurant.toLowerCase());
    }

    // EFFECTS: returns true if ratedRestaurants already contains restaurant name, false otherwise
    public Boolean isRatedRestaurant(String restaurant) {
        if (ratedRestaurants.contains(restaurant.toLowerCase())) {
            return true;
        } else {
            return false;
        }
    }

    public ArrayList<String> getRatedRestaurants() {
        return ratedRestaurants;
    }

}
