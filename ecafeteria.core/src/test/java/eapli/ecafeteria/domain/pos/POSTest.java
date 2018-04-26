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
    
    private  SystemUser cashier;
    private POS instance; //pos to test
    
    public POSTest() {
      
    }
    
    @BeforeClass
    public static void setUpClass() throws Exception{
    }
    
    @AfterClass
    public static void tearDownClass() throws Exception {
    }
    
    @Before
    public void setUp() throws Exception{
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
    public void testId(){
        System.out.println("id");
        Long result = instance.id(); 
        assertNotNull(result);
    }

    /**
     * Test of isClosed method, of class POS.
     */
    @Test
    public void testIsClosed() {
        System.out.println("isClosed");
        boolean expResult = false;
        boolean result = this.instance.isClosed();
        assertEquals(expResult, result);
    }

    /**
     * Test of changeState method, of class POS.
     */
    @Test
    public void testChangeState() {
        System.out.println("changeState");
        instance.changeState();
        boolean expResult = true;
        boolean result = instance.isClosed();
        assertEquals(expResult,result);
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
    
}
