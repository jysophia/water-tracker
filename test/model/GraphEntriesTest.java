package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GraphEntriesTest {
    private GraphEntry testGraph;

    @BeforeEach
    public void setup() {
        testGraph = new GraphEntry();
    }

    @Test
    public void testDayEntryIsAddedToGraph() {
        DayEntry testDayEntry1 = new DayEntry("02/20/2022", 2500);
        testGraph.addToGraph(testDayEntry1);
        List<DayEntry> graphValues = testGraph.getGraphEntry();
        assertEquals(graphValues.get(0), testDayEntry1);
        assertEquals(graphValues.size(), 1);

        DayEntry testDayEntry2 = new DayEntry("02/21/2022", 1400);
        testGraph.addToGraph(testDayEntry2);
        graphValues = testGraph.getGraphEntry();
        assertEquals(graphValues.get(0), testDayEntry1);
        assertEquals(graphValues.get(1), testDayEntry2);
        assertEquals(graphValues.size(), 2);
    }
}
