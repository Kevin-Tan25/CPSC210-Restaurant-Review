package model;

// Restaurant documents the name and location of a restaurant

import java.util.ArrayList;

public class Restaurant {

    private String name;
    private ArrayList<String> locations;

    // EFFECTS: creates a restaurant object with specified name and
    public Restaurant(String name, String location) {
        this.name = name;
        locations = new ArrayList<String>();
        locations.add(location);
    }

    // REQUIRES: name is not an empty string
    // MODIFIES: this
    // EFFECTS: changes the restaurant's name
    public void editRestaurantName(String name) {
        this.name = name;
    }

    // REQUIRES: location is not an empty string
    // MODIFIES: this
    // EFFECTS: add a restaurant location
    public void addLocation(String location) {
        locations.add(location);
    }

    // REQUIRES: location is not an empty string, locations is not an empty list
    // MODIFIES: this
    // EFFECTS: changes the restaurant's location at specified location # to a new location
    public void editLocation(int locationNumber,String location) {
        locations.set(locationNumber - 1, location);
        this.name = name;
    }

    // REQUIRES: locations is not an empty list
    // MODIFIES: this
    // EFFECTS: removes the location at specified location #
    public void deleteLocation(int locationNumber) {
        locations.remove(locationNumber - 1);
    }

    public String getName() {
        return name;
    }

    public ArrayList<String> getLocations() {
        return locations;
    }
}
