package model;

// A tandem Arraylist for ratedRestaurantsName, which keeps track of restaurants that have received reviews

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

public class RatedRestaurants implements Writable {

    private ArrayList<Restaurant> ratedRestaurants;
    private ArrayList<Restaurant> sortedRestaurant;

    // EFFECTS: creates an empty rated restaurants object
    public RatedRestaurants() {
        ratedRestaurants = new ArrayList<Restaurant>();
        sortedRestaurant = new ArrayList<Restaurant>();
    }

    // REQUIRES: restaurant is not repeated in the list
    // MODIFIES: this
    // EFFECTS: adds the restaurant to the list
    public void addRestaurant(Restaurant restaurant) {
        ratedRestaurants.add(restaurant);
        sortedRestaurant.add(restaurant);
    }

    // REQUIRES: list is not empty, target restaurant exists in list
    // MODIFIES: this
    // EFFECTS: remove the restaurant from the list
    public void deleteRestaurant(Restaurant restaurant) {
        ratedRestaurants.remove(restaurant);
        sortedRestaurant.remove(restaurant);
    }

    // REQUIRES: restaurant is not repeated in the list, strings are not empty
    // MODIFIES: this
    // EFFECTS: changes the name of the target restaurant to new restaurant
    public void editRestaurantName(Restaurant targetRestaurant, Restaurant newRestaurant) {
        ratedRestaurants.set(ratedRestaurants.indexOf(targetRestaurant), newRestaurant);
        sortedRestaurant.set(sortedRestaurant.indexOf(targetRestaurant), newRestaurant);
    }

    public ArrayList<Restaurant> getRatedRestaurants() {
        return ratedRestaurants;
    }

    public ArrayList<Restaurant> getSortedRestaurant() {
        return sortedRestaurant;
    }

    // Sorts the list in ascending order based on average rating
    // Referenced from Greg Anderson YouTube
    public ArrayList<Restaurant> getTopRestaurants() {
        Collections.sort(sortedRestaurant, new Comparator<Restaurant>() {
            @Override
            public int compare(Restaurant r1, Restaurant r2) {
                return Integer.compare(r2.getAverageRating(), r1.getAverageRating());
            }
        });
        return sortedRestaurant;

    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("restaurants", restaurantsToJson());
        return json;
    }

    // EFFECTS: returns all restaurants in RatedRestaurants as a JSON array
    private JSONArray restaurantsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Restaurant r : ratedRestaurants) {
            jsonArray.put(r.toJson());
        }

        return jsonArray;
    }
}
