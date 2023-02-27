package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.Application;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {
    User u1;
    User u2;
    User u3;
    Activity a1;
    Activity a2;
    Activity a3;

    @BeforeEach
    public void setup() {
        u1 = new User();
        u2 = new User();
        u3 = new User();

        a1 = new Activity(Application.Type.RUN, Application.Area.VANCOUVER, LocalDate.parse("2023-05-01"));
        a2 = new Activity(Application.Type.WALK, Application.Area.BURNABY, LocalDate.parse("2023-04-01"));
        a3 = new Activity(Application.Type.BIKE, Application.Area.SURREY, LocalDate.parse("2023-05-05"));

        u2.registerActivity(a1);
        u2.postActivity(a1);

        u3.registerActivity(a1);
        u3.registerActivity(a2);
        u3.registerActivity(a3);
        u3.postActivity(a1);
        u3.postActivity(a2);
        u3.postActivity(a3);
    }

    @Test
    public void testConstructor() {
        List<Activity> result = u1.getPostedActivities();
        assertTrue(result.isEmpty());

        List<Activity> result2 = u1.getRegisteredActivities();
        assertTrue(result2.isEmpty());
    }

    @Test
    public void testPostActivity() {
        u1.postActivity(a1);
        List<Activity> r1 = u1.getPostedActivities();

        List<Activity> e1 = new ArrayList<>();
        e1.add(a1);

        assertEquals(r1, e1);

        u2.postActivity(a2);
        List<Activity> r2 = u2.getPostedActivities();

        List<Activity> e2 = new ArrayList<>();
        e2.add(a1);
        e2.add(a2);

        assertEquals(r2, e2);
    }

    @Test
    public void testRegisterActivity() {
        u1.registerActivity(a1);
        List<Activity> r1 = u1.getRegisteredActivities();

        List<Activity> e1 = new ArrayList<>();
        e1.add(a1);

        assertEquals(r1, e1);


        u2.registerActivity(a2);
        List<Activity> r2 = u2.getRegisteredActivities();

        List<Activity> e2 = new ArrayList<>();
        e2.add(a1);
        e2.add(a2);

        assertEquals(r2, e2);
    }

    @Test
    public void testDeletePosting() {
        u2.deletePosting(a1);
        assertTrue(u2.getPostedActivities().isEmpty());

        u3.deletePosting(a3);
        List<Activity> r1 = u3.getPostedActivities();

        List<Activity> e1 = new ArrayList<>();
        e1.add(a1);
        e1.add(a2);

        assertEquals(e1, r1);
    }

    @Test
    public void testCancelRegistration() {
        u2.cancelRegistration(a1);
        assertTrue(u2.getRegisteredActivities().isEmpty());

        u3.cancelRegistration(a3);
        List<Activity> r1 = u3.getRegisteredActivities();

        List<Activity> e1 = new ArrayList<>();
        e1.add(a1);
        e1.add(a2);

        assertEquals(e1, r1);
    }

    @Test
    public void testAlreadyRegistered() {
        assertTrue(u2.alreadyRegistered(a1));
        assertFalse(u2.alreadyRegistered(a2));

        assertFalse(u1.alreadyRegistered(a1));
    }
}
