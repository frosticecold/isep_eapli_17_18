/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.reasons;

import eapli.ecafeteria.domain.authz.RoleType;
import eapli.ecafeteria.domain.authz.SystemUser;
import java.util.HashSet;
import java.util.Set;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Raúl Correia <1090657@isep.ipp.pt>
 */
public class ReasonTest {

    private static SystemUser system_user;

    public ReasonTest() {
    }

    @BeforeClass
    public static void setUpClass() {
        Set<RoleType> set = new HashSet<>();
        set.add(RoleType.CAFETERIA_USER);
        system_user = new SystemUser("123456789", "Password1", "User", "Top", "email@email.pt", set);
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test Null User
     */
    @Test(expected = IllegalArgumentException.class)
    public void testReasonNullUser() {
        Reason r = new Reason(null, ReasonType.DEACTIVATION_OTHER, "Comment 123");
    }

    /**
     * Test Null ReasonType
     */
    @Test(expected = IllegalArgumentException.class)
    public void testReasonNullReasonType() {
        Reason r = new Reason(system_user, null, "Comment 123");
    }

    /**
     * Test Null User
     */
    @Test(expected = IllegalArgumentException.class)
    public void testReasonNullComment() {
        Reason r = new Reason(system_user, ReasonType.DEACTIVATION_OTHER, null);
    }

}
