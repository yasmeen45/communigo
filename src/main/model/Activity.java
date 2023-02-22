package model;

public class Activity {

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

    Type type;
    Area area;
    String date; // FORMAT CORRECTLY


    public Activity() {
        type = Type.WALK;
        area = Area.SURREY;
    }

}
