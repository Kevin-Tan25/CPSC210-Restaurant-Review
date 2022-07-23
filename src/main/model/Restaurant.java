package model;

// Restaurant documents the name and location of a restaurant

public class Restaurant {

    private String name;
    private String location;

    // EFFECTS: creates a restaurant object with specified name and location
    public Restaurant(String name, String location) {
        this.name = name;
        this.location = location;
    }

    // EFFECTS: changes the restaurant's name
    public void changeRestaurantName(String name) {

    }
}
