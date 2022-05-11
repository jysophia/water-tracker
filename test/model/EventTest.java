package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

//Reference: AlarmSystem https://github.students.cs.ubc.ca/CPSC210/AlarmSystem.git
public class EventTest {

    private Event event;
    private Date date;
    private static final int HASH_CONSTANT = 13;

    @BeforeEach
    public void setup() {
        event = new Event("Added a new entry to user's progress");
        date = Calendar.getInstance().getTime();
    }

    @Test
    public void testEvent() {
        assertEquals("Added a new entry to user's progress", event.getDescription());
        assertEquals(date, event.getDate());
    }

    @Test
    public void testHashCode() {
        assertEquals(HASH_CONSTANT * date.hashCode() + event.getDescription().hashCode(), event.hashCode());
    }

    @Test
    public void testToString() {
        assertEquals(date.toString() + "\n" + "Added a new entry to user's progress", event.toString());
    }
}
