package persistance;

// citation: modelled after Json Demo provided in P2 description on EdX

import model.Manager;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

// Represents a writer that writes a JSON representation of Communigo manager (all data) to file
public class JsonWriter {
    private static final int TAB = 4;
    private PrintWriter writer;
    private String path;

    // EFFECTS: creates a writer to write to file at given path
    public JsonWriter(String path) {
        this.path = path;
    }

    // MODIFIES: this
    // EFFECTS: opens a writer in a new file at path
    //          throws FileNotFoundException if file cannot be opened
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(path));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of manager to file
    public void write(Manager manager) {
        JSONObject object = manager.toJson();
        saveToFile(object.toString(TAB));
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile(String object) {
        writer.print(object);
    }

    // MODIFIES: this
    // EFFECTS: close writer
    public void close() {
        writer.close();
    }
}
