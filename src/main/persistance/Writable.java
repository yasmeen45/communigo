package persistance;

import org.json.JSONObject;

// citation: modelled after Json Demo provided in P2 description on EdX

public interface Writable {

    // EFFECTS: returns this as a JSON object
    JSONObject toJson();
}
