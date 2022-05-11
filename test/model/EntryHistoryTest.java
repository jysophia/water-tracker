package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class EntryHistoryTest {
    private Progress testHistory;

    @BeforeEach
    public void setup() {
        testHistory = new Progress();
    }

    @Test
    public void testDayEntryIsAdded() {
        DayEntry testDayEntry1 = new DayEntry("02/08/2022", 1000);
        testHistory.addToProgress(testDayEntry1);
        List<DayEntry> wholeList = testHistory.getProgress();
        assertEquals(wholeList.get(0), testDayEntry1);
        assertEquals(wholeList.size(), 1);

        DayEntry testDayEntry2 = new DayEntry("02/09/2022", 2000);
        wholeList = testHistory.getProgress();
        testHistory.addToProgress(testDayEntry2);
        assertEquals(wholeList.get(0), testDayEntry1);
        assertEquals(wholeList.get(1), testDayEntry2);
        assertEquals(wholeList.size(), 2);
    }
}
