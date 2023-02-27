package model;

import java.util.ArrayList;
import java.util.List;

public class User {
    private List<Activity> postedActivities;
    private List<Activity> registeredActivities;

    // EFFECTS: create new user with no joined or posted activities
    public User() {
        this.postedActivities = new ArrayList<>();
        this.registeredActivities = new ArrayList<>();
    }

    // MODIFIES: this
    // REQUIRES: activity is not null
    // EFFECTS: add activity to posted activities
    public void postActivity(Activity activity) {
        this.postedActivities.add(activity);
    }

    // MODIFIES: this
    // EFFECTS: register user in provided activity
    public void registerActivity(Activity activity) {
        this.registeredActivities.add(activity);
    }

    // MODIFIES: this
    // EFFECTS: delete posting of provided activity
    public void deletePosting(Activity activity) {
        postedActivities.remove(activity);
    }

    // MODIFIES: this
    // EFFECTS: cancel registration of provided activity
    public void cancelRegistration(Activity activity) {
        registeredActivities.remove(activity);
    }

    // EFFECTS: return true if user is registered in activity
    public boolean alreadyRegistered(Activity activity) {
        return registeredActivities.contains(activity);
    }

    // EFFECTS: return posted activities
    public List<Activity> getPostedActivities() {
        return this.postedActivities;
    }

    // EFFECTS: return registered activities
    public List<Activity> getRegisteredActivities() {
        return this.registeredActivities;
    }
}












