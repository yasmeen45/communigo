package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ManagerTest {

    Manager manager;
    Activity a1;
    Activity a2;
    Activity a3;
    Activity a4;
    Activity a5;

    @BeforeEach
    public void setup() {
        manager = new Manager();

        a1 = manager.createActivity(ui.Application.Type.WALK, ui.Application.Area.SURREY,
                LocalDate.parse("2023-05-21"));
        a2 = manager.createActivity(ui.Application.Type.RUN, ui.Application.Area.VANCOUVER,
                LocalDate.parse("2023-04-12"));
        a3 = manager.createActivity(ui.Application.Type.BIKE, ui.Application.Area.BURNABY,
                LocalDate.parse("2023-04-24"));
        a4 = manager.createActivity(ui.Application.Type.WALK, ui.Application.Area.VANCOUVER,
                LocalDate.parse("2023-05-08"));
        a5 = manager.createActivity(ui.Application.Type.RUN, ui.Application.Area.BURNABY,
                LocalDate.parse("2023-05-14"));
    }

    @Test
    public void testConstructor() {
        List<Activity> result = manager.getActivities();
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
        List<Activity> result = manager.getActivitiesChronological();

        List<Activity> expected = new ArrayList<>();
        expected.add(a2);
        expected.add(a3);
        expected.add(a4);
        expected.add(a5);
        expected.add(a1);

        assertEquals(expected, result);
    }
}