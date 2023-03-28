package model;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ui.Application;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ActivityTest {

    Activity a1;
    Activity a2;
    Activity a3;

    @BeforeEach
    public void setup() {
        a1 = new Activity(Application.Type.RUN, Application.Area.VANCOUVER, LocalDate.parse("2023-05-01"));
        a2 = new Activity(Application.Type.WALK, Application.Area.BURNABY, LocalDate.parse("2023-04-01"));
        a3 = new Activity(Application.Type.BIKE, Application.Area.SURREY, LocalDate.parse("2023-05-05"));
    }

    @Test
    public void testConstructor() {
        assertEquals(Application.Type.RUN, a1.getType());
        assertEquals(Application.Area.VANCOUVER, a1.getArea());
        assertEquals(LocalDate.parse("2023-05-01"), a1.getDate());

        assertEquals(Application.Type.WALK, a2.getType());
        assertEquals(Application.Area.BURNABY, a2.getArea());
        assertEquals(LocalDate.parse("2023-04-01"), a2.getDate());

        assertEquals(Application.Type.BIKE, a3.getType());
        assertEquals(Application.Area.SURREY, a3.getArea());
        assertEquals(LocalDate.parse("2023-05-05"), a3.getDate());
    }

    @Test
    public void testEqualTo() {
        Activity a4 = new Activity(Application.Type.RUN, Application.Area.VANCOUVER, LocalDate.parse("2023-05-01"));
        assertTrue(a1.equalTo(a4));

        Activity a5 = new Activity(Application.Type.WALK, Application.Area.VANCOUVER, LocalDate.parse("2023-05-01"));
        assertFalse(a1.equalTo(a5));

        Activity a6 = new Activity(Application.Type.RUN, Application.Area.SURREY, LocalDate.parse("2023-05-01"));
        assertFalse(a1.equalTo(a6));

        Activity a7 = new Activity(Application.Type.RUN, Application.Area.VANCOUVER, LocalDate.parse("2023-05-06"));
        assertFalse(a1.equalTo(a7));
    }

    @Test
    public void testEquals() {
        assertFalse(a1.equals(null));
    }

    @Test
    public void testTypeToPrint() {
        assertEquals("Running", a1.getTypeToPrint());
        assertEquals("Walking", a2.getTypeToPrint());
        assertEquals("Biking", a3.getTypeToPrint());
    }

    @Test
    public void testAreaToPrint() {
        assertEquals("Vancouver", a1.getAreaToPrint());
        assertEquals("Burnaby", a2.getAreaToPrint());
        assertEquals("Surrey", a3.getAreaToPrint());
    }

    @Test
    public void testToJson() {
        assertEquals("{\"area\":\"VANCOUVER\",\"date\":\"2023-05-01\",\"type\":\"RUN\"}",
                a1.toJson().toString());
        // credit: https://piazza.com/class/lci2wx0f1i74k2/post/872
    }

    @Test
    public void testEqualsOverride() {
        assertFalse(a1.equals(null));
        Activity aa = new Activity(a1.getType(), a1.getArea(), a1.getDate());
        assertTrue(a1.equals(aa));
    }
}
