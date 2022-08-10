package persistence;

import model.Event;
import model.EventLog;
import model.RatedRestaurants;
import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

// Taken from JsonSerializationDemo file
// Represents a writer that writes JSON representation of workroom to file
public class JsonWriterAllRestaurants {
    private static final int TAB = 4;
    private PrintWriter writer;
    private String destination;

    // EFFECTS: constructs writer to write to destination file
    public JsonWriterAllRestaurants(String destination) {
        this.destination = destination;
    }

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file cannot
    // be opened for writing
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of RatedRestaurants restaurant to file
    public void write(RatedRestaurants restaurants) {
        JSONObject json = restaurants.toJson();
        saveToFile(json.toString(TAB));
    }

    // MODIFIES: this
    // EFFECTS: closes writer
    public void close() {
        writer.close();
    }

    // MODIFIES: this
    // EFFECTS: writes string to file
    private void saveToFile(String json) {
        writer.print(json);
        EventLog.getInstance().logEvent(new Event("Saved Restaurant reviews to file."));
    }
}
