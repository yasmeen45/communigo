package model;

import ui.Application;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Manager {

    private enum Type {
        WALK,
        RUN,
        BIKE
    }

    private enum Area {
        VANCOUVER,
        BURNABY,
        SURREY
    }

    private User user;
    private List<Activity> upcomingActivities;

    // EFFECTS: creates a new manager with a user and pre-created activities
    public Manager() {
        user = new User();
        upcomingActivities = new ArrayList<>();

        this.createActivity(ui.Application.Type.WALK, ui.Application.Area.SURREY, LocalDate.parse("2023-05-21"));
        this.createActivity(ui.Application.Type.RUN, ui.Application.Area.VANCOUVER,LocalDate.parse("2023-04-12"));
        this.createActivity(ui.Application.Type.BIKE, ui.Application.Area.BURNABY, LocalDate.parse("2023-04-24"));
        this.createActivity(ui.Application.Type.WALK, ui.Application.Area.VANCOUVER,
                LocalDate.parse("2023-05-08"));
        this.createActivity(ui.Application.Type.RUN, ui.Application.Area.BURNABY, LocalDate.parse("2023-05-14"));
    }

    // MODIFIES: this
    // REQUIRES: date is not null
    // EFFECTS: create new activity, add it to upcomingActivities, and return it
    public Activity createActivity(ui.Application.Type type, ui.Application.Area area, LocalDate date) {
        Activity activity = new Activity(type, area, date);
        upcomingActivities.add(activity);
        return activity;
    }

    // MODIFIES: this
    // REQUIRES: date is not null
    // EFFECTS: create new activity and add it to user's posted activities
    public void postActivity(ui.Application.Type type, ui.Application.Area area, LocalDate date) {
        Activity activity = createActivity(type, area, date);
        user.postActivity(activity);
    }

    // MODIFIES: this
    // EFFECTS: register user in provided activity. if user already registered
    public void registerActivity(Activity activity) {
        user.registerActivity(activity);
    }

    // MODIFIES: this
    // EFFECTS: delete user's posting of provided activity
    public void deletePosting(Activity activity) {
        upcomingActivities.remove(activity);
        user.deletePosting(activity);
        if (user.alreadyRegistered(activity)) {
            user.cancelRegistration(activity);
        }
    }

    // MODIFIES: this
    // EFFECTS: cancel user's registration for provided activity
    public void cancelRegistration(Activity activity) {
        user.cancelRegistration(activity);
    }

    // EFFECTS: return true if user is registered in activity
    public boolean alreadyRegistered(Activity activity) {
        return user.alreadyRegistered(activity);
    }


    // getters =======================================================================================

    // EFFECTS: return upcoming activities
    public List<Activity> getActivities() {
        return upcomingActivities;
    }

    // MODIFIES: this
    // EFFECTS: sort list of upcoming activities in chronological order and return it
    public List<Activity> getActivitiesChronological() {
        return sortChronological(upcomingActivities);
        // https://stackoverflow.com/questions/5927109/sort-objects-in-arraylist-by-date
    }

    // MODIFIES: this
    // EFFECTS: sort user's posted activities in chronological order and return it
    public List<Activity> getPostedActivities() {
        return sortChronological(user.getPostedActivities());
    }

    // MODIFIES: this
    // EFFECTS: sort user's registered activities in chronological order and return it
    public List<Activity> getRegisteredActivities() {
        return sortChronological(user.getRegisteredActivities());
    }

    // REQUIRES: list is not empty
    // EFFECTS: return list sorted in chronological order
    public List<Activity> sortChronological(List<Activity> list) {
        list.sort(Comparator.comparing(a -> a.getDate()));
        return list;
    }

    // REQUIRES: Type is not null
    // EFFECTS: return upcoming activities with specified type
    public List<Activity> getActivitiesWithType(Application.Type type) {
        List<Activity> result = new ArrayList<>();
        for (Activity a : upcomingActivities) {
            if (a.getType().equals(type)) {
                result.add(a);
            }
        }
        return sortChronological(result);
    }

    // REQUIRES: Area is not null
    // EFFECTS: return upcoming activities with specified Area
    public List<Activity> getActivitiesWithArea(Application.Area area) {
        List<Activity> result = new ArrayList<>();
        for (Activity a : upcomingActivities) {
            if (a.getArea().equals(area)) {
                result.add(a);
            }
        }
        return sortChronological(result);
    }

    // REQUIRES: date is not null
    // EFFECTS: return upcoming activities with specified date
    public List<Activity> getActivitiesWithDate(LocalDate date) {
        List<Activity> result = new ArrayList<>();
        for (Activity a : upcomingActivities) {
            if (a.getDate().equals(date)) {
                result.add(a);
            }
        }
        return sortChronological(result);
    }

}
















