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
    // EFFECTS: sort list of upcoming activities in chronological order and return it
    public List<Activity> getActivitiesChronological() {
        upcomingActivities.sort(Comparator.comparing(a -> a.getDate()));
        return upcomingActivities;
        // https://stackoverflow.com/questions/5927109/sort-objects-in-arraylist-by-date
    }

    // EFFECTS: return upcoming activities
    public List<Activity> getActivities() {
        return upcomingActivities;
    }
}
















