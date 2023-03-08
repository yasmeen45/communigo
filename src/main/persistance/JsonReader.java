package persistance;


import model.Activity;
import model.Manager;
import model.User;
import org.json.JSONArray;
import org.json.JSONObject;
import ui.Application;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

// citation: modelled after Json Demo provided in P2 description on EdX

// Represents a reader that reads Communigo data from JSON data in a file
public class JsonReader {
    private String path;

    // EFFECTS: creates a reader to read from file at given path
    public JsonReader(String path) {
        this.path = path;
    }

    // EFFECTS: reads Manager data from file and returns it;
    //          throws IOException if an error occurs with reading the data
    public Manager read() throws IOException {
        String data = readFile(path);
        JSONObject object = new JSONObject(data);
        return parseManager(object);
    }

    // EFFECTS: read file and return it as String
    private String readFile(String path) throws IOException {
        StringBuilder builder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(path), StandardCharsets.UTF_8)) {
            stream.forEach(s -> builder.append(s));
        }

        return builder.toString();
    }

    // EFFECTS: parses Manger from JSON object and returns it
    private Manager parseManager(JSONObject object) {
        List<Activity> upcomingActivities = parseActivityList(object.getJSONArray("upcoming activities"));
        JSONObject userObject = object.getJSONObject("user");
        List<Activity> registeredActivities = parseActivityList(userObject.getJSONArray("registered activities"));
        List<Activity> postedActivities = parseActivityList(userObject.getJSONArray("posted activities"));

        User user = new User(registeredActivities, postedActivities);
        Manager manager = new Manager(user, upcomingActivities);
        return manager;
    }

    // EFFECTS: parses a list of activities from JSONArray and returns it
    private List<Activity> parseActivityList(JSONArray array) {
        List<Activity> result = new ArrayList<>();
        for (Object object : array) {
            JSONObject activityObject = (JSONObject) object;
            ui.Application.Type type = Application.Type.valueOf(activityObject.getString("type"));
            ui.Application.Area area = Application.Area.valueOf(activityObject.getString("area"));
            String dateString = activityObject.getString("date");
            LocalDate date = LocalDate.parse(dateString);
            Activity newActivity = new Activity(type, area, date);
            result.add(newActivity);
        }
        return result;
    }

}







