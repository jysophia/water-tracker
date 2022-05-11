package model;


import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

//Reference: JsonSerializationDemo repo link: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
//This class is a list of DayEntry, and it will eventually be used for the graphing feature on this app
public class GraphEntry {
    private List<DayEntry> myGraph;

    public GraphEntry() {
        myGraph = new ArrayList<>();
    }

    public void addToGraph(DayEntry entry) {
        myGraph.add(entry);
        EventLog.getInstance().logEvent(new Event("Summarized and compared user's progress."));
    }

    public List<DayEntry> getGraphEntry() {
        return myGraph;
    }

    public JSONArray toJsonArray() {
        JSONArray jsonArray = new JSONArray();

        for (DayEntry dayEntry : myGraph) {
            jsonArray.put(dayEntry.toJson());
        }

        return jsonArray;
    }
}
