package persistence;

import org.json.JSONObject;

// Taken from JsonSerializationDemo file
public interface Writable {
    // EFFECTS: returns this as JSON object
    JSONObject toJson();
}
