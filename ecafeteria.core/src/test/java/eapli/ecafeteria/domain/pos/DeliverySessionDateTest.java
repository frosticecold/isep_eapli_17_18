
package eapli.ecafeteria.domain.pos;

import eapli.framework.util.DateTime;
import java.util.Calendar;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


/**
 *
 * @author PedroEmanuelCoelho 1131485@isep.ipp.pt
 */
public class DeliverySessionDateTest {
    
    private Calendar ca;
    private DeliverySessionDate instance;

    /**
     * @throws java.lang.Exception
     */
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    /**
     * @throws java.lang.Exception
     */
    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    /**
     * @throws java.lang.Exception
     */
    @Before
    public void setUp() throws Exception {

        ca = DateTime.now();

        instance = new DeliverySessionDate(ca);
    }

    /**
     * @throws java.lang.Exception
     */
    @After
    public void tearDown() throws Exception {
    }
    
    @Test
    public void ensureHourIsValidated() {

        int expected = ca.HOUR_OF_DAY;
        assertEquals(expected,instance.Hour(),0);
    }

    @Test
    public void ensureMinutesAreValidated() {

        int expected = ca.MINUTE;
        assertEquals(expected, instance.Minutes(),0);
    }

    @Test
    public void ensureSecondsAreValidated() {

        int expected = ca.SECOND;
        assertEquals(expected, instance.Seconds(),0);
    }

    @Test
    public void ensureYearIsValidated() {

        int expected = ca.YEAR;
        assertEquals(expected, instance.Year(),0);
    }

    @Test
    public void ensureMonthIsValidated() {

        int expected = ca.MONTH;
        assertEquals(expected, instance.Month(), 0);
    }

    @Test
    public void ensureDayIsValidated() {

        int expected = ca.DAY_OF_MONTH;
        assertEquals(expected, instance.Day(), 0);
    }
}
