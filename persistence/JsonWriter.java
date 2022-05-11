package persistence;

import model.Progress;
import model.GraphEntry;
import org.json.JSONArray;
import org.json.JSONObject;


import java.io.*;

//Reference: JsonSerializationDemo repo link: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
//This writes the JSON representation of the workroom into the workroom file
public class JsonWriter {

    private static final int TAB = 4;
    private PrintWriter writer;
    private String destination;

    // EFFECTS: constructs writer to write to destination file
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    // MODIFIES: this
    // EFFECTS: opens writer; throws FileNotFoundException if destination file cannot
    // be opened for writing
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    // MODIFIES: this
    // EFFECTS: writes JSON representation of Progress and GraphEntry to file
    public void write(Progress progress, GraphEntry graph) {
        JSONArray progressToJson = progress.toJsonArray();
        JSONArray graphToJson = graph.toJsonArray();
        JSONObject map = new JSONObject();
        map.put("progress", progressToJson);
        map.put("graph", graphToJson);
        saveToFile(map.toString(TAB));
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
    }
}
