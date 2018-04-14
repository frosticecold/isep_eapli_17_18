/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.menu;

import eapli.framework.util.DateTime;
import java.util.Calendar;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author Raúl Correia <1090657@isep.ipp.pt>
 */
public class PeriodTest {
    
    public PeriodTest() {
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testInvalidFormat() {
        System.out.println("invalidPeriod");
        Calendar start = DateTime.now();
        do {
            start.add(Calendar.DATE, 1);
        } while (start.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY);
        Calendar end = (Calendar) start.clone();
        do {
            start.add(Calendar.DATE, 1);
        } while (start.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY);
        
        String startString = start.get(Calendar.DAY_OF_MONTH) + "#" + (start.get(Calendar.MONTH) + 1) + "#" + start.get(Calendar.YEAR);
        System.out.println(startString);
        String endString = start.get(Calendar.DAY_OF_MONTH) + "-" + (end.get(Calendar.MONTH) + 1) + "-" + end.get(Calendar.YEAR);
        System.out.println(endString);
        Period instance = new Period(startString, endString);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testInvalidFormat2() {
        System.out.println("invalidPeriod2");
        Calendar start = DateTime.now();
        do {
            start.add(Calendar.DATE, 1);
        } while (start.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY);
        Calendar end = (Calendar) start.clone();
        do {
            start.add(Calendar.DATE, 1);
        } while (start.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY);
        
        String startString = start.get(Calendar.DAY_OF_MONTH) + "-" + (start.get(Calendar.MONTH) + 1) + "-" + start.get(Calendar.YEAR);
        System.out.println(startString);
        String endString = start.get(Calendar.DAY_OF_MONTH) + "#" + (end.get(Calendar.MONTH) + 1) + "#" + end.get(Calendar.YEAR);
        System.out.println(endString);
        Period instance = new Period(startString, endString);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testValidPeriod() {
        System.out.println("validPeriod");
        Calendar start = DateTime.now();
        do {
            start.add(Calendar.DATE, 1);
        } while (start.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY);
        Calendar end = (Calendar) start.clone();
        do {
            start.add(Calendar.DATE, 1);
        } while (start.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY);
        
        String startString = start.get(Calendar.DAY_OF_MONTH) + "-" + (start.get(Calendar.MONTH) + 1) + "-" + start.get(Calendar.YEAR);
        System.out.println(startString);
        String endString = start.get(Calendar.DAY_OF_MONTH) + "-" + (end.get(Calendar.MONTH) + 1) + "-" + end.get(Calendar.YEAR);
        System.out.println(endString);
        Period instance = new Period(startString, endString);
        Assert.assertNotNull(instance);
    }

    /**
     * Test of isCritical method, of class Period.
     */
    /*@Test
    public void testIsCritical() {
        System.out.println("isCritical");
        Period instance = null;
        boolean expResult = false;
        boolean result = instance.isCritical();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }
     */
}
