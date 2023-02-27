package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.Application;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ManagerTest {

    Manager m1;
    Activity a1;
    Activity a2;
    Activity a3;
    Activity a4;
    Activity a5;

    @BeforeEach
    public void setup() {
        m1 = new Manager();

        List<Activity> preset = m1.getActivities();

        a1 = preset.get(0);
        a2 = preset.get(1);
        a3 = preset.get(2);
        a4 = preset.get(3);
        a5 = preset.get(4);
    }

    @Test
    public void testConstructor() {
        List<Activity> result = m1.getActivities();
        List<Activity> expected = new ArrayList<>();

        expected.add(a1);
        expected.add(a2);
        expected.add(a3);
        expected.add(a4);
        expected.add(a5);

        assertEquals(expected, result);
    }

    @Test
    public void testGetActivitiesChronological() {
        List<Activity> result = m1.getActivitiesChronological();

        List<Activity> expected = new ArrayList<>();
        expected.add(a2);
        expected.add(a3);
        expected.add(a4);
        expected.add(a5);
        expected.add(a1);

        assertEquals(expected, result);
    }

    @Test
    public void testGetPostedActivities() {
        List<Activity> r1 = m1.getPostedActivities();
        assertTrue(r1.isEmpty());

        m1.postActivity(a1.getType(), a1.getArea(),a1.getDate());
        m1.postActivity(a2.getType(), a2.getArea(),a2.getDate());
        List <Activity> r2 = m1.getPostedActivities();
        assertTrue(r2.get(0).equalTo(a2));
        assertTrue(r2.get(1).equalTo(a1));
    }

    @Test
    public void testGetRegisteredActivities() {
        List<Activity> r1 = m1.getRegisteredActivities();
        assertTrue(r1.isEmpty());

        m1.registerActivity(a1);
        m1.registerActivity(a2);
        List <Activity> r2 = m1.getRegisteredActivities();
        assertTrue(r2.get(0).equalTo(a2));
        assertTrue(r2.get(1).equalTo(a1));
    }

    @Test
    public void testGetActivitiesWithType() {
        List<Activity> r1 = m1.getActivitiesWithType(Application.Type.WALK);
        assertTrue(r1.get(0).equalTo(a4));
        assertTrue(r1.get(1).equalTo(a1));
        assertEquals(2, r1.size());

        List<Activity> r2 = m1.getActivitiesWithType(Application.Type.RUN);
        assertTrue(r2.get(0).equalTo(a2));
        assertTrue(r2.get(1).equalTo(a5));
        assertEquals(2, r2.size());

        List<Activity> r3 = m1.getActivitiesWithType(Application.Type.BIKE);
        assertTrue(r3.get(0).equalTo(a3));
        assertEquals(1, r3.size());
    }

    @Test
    public void testGetActivitiesWithArea() {
        List<Activity> r1 = m1.getActivitiesWithArea(Application.Area.VANCOUVER);
        assertTrue(r1.get(0).equalTo(a2));
        assertTrue(r1.get(1).equalTo(a4));
        assertEquals(2, r1.size());

        List<Activity> r2 = m1.getActivitiesWithArea(Application.Area.BURNABY);
        assertTrue(r2.get(0).equalTo(a3));
        assertTrue(r2.get(1).equalTo(a5));
        assertEquals(2, r2.size());

        List<Activity> r3 = m1.getActivitiesWithArea(Application.Area.SURREY);
        assertTrue(r3.get(0).equalTo(a1));
        assertEquals(1, r3.size());
    }

    @Test
    public void testGetActivitiesWithDate() {
        List<Activity> r1 = m1.getActivitiesWithDate(LocalDate.parse("2023-04-12"));
        assertTrue(r1.get(0).equalTo(a2));
        assertEquals(1, r1.size());

        List<Activity> r2 = m1.getActivitiesWithDate(LocalDate.parse("2023-08-12"));
        assertTrue(r2.isEmpty());
    }

    @Test
    public void testRegisterActivity() {
        assertFalse(m1.alreadyRegistered(a3));
        m1.registerActivity(a3);
        assertTrue(m1.alreadyRegistered(a3));
        m1.registerActivity(a5);
        assertTrue(m1.alreadyRegistered(a5));
    }

    @Test
    public void testCancelRegistration() {
        assertFalse(m1.alreadyRegistered(a3));
        m1.registerActivity(a3);
        assertTrue(m1.alreadyRegistered(a3));
        m1.cancelRegistration(a3);
        assertFalse(m1.alreadyRegistered(a3));
    }

    @Test
    public void testDeletePosting() {
        Activity a6 = new Activity(Application.Type.BIKE, Application.Area.SURREY, LocalDate.parse("2023-05-05"));
        assertTrue(m1.getPostedActivities().isEmpty());
        m1.postActivity(Application.Type.BIKE, Application.Area.SURREY, LocalDate.parse("2023-05-05"));
        List<Activity> posted = m1.getPostedActivities();
        assertEquals(1, posted.size());
        m1.registerActivity(posted.get(0));
        assertEquals(1, m1.getRegisteredActivities().size());

        m1.deletePosting(posted.get(0));
        assertTrue(m1.getPostedActivities().isEmpty());
        assertEquals(0, m1.getRegisteredActivities().size());
    }

    @Test
    public void testCreateActivity() {
        assertEquals(5, m1.getActivities().size());
        Activity a6 = new Activity(Application.Type.BIKE, Application.Area.SURREY, LocalDate.parse("2023-05-05"));
        m1.createActivity(Application.Type.BIKE, Application.Area.SURREY, LocalDate.parse("2023-05-05"));
        assertEquals(6, m1.getActivities().size());
        List<Activity> result = m1.getActivities();
        assertTrue(result.get(5).equalTo(a6));
    }

    @Test
    public void testPostActivity() {
        assertEquals(5, m1.getActivities().size());
        assertTrue(m1.getPostedActivities().isEmpty());
        Activity a6 = new Activity(Application.Type.BIKE, Application.Area.SURREY, LocalDate.parse("2023-05-05"));

        m1.postActivity(Application.Type.BIKE, Application.Area.SURREY, LocalDate.parse("2023-05-05"));
        assertEquals(6, m1.getActivities().size());
        List<Activity> result = m1.getActivities();
        assertTrue(result.get(5).equalTo(a6));

        assertEquals(1, m1.getPostedActivities().size());
        assertTrue(m1.getPostedActivities().get(0).equalTo(a6));
    }

}

/*

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
 */


