package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//Reference: AlarmSystem https://github.students.cs.ubc.ca/CPSC210/AlarmSystem.git
public class EventLogTest {

    private Event event1;
    private Event event2;
    private Event event3;

    @BeforeEach
    public void setup() {
        event1 = new Event("Event 1");
        event2 = new Event("Event 2");
        event3 = new Event("Event 3");
        EventLog el = EventLog.getInstance();
        el.logEvent(event1);
        el.logEvent(event2);
        el.logEvent(event3);
    }

    @Test
    public void testLogEvents() {
        List<Event> l = new ArrayList<>();

        EventLog el = EventLog.getInstance();
        for (Event next : el) {
            l.add(next);
        }

        assertTrue(l.contains(event1));
        assertTrue(l.contains(event2));
        assertTrue(l.contains(event3));
    }
}
