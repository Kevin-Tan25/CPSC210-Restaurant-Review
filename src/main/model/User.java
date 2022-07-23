package model;

import java.util.ArrayList;

public class User {

    protected final int MAX_SAVED_RESTAURANTS = 10;

    private ArrayList<User> friendList;
    private ArrayList<Restaurant> savedRestaurants;
    private int uniqueID;
    private String name;

    // EFFECTS: creates a new User object with specified ID and NAME
    public User(int uniqueID, String name) {
        this.uniqueID = uniqueID;
        this.name = name;
        friendList = new ArrayList<User>();
    }

    // EFFECTS: shows the user's friends
    public void showFriends(){

    }



}
