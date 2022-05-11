package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DayEntryTest {
    private DayEntry testWater;

    @BeforeEach
    public void setup() {
        testWater = new DayEntry("02/08/2022", 200);
    }

    @Test
    void testConstructor() {
        assertEquals("02/08/2022", testWater.getDate());
        assertEquals(200, testWater.getAmount());
    }

    @Test
    void testCannotEnterZeroOrNegAmount() {
        testWater.enterAmount(-100);
        assertEquals("02/08/2022", testWater.getDate());
        assertEquals(200, testWater.getAmount());
        testWater.enterAmount(-1);
        assertEquals("02/08/2022", testWater.getDate());
        assertEquals(200, testWater.getAmount());
        testWater.enterAmount(0);
        assertEquals("02/08/2022", testWater.getDate());
        assertEquals(200, testWater.getAmount());
    }

    @Test
    void testDateFormatIsCorrect() {
        testWater.enterDate("02/08/2022");
        assertEquals("02/08/2022", testWater.getDate());
        testWater.enterDate("12/13/2023");
        assertEquals("12/13/2023", testWater.getDate());
    }

    @Test
    void testCannotInputAnUnreasonablyOldDate() {
        testWater.enterDate("12/13/1999");
        assertEquals("02/08/2022", testWater.getDate());
        testWater.enterDate("01/01/1989");
        assertEquals("02/08/2022", testWater.getDate());
        testWater.enterDate("02/10/2001");
        assertEquals("02/08/2022", testWater.getDate());
        testWater.enterDate("04/13/2020");
        assertEquals("02/08/2022", testWater.getDate());
    }

    @Test
    void testCannotInputAnUnreasonablyFutureDate() {
        testWater.enterDate("12/13/3000");
        assertEquals("02/08/2022", testWater.getDate());
        testWater.enterDate("12/25/2134");
        assertEquals("02/08/2022", testWater.getDate());
    }

    @Test
    void testCannotEnterDateWithWrongFormat() {
        testWater.enterDate("2/8/2022");
        assertEquals("02/08/2022", testWater.getDate());
        testWater.enterDate("02082022");
        assertEquals("02/08/2022", testWater.getDate());
        testWater.enterDate("Feb2,2021");
        assertEquals("02/08/2022", testWater.getDate());
        testWater.enterDate("Feb 2, 2021");
        assertEquals("02/08/2022", testWater.getDate());
        testWater.enterDate("31/54/3001");
        assertEquals("02/08/2022", testWater.getDate());
        testWater.enterDate("02.08.2022");
        assertEquals("02/08/2022", testWater.getDate());
    }

    @Test
    void testDateAndAmountAreAddedToSameConstructor() {
        testWater.enterDate("02/09/2022");
        testWater.enterAmount(1000);
        assertEquals("02/09/2022",testWater.getDate());
        assertEquals(1000, testWater.getAmount());
    }
}
