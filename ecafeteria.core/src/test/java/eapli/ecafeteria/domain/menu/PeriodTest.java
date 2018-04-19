/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.menu;

import eapli.framework.util.DateTime;
import java.text.ParseException;
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
    public void testInvalidFormat() throws IllegalArgumentException, java.text.ParseException {
        System.out.println("invalidPeriodFormat");
        Calendar start = DateTime.now();
        do {
            start.add(Calendar.DATE, 1);
        } while (start.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY);
        start.add(Calendar.DATE, -1);
        Calendar end = (Calendar) start.clone();
        do {
            start.add(Calendar.DATE, 1);
        } while (start.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY);
        end.add(Calendar.DATE, 1);
        String startString = start.get(Calendar.DAY_OF_MONTH) + "-" + (start.get(Calendar.MONTH) + 1) + "-" + start.get(Calendar.YEAR);
        String endString = start.get(Calendar.DAY_OF_MONTH) + "-" + (end.get(Calendar.MONTH) + 1) + "-" + end.get(Calendar.YEAR);
        Period instance = new Period(startString, endString);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidFormat2() throws IllegalArgumentException {
        System.out.println("invalidPeriodFormat2");
        Calendar start = DateTime.now();
        do {
            start.add(Calendar.DATE, 1);
        } while (start.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY);
        Calendar end = (Calendar) start.clone();
        do {
            start.add(Calendar.DATE, 1);
        } while (start.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY);

        String startString = start.get(Calendar.DAY_OF_MONTH) + "-" + (start.get(Calendar.MONTH) + 1) + "-" + start.get(Calendar.YEAR);
        String endString = start.get(Calendar.DAY_OF_MONTH) + "-" + (end.get(Calendar.MONTH) + 1) + "-" + end.get(Calendar.YEAR);
        Period instance = new Period(startString, endString);
    }

    @Test
    public void testValidPeriod() throws IllegalArgumentException {
        System.out.println("validPeriod");
        Calendar cal = DateTime.now();
        do {
            cal.add(Calendar.DATE, 1);
        } while (cal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY);
        Calendar end = (Calendar) cal.clone();
        do {
            end.add(Calendar.DATE, 1);
        } while (end.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY);
        String startString, endString;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        //================STARTING STRING
        if (day < 10) {
            startString = "0" + day;
        } else {
            startString = "" + day;
        }
        int month = (cal.get(Calendar.MONTH) + 1);
        if (month < 10) {
            startString += "-0" + month;
        } else {
            startString += "-" + month;
        }
        int year = cal.get(Calendar.YEAR);
        startString += "-" + year;

        //Ending String
        day = end.get(Calendar.DAY_OF_MONTH);
        month = (end.get(Calendar.MONTH) + 1);
        year = end.get(Calendar.YEAR);
        if (day < 10) {
            endString = "0" + day;
        } else {
            endString = "" + day;
        }
        if (month < 10) {
            endString += "-0" + month;
        } else {
            endString += "-" + month;
        }
        endString += "-" + year;
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
