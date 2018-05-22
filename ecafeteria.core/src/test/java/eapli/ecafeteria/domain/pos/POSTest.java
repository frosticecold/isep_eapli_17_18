package eapli.ecafeteria.domain.pos;

import eapli.ecafeteria.domain.authz.RoleType;
import eapli.ecafeteria.domain.authz.SystemUser;
import eapli.ecafeteria.domain.authz.SystemUserBuilder;
import eapli.ecafeteria.domain.authz.Username;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author PedroEmanuelCoelho 1131485@isep.ipp.pt
 */
public class POSTest {

    private SystemUser cashier;
    private POS instance; //pos to test

    public POSTest() {

    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
        SystemUserBuilder builder = new SystemUserBuilder();
        Username username = new Username("pedro");
        String pw = "Password1";
        String firstName = "pedro";
        String lastName = "coelho";
        builder.withEmail("1131485@isep.ipp.pt");
        builder.withPassword(pw);
        builder.withUsername(username);
        builder.withRole(RoleType.CASHIER);
        builder.withFirstName(firstName);
        builder.withLastName(lastName);

        this.cashier = builder.build();
        this.instance = new POS(this.cashier);
    }

    @After
    public void tearDown() throws Exception {
    }

    /**
     * Test POS
     */
    @Test
    public void testPos() {
        assertNotNull(this.instance);
    }

    /**
     * Test of id method, of class POS.
     */
    @Test
    public void testId() {
        System.out.println("id");
        Long result = instance.id();
        assertNotNull(result);
    }


    /**
     * Test of cashier method, of class POS.
     */
    @Test
    public void testCashier() {
        System.out.println("cashier");
        SystemUser result = instance.cashier();
        assertNotNull(result);
    }

    /**
     * Test of openPOS method, of class POS.
     */
    @Test
    public void testOpenPOS() {
        System.out.println("openPOS");
        this.instance.openPOS();
        assertTrue(this.instance.checkState());
    }

    /**
     * Test of closePOS method, of class POS.
     */
    @Test
    public void testClosePOS() {
        System.out.println("closePOS");
        this.instance.closePOS();
        assertFalse(this.instance.checkState());
    }

    /**
     * Test of openSession method, of class POS.
     */
    @Test
    public void testOpenSession() {
        System.out.println("openSession");
        DeliveryMealSession result = instance.openSession();
        assertNotNull(result);
    }

    /**
     * Test of checkState method, of class POS.
     */
    @Test
    public void testCheckState() {
        System.out.println("checkState");
        this.instance.closePOS();
        boolean expResult = false;
        boolean result = instance.checkState();
        assertEquals(expResult, result);
    }

}
