package persistence;

import model.Restaurant;
import model.Review;
import model.User;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Taken from JsonSerializationDemo file
// Represents a reader that reads user from JSON data stored in file
public class JsonReaderUser {
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReaderUser(String source) {
        this.source = source;
    }

    // EFFECTS: reads user from file and returns it;
    // throws IOException if an error occurs reading data from file
    public User read() throws IOException {
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

    // EFFECTS: parses workroom from JSON object and returns it
    private User parseReview(JSONObject jsonObject) {
        String name = jsonObject.getString("name");
        User u = new User(name);
        addReviews(u, jsonObject);
        return u;
    }

    // MODIFIES: u
    // EFFECTS: parses reviews from JSON object and adds them to user
    private void addReviews(User u, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("reviews");
        for (Object json : jsonArray) {
            JSONObject review = (JSONObject) json;
            addReview(u, review);
        }
    }

    // MODIFIES: u
    // EFFECTS: parses review from JSON object and adds it to the user
    private void addReview(User u, JSONObject jsonObject) {
        String restaurantName = jsonObject.getString("restaurantName");
        String location = jsonObject.getString("address");
        int rating = jsonObject.getInt("rating");
        double cost = jsonObject.getDouble("cost");
        String comment = jsonObject.getString("comment");
        Restaurant r = new Restaurant(restaurantName, location);
        Review review = new Review(r, rating, cost, comment);
        u.addReview(review);
    }
}
