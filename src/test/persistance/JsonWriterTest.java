package persistance;

// citation: modelled after Json Demo provided in P2 description on EdX

import model.Activity;
import model.Manager;
import model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.Application;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonWriterTest extends JsonTest {
    @Test
    public void testNoFile() {
        try {
            JsonWriter writer = new JsonWriter("./data/\0illegal:File.json");
            Manager manager = new Manager();
            writer.open();
            fail("IOException not thrown.");
        } catch (IOException e) {
            // should get here!
        }
    }

    @Test
    public void testEmptyManagerData() {
        try {
            User user = new User(new ArrayList<>(), new ArrayList<>());
            Manager manager = new Manager(user, new ArrayList<>());
            JsonWriter writer = new JsonWriter("./data/testEmptyManager.json");
            writer.open();
            writer.write(manager);
            writer.close();

            JsonReader reader = new JsonReader("./data/testEmptyManager.json");
            manager = reader.read();
            assertTrue(manager.getActivities().isEmpty());
            assertTrue(manager.getRegisteredActivities().isEmpty());
            assertTrue(manager.getPostedActivities().isEmpty());
        } catch (IOException e) {
            fail("IOException thrown.");
        }
    }

    @Test
    public void testNormalManagerData() {
        try {
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

            JsonWriter writer = new JsonWriter("./data/testNormalManager.json");
            writer.open();
            writer.write(expectedManager);
            writer.close();


            JsonReader reader = new JsonReader("./data/testNormalManager.json");
            Manager manager = reader.read();
            super.checkManager(manager, expectedManager);
        } catch (IOException e) {
            fail("IOException thrown.");
        }
    }
}







