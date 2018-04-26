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
public class DeliveryMealSessionTest {
    
    private DeliveryMealSession instance;
    
    public DeliveryMealSessionTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
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
       
       SystemUser cashier = builder.build();
       POS pos = new POS(cashier);
       this.instance = new DeliveryMealSession(pos);
    }
    
    @After
    public void tearDown() {
    }
    
    @Test
    public void testIsNotNull() {
        
        assertNotNull(this.instance);
    }

    /**
     * Test of date method, of class DeliveryMealSession.
     */
    @Test
    public void testDate() {
        System.out.println("date");
        
        assertNotNull(this.instance.date());
    }

    /**
     * Test of pos method, of class DeliveryMealSession.
     */
    @Test
    public void testPos() {
        System.out.println("pos");
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
       
        SystemUser cashier = builder.build();
        POS pos = new POS(cashier);
        POS result = instance.pos();
        assertTrue(pos.is(result.id()));
    }

    /**
     * Test of closeSession method, of class DeliveryMealSession.
     */
    @Test
    public void testCloseSession() {
        System.out.println("closeSession");
        this.instance.closeSession();
        boolean result = this.instance.isActive();
        assertFalse(result);
    }
    
}
