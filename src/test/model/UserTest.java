package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {

    User u1;
    User u2;
    User u3;
    Restaurant r1;
    Restaurant r2;
    Review rev1;
    Review rev2;


    @BeforeEach
    public void setUp() {
        u1 = new User(1, "Kevin");
        u2 = new User(2, "Steve");
        u3 = new User(3, "Joe");

        r1 = new Restaurant("McDonald's", "5728 University Blvd");
        r2 = new Restaurant("Burger King", "1234 University Blvd");

        rev1 = new Review(1, r1, 4, 3.99, "Good and cheap");
        rev2 = new Review(2, r2, 3, 5.99, "Not bad");
    }

    @Test
    public void testConstructor() {
        assertEquals(u1.getUserID(), 1);
        assertEquals(u1.getUserName(), "Kevin");
        assertEquals(u1.getMyReviews().size(), 0);
        assertEquals(u1.getMyFriends().size(), 0);
    }

    @Test
    public void testChangeUserName() {
        assertEquals(u1.getUserName(), "Kevin");
        u1.changeUserName("Joe");
        assertEquals(u1.getUserName(), "Joe");
        u1.changeUserName("Steve");
        assertEquals(u1.getUserName(), "Steve");
    }

    @Test
    public void testAddReview() {
        assertEquals(u1.getMyReviews().size(), 0);
        u1.addReview(rev1);
        assertEquals(u1.getMyReviews().size(), 1);
        assertEquals(u1.getMyReviews().get(0), rev1);
        u1.addReview(rev2);
        assertEquals(u1.getMyReviews().size(), 2);
        assertEquals(u1.getMyReviews().get(1), rev2);
    }

    @Test
    public void testAddDeleteFriends() {
        assertEquals(u1.getMyFriends().size(), 0);
        u1.addFriend(u2);
        assertEquals(u1.getMyFriends().size(),1);
        assertEquals(u1.getMyFriends().get(0), u2);
        u1.addFriend(u3);
        assertEquals(u1.getMyFriends().size(),2);
        assertEquals(u1.getMyFriends().get(0), u2);
        assertEquals(u1.getMyFriends().get(1), u3);
        u1.deleteFriend(1);
        assertEquals(u1.getMyFriends().size(),1);
        assertEquals(u1.getMyFriends().get(0), u3);
        u1.deleteFriend(1);
        assertEquals(u1.getMyFriends().size(),0);
    }
}
