# My Personal Project

## Restaurant Finder

### **Problem:**

Tired of searching for "best restaurants near me" on Google? Tired of the same few restaurants being recommended? Want to get an accurate estimation of how much to pay at a restaurant? Well this app solves all these problems.

### **Practical usages:**

This application combines elements of Google Review and Yelp. What's the main difference? It is more personal and unbiased.

After eating at a restaurant, you can select the restaurant, insert the price you paid for the meal, and add a review. You can add friends in this app. You are then able to view the entries made by your friends and the reviews they left at each restaurant. If you made a good suggestion, friends can like the recommendation. Users with a large amount of good suggestions will be awarded a title of "Food Connoisseur".

Together, the application also compiles a top list of reviewed restaurants by friends and displays them on a dashboard for the user. There is also another suggestion for users to try less popular/ less tried restaurants.

### **User Stories**

- As a user, I want to write a review for a NEW restaurant, add a price, and rate it out of 5.

- As a user, I want to be able to add to existing reviews of a restaurant, add a price, and rate it out of 5.

- As a user, I want to be able to view all reviews that I have written.

- As a user, I want to be able to receive restaurant recommendations: a list of top-rated or "unique" restaurants by my friends.

- As a user, I want to view a list of my friends, and how many reviews they have written.

- As a user, I want to be able to search up a restaurant and view all reviews for the restaurant.

- As a user, I want to be able to save my reviews to file.

- As a user, I want to be able to load my reviews from file.

### **Instructions for Grader**

- You can generate the first required event (adding a review for a restaurant), by clicking on the button labelled "Write a Review", and filling out the fields.

- You can generate the second required event (to view your reviews) by selecting the button labelled "View My Reviews", which is a list of all reviews that YOU wrote.

- You can also generate another list by clicking on the "View Top Restaurants" section, which shows a list of restaurants ranked by their average rating.

- You can locate my visual component by:
  - Clicking on "Load Reviews from File", and program successfully loads the reviews.
  - Clicking on "Save Reviews to File", and program successfully saves the reviews.
  - Clicking on "Write a Review", and correctly writing a review.

- You can save the state of my application by clicking on the "Save Reviews to File" button.

- You can reload the state of my application by clicking on the "Load Reviews from File" button.

### Phase 4: Task 2

Wed Aug 10 15:21:03 PDT 2022
Loaded User reviews.

Wed Aug 10 15:21:03 PDT 2022
A review for: McDonald's
rating: 4
cost: 3.99
and review comment: Good and cheap
has been added to Kevin's reviews

Wed Aug 10 15:21:03 PDT 2022
A review for: Burger King
rating: 3
cost: 5.99
and review comment: Not bad
has been added to Kevin's reviews

Wed Aug 10 15:21:03 PDT 2022
A review for: Five Guys
rating: 5
cost: 8.45
and review comment: Lots of peanuts
has been added to Kevin's reviews

Wed Aug 10 15:21:03 PDT 2022
A review for: A&W
rating: 4
cost: 6.78
and review comment: Bomb poutine!
has been added to Kevin's reviews

Wed Aug 10 15:21:03 PDT 2022
A review for: Miku
rating: 5
cost: 41.99
and review comment: Great service. Good ambiance.
has been added to Kevin's reviews

Wed Aug 10 15:21:03 PDT 2022
A review for: Koi Sushi
rating: 5
cost: 11.99
and review comment: Cheap but good sushi
has been added to Kevin's reviews

Wed Aug 10 15:21:03 PDT 2022
Loaded Restaurant reviews.

Wed Aug 10 15:21:03 PDT 2022
McDonald's has been added to a list of all restaurants.

Wed Aug 10 15:21:03 PDT 2022
Burger King has been added to a list of all restaurants.

Wed Aug 10 15:21:03 PDT 2022
Five Guys has been added to a list of all restaurants.

Wed Aug 10 15:21:03 PDT 2022
A&W has been added to a list of all restaurants.

Wed Aug 10 15:21:03 PDT 2022
Miku has been added to a list of all restaurants.

Wed Aug 10 15:21:03 PDT 2022
My Home Cuisine has been added to a list of all restaurants.

Wed Aug 10 15:21:03 PDT 2022
Koi Sushi has been added to a list of all restaurants.

Wed Aug 10 15:21:19 PDT 2022
A review for: New Restaurant Test
rating: 5
cost: 11.99
and review comment: Good restaurant
has been added to Kevin's reviews

Wed Aug 10 15:21:19 PDT 2022
New Restaurant Test has been added to a list of all restaurants.

Wed Aug 10 15:21:24 PDT 2022
Saved User reviews to file.

Wed Aug 10 15:21:24 PDT 2022
Saved Restaurant reviews to file.

### Phase 4: Task 3

There is quite low coupling in my program. The main aspects of my program are the User, Restaurant, and Review classes. Users can have multiple reviews, restaurants can have multiple reviews, and each review has one restaurant assigned. The review-restaurant relationship is necessary to identify which review corresponds to which restaurant. 

However, one thing that I would change are my UI classes. In phase 0 and 1, I spent the majority of time in my UI class, creating the console version of my app. There ended up being a lot of extra functionalities and logic statements that I had to come up within the class. Instead, I could have implemented these functionalities in the model classes. An example was the "search for restaurant" functionality, which displays all reviews pertaining to a restaurant. I ended up creating this method in the UI class instead of the Restaurant class. This made it time-consuming for me to create the GUI class. I ended up having to copy-paste lots of the code from one class to another, instead of calling other classes. If I had helper classes in the UI package, or increased the cohesion for my model classes, phase 3 would have been much easier for me. Apart from cohesion and coupling problems, there are still some other aspects that I would have focused on if I had more time:

- The RatedRestaurantsName class is a tandem list that keeps track of the NAMES of the restaurants in the RatedRestaurants class. One thing that I could've done was to use a dictionary/ map data structure instead, which removes code redundancy as it removes the need of an extra class entirely. This, also allows me to remove the JsonReaderAllRestaurantsName class


- Another thing that I could have done to improve the readability of code is to refactor the GraphicalInterface class. Right now, all my event listeners are in one main class. I could have taken the snippets of event listeners out (class declarations) and defined the classes/ event listeners in a different file.
