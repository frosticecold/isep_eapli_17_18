/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.authz;

import eapli.ecafeteria.domain.deactivationreasons.DeactivationReasonType;
import eapli.framework.dto.GenericDTO;
import eapli.framework.util.DateTime;
import eapli.framework.visitor.Visitor;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Raúl Correia <1090657@isep.ipp.pt>
 */
public class SystemUserTest {

    private SystemUser user;

    public SystemUserTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        Set<RoleType> roles = new HashSet<>();
        roles.add(RoleType.ADMIN);
        user = new SystemUser("USERA", "Password1", "FIRST", "LAST", "email@email.pt", roles);
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of deactivate method, of class SystemUser.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testDeactivateUserNullCalendar() {
        System.out.println("deactivateUserNullCalendar");
        Calendar deactivatedOn = null;
        DeactivationReasonType reason = new DeactivationReasonType("Random reason");
        String comment = "Abcdefg";
        SystemUser instance = user;
        /**
         * Passing null calendar throws exception
         */
        instance.deactivate(deactivatedOn, reason, comment);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDeactivateUserNullReason() {
        System.out.println("deactivateUserNullReason");
        Calendar deactivatedOn = DateTime.now();
        DeactivationReasonType reason = null;
        String comment = "Abcdefg";
        SystemUser instance = user;
        /**
         * Passing null reason, throws exception
         */
        instance.deactivate(deactivatedOn, reason, comment);
    }

    @Test(expected = IllegalStateException.class)
    public void testDeactivateUserTwice() {
        System.out.println("deactivateUserNullReason");
        Calendar deactivatedOn = DateTime.now();
        DeactivationReasonType reason = new DeactivationReasonType("new Reason");
        String comment = "ASDASD";
        SystemUser instance = user;
        instance.deactivate(deactivatedOn, reason, comment);
        /**
         * This should throw an execption
         */
        instance.deactivate(deactivatedOn, reason, comment);
    }

    @Test
    public void testDeactivateUserValid() {
        System.out.println("deactivateUserNullReason");
        Calendar deactivatedOn = DateTime.now();
        final String reason_description = "TestReason";
        DeactivationReasonType reason = new DeactivationReasonType(reason_description);
        String comment = "ASDASD";
        SystemUser instance = user;
        instance.deactivate(deactivatedOn, reason, comment);
        assertTrue(instance.getReason().getReasonType().equals(reason));
    }

}
