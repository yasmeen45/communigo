package model;

import ui.Application;

import java.time.LocalDate;

public class Activity {

    private ui.Application.Type type;
    private ui.Application.Area area;
    private LocalDate date; //https://docs.oracle.com/javase/8/docs/api/java/time/LocalDate.html

    // EFFECTS: create a new activity with give type, area, and date
    public Activity(ui.Application.Type type, ui.Application.Area area, LocalDate date) {
        this.type = type;
        this.area = area;
        this.date = date;
    }

    // EFFECTS: return true if contents of a is equal to this
    public boolean equalTo(Activity a) {
        return ((this.type == a.getType()) && (this.area == a.getArea())
                && (this.date.toString().equals(a.getDate().toString())));
    }

    // EFFECTS: return date
    public LocalDate getDate() {
        return this.date;
    }

    // EFFECTS: return type
    public Application.Type getType() {
        return this.type;
    }

    // EFFECTS: return area
    public Application.Area getArea() {
        return this.area;
    }

    // EFFECTS: return type as a printable string
    public String getTypeToPrint() {
        switch (this.type) {
            case WALK:
                return "Walking";
            case RUN:
                return "Running";
            case BIKE:
                return "Biking";

        }
        return null;
    }

    // EFFECTS: return area as a printable string
    public String getAreaToPrint() {
        switch (this.area) {
            case VANCOUVER:
                return "Vancouver";
            case BURNABY:
                return "Burnaby";
            case SURREY:
                return "Surrey";
        }
        return null;
    }
}
