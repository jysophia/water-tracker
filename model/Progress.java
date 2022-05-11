package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;

//Reference: JsonSerializationDemo repo link: https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git
//This class is a list of DayEntry, and it stores all the entries made every time the user makes an entry
public class Progress {
    private List<DayEntry> myProgress;

    //EFFECT: instantiates a new list of DayEntry
    public Progress() {
        myProgress = new ArrayList<>();
    }

    //REQUIRES: DayEntry input
    //MODIFIES: this
    //EFFECT: adds given DayEntry into Progress
    public void addToProgress(DayEntry entry) {
        myProgress.add(entry);
        EventLog.getInstance().logEvent(new Event("Added a new entry to user's progress."));
    }

    //EFFECT: gets the full list of DayEntry objects inputted into Progress
    public List<DayEntry> getProgress() {
        return myProgress;
    }

    public JSONArray toJsonArray() {
        JSONArray jsonArray = new JSONArray();

        for (DayEntry dayEntry : myProgress) {
            jsonArray.put(dayEntry.toJson());
        }

        return jsonArray;
    }
}
