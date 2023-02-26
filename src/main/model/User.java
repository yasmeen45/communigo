package model;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class User {
    private List<Activity> activitiesPosted;
    private List<Activity> activitiesJoined;

    // EFFECTS: create new user with no joined or posted activities
    public User() {
        activitiesPosted = new ArrayList<>();
        activitiesJoined = new ArrayList<>();
    }

    // MODIFIES: this
    // REQUIRES: activity is not null
    // EFFECTS: add activity to posted activities
    public void postActivity(Activity activity) {
        activitiesPosted.add(activity);
    }
}
