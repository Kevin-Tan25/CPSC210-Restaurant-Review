package persistence;

import model.RatedRestaurants;
import model.Restaurant;
import model.Review;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Taken from JsonSerializationDemo file
// Represents a reader that reads user from JSON data stored in file
public class JsonReaderAllReviews {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReaderAllReviews(String source) {
        this.source = source;
    }

    // EFFECTS: reads user from file and returns it;
    // throws IOException if an error occurs reading data from file
    public RatedRestaurants read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseReview(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses ratedRestaurants from JSON object and returns it
    private RatedRestaurants parseReview(JSONObject jsonObject) {
        RatedRestaurants restaurants = new RatedRestaurants();
        addRatedRestaurants(restaurants, jsonObject);
        return restaurants;
    }

    // MODIFIES: restaurants
    // EFFECTS: parses reviews from JSON object and adds them to ratedRestaurant
    private void addRatedRestaurants(RatedRestaurants restaurants, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("restaurants");
        for (Object json : jsonArray) {
            JSONObject restaurant = (JSONObject) json;
            addRestaurant(restaurants, restaurant);
        }
    }

    // MODIFIES: restaurants
    // EFFECTS: parses thingy from JSON object and adds it to Rated Restaurants
    private void addRestaurant(RatedRestaurants restaurants, JSONObject jsonObject) {
        String restaurantName = jsonObject.getString("restaurantName");
        String address = jsonObject.getString("address");
        Restaurant restaurant = new Restaurant(restaurantName, address);
        JSONArray jsonArray = jsonObject.getJSONArray("reviews");
        for (Object json : jsonArray) {
            JSONObject review = (JSONObject) json;
            addReview(restaurant, review);
        }
        restaurants.addRestaurant(restaurant);
    }

    // MODIFIES: restaurants
    // EFFECTS: parses review from JSON object and adds it to restaurant
    private void addReview(Restaurant restaurant, JSONObject jsonObject) {
        int rating = jsonObject.getInt("rating");
        double cost = jsonObject.getDouble("cost");
        String comment = jsonObject.getString("comment");
        Review review = new Review(restaurant, rating, cost, comment);
        restaurant.addReview(review);
    }
}
