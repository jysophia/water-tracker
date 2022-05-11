package persistence;

import model.Progress;
import model.GraphEntry;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            Map.Entry data = reader.read();
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyFile() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyFile.json");
        try {
            Map.Entry<Progress, GraphEntry> data = reader.read();
            Progress progress = data.getKey();
            GraphEntry graph = data.getValue();
            assertEquals(0, data.getKey().getProgress().size());
            assertEquals(0, data.getValue().getGraphEntry().size());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralFile() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralFile.json");
        try {
            Map.Entry<Progress, GraphEntry> data = reader.read();
            Progress progress = data.getKey();
            GraphEntry graph = data.getValue();
            assertEquals(progress, data.getKey());
            checkDayEntry("02/24/2022", 2000, data.getKey().getProgress().get(0));
            checkDayEntry("02/24/2022", 2000, data.getValue().getGraphEntry().get(0));
        } catch (IOException e) {
            fail("Can't read from file");
        }
    }

}
