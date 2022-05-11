package persistence;

import model.DayEntry;
import model.Progress;
import model.GraphEntry;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.AbstractMap;
import java.util.Map;
import java.util.stream.Stream;

//Reference: JsonSerializationDemo repo link: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
//This represents a reader that reads Progress and GraphHistory from JSON data stored in file
public class JsonReader {

    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads Progress and GraphEntry from file and returns it;
    // throws IOException if an error occurs reading Progress and GraphEntry from file
    public Map.Entry<Progress, GraphEntry> read() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        Progress progress = parseProgress(jsonObject);
        GraphEntry graph = parseGraphEntry(jsonObject);
        AbstractMap.SimpleEntry<Progress, GraphEntry> tracker = new AbstractMap.SimpleEntry<>(progress, graph);
        return tracker;
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses Progress from JSON object and returns it
    private Progress parseProgress(JSONObject jsonObject) {
        Progress progress = new Progress();
        addDayEntry(progress, jsonObject);
        return progress;
    }

    // EFFECTS: parses GraphEntry from JSON object and returns it
    private GraphEntry parseGraphEntry(JSONObject jsonObject) {
        GraphEntry graph = new GraphEntry();
        addGraphEntry(graph, jsonObject);
        return graph;
    }

    // MODIFIES: progress
    // EFFECTS: parses DayEntry from JSON object and adds them to progress
    private void addDayEntry(Progress progress, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("progress");
        for (Object json : jsonArray) {
            JSONObject nextDayEntry = (JSONObject) json;
            addDayEntryToProgress(progress, nextDayEntry);
        }
    }

    // MODIFIES: graph
    // EFFECTS: parses DayEntry from JSON object and adds them to graph
    private void addGraphEntry(GraphEntry graph, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("graph");
        for (Object json : jsonArray) {
            JSONObject nextDayEntry = (JSONObject) json;
            addDayEntryToGraph(graph, nextDayEntry);
        }
    }

    // MODIFIES: progress
    // EFFECTS: parses DayEntry from JSON object and adds it to Progress
    private void addDayEntryToProgress(Progress progress, JSONObject jsonObject) {
        String date = jsonObject.getString("date");
        int amt = jsonObject.getInt("amount");
        DayEntry entry = new DayEntry(date, amt);
        progress.addToProgress(entry);
    }

    // MODIFIES: graph
    // EFFECTS: parses DayEntry from JSON object and adds it to GraphEntry
    private void addDayEntryToGraph(GraphEntry graph, JSONObject jsonObject) {
        String date = jsonObject.getString("date");
        int amt = jsonObject.getInt("amount");
        DayEntry entry = new DayEntry(date, amt);
        graph.addToGraph(entry);
    }
}
