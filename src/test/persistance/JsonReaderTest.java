package persistance;

// citation: modelled after Json Demo provided in P2 description on EdX

import model.Activity;
import model.Manager;
import model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.Application;

import java.io.IOException;
import java.lang.reflect.AccessibleObject;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest extends JsonTest {

    @Test
    public void testNoFile() {
        JsonReader reader = new JsonReader("./data/noFile.json");
        try {
            Manager manager = reader.read();
            fail("IOException not thrown.");
        } catch (IOException e) {
            // should get here!
        }
    }

    @Test
    public void testEmptyManagerData() {
        JsonReader reader = new JsonReader("./data/testEmptyManager.json");
        try {
            Manager manager = reader.read();
            assertTrue(manager.getActivities().isEmpty());
            assertTrue(manager.getRegisteredActivities().isEmpty());
            assertTrue(manager.getPostedActivities().isEmpty());
        } catch (IOException e) {
            fail("IOException thrown.");
        }
    }

    @Test
    public void testNormalManagerData() {
        JsonReader reader = new JsonReader("./data/testNormalManager.json");
        try {
            Manager manager = reader.read();
            Manager dummyManager = new Manager();

            List<Activity> upcomingActivities = dummyManager.getActivities();

            Activity a1 = new Activity(Application.Type.WALK, Application.Area.VANCOUVER,
                    LocalDate.parse("2023-05-08"));
            Activity a2 = new Activity(Application.Type.RUN, Application.Area.BURNABY,
                    LocalDate.parse("2023-05-14"));

            List<Activity> registeredActivities = new ArrayList<>();
            List<Activity> postedActivities = new ArrayList<>();
            registeredActivities.add(a2);
            registeredActivities.add(a1);
            postedActivities.add(a2);

            User expectedUser = new User(registeredActivities, postedActivities);
            Manager expectedManager = new Manager(expectedUser, upcomingActivities);

            super.checkManager(manager, expectedManager);
        } catch (IOException e) {
            fail("IOException thrown.");
        }
    }
}







