/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.deactivationreasons;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Raúl Correia <1090657@isep.ipp.pt>
 */
public class DeactivationReasonTypeTest {

    @Test(expected = IllegalArgumentException.class)
    public void testDeactivationReasonNull() {
        DeactivationReasonType dr = new DeactivationReasonType(null);
        assertNull(dr);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDeactivationReasonEmpty() {
        DeactivationReasonType dr = new DeactivationReasonType("");
        assertNull(dr);
    }
    
    
    public void testDeactivationReasonString(){
        DeactivationReasonType dr = new DeactivationReasonType("No business rules limiting this.");
        assertNotNull(dr);
    }

}
