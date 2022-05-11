package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

//Reference: AlarmSystem https://github.students.cs.ubc.ca/CPSC210/AlarmSystem.git
//This class logs all the events that took place (Singleton Design Pattern)
public class EventLog implements Iterable<Event> {
    private static EventLog theLog;
    private Collection<Event> logs;

    //MODIFIES: this
    //EFFECTS: constructs the event log
    public EventLog() {
        logs = new ArrayList<Event>();
    }

    //MODIFIES: this
    //EFFECTS: gets the instance of an EventLog if it does not exist
    public static EventLog getInstance() {
        if (theLog == null) {
            theLog = new EventLog();
        }

        return theLog;
    }

    //MODIFIES: this
    //EFFECTS: adds an event to the event log
    public void logEvent(Event e) {
        logs.add(e);
    }

    //MODIFIES: this
    //EFFECTS: iterates the log through an iterator
    @Override
    public Iterator<Event> iterator() {
        return logs.iterator();
    }
}
