package persistence;

import model.DayEntry;
import static org.junit.jupiter.api.Assertions.*;

public class JsonTest {
//    private DayEntry dayEntry;

    protected void checkDayEntry(String date, int amount, DayEntry dayEntry) {
        assertEquals(date, dayEntry.getDate());
        assertEquals(amount, dayEntry.getAmount());
    }
}
