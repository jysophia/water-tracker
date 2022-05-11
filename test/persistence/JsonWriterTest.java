package persistence;

import model.DayEntry;
import model.Progress;
import model.GraphEntry;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            Progress progress = new Progress();
            JsonWriter writer = new JsonWriter("./data/thisis\0illegalfile.json");
            writer.open();
            fail("IOException was expected");

            GraphEntry graph = new GraphEntry();
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            //pass
        }
    }

    @Test
    void testWriterEmptyFile() {
        JsonWriter writer = new JsonWriter("./data/testWriterEmptyFile.json");
        Progress progress = new Progress();
        GraphEntry graph = new GraphEntry();
        try {
            writer.open();
            writer.write(progress, graph);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyFile.json");
            Map.Entry<Progress, GraphEntry> data = reader.read();
            assertEquals(0, data.getKey().getProgress().size());
            assertEquals(0, data.getValue().getGraphEntry().size());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }


    @Test
    void testWriterGeneralFile() {
        Progress progress = new Progress();
        GraphEntry graph = new GraphEntry();
        try {
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralFile.json");
            progress.addToProgress(new DayEntry("02/24/2022", 2000));
            graph.addToGraph(new DayEntry("02/24/2022", 2000));
            writer.open();
            writer.write(progress, graph);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralFile.json");
            Map.Entry<Progress, GraphEntry> data = reader.read();
            assertEquals(1, data.getKey().getProgress().size());
            assertEquals(1, data.getValue().getGraphEntry().size());
            checkDayEntry("02/24/2022", 2000, data.getKey().getProgress().get(0));
            checkDayEntry("02/24/2022", 2000, data.getValue().getGraphEntry().get(0));
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

}
