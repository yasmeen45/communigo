package persistance;

// citation: modelled after Json Demo provided in P2 description on EdX

import model.Activity;
import model.Manager;
import ui.Application;

import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class JsonTest {
    protected void checkManager(Manager m1, Manager m2) {
        assertTrue(m1.getActivities().equals(m2.getActivities()));
        assertTrue(m1.getPostedActivities().equals(m2.getPostedActivities()));
        assertTrue(m1.getRegisteredActivities().equals(m2.getRegisteredActivities()));
    }
}
