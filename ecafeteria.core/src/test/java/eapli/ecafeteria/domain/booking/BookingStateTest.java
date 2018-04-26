/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.booking;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author David Camelo <1161294@isep.ipp.pt>
 */
public class BookingStateTest {
    
    public BookingStateTest() {
    }

    /**
     * Test of actualState method, of class BookingState.
     */
    @Test
    public void testActualState() {
        System.out.println("actualState");
        BookingState instance = new BookingState();
        BookingState.BookingStates expResult = BookingState.BookingStates.BOOKED;
        
        BookingState.BookingStates result = instance.actualState();
        assertEquals(expResult, result);
        
        instance = new BookingState();
        if(instance.changeToCanceled()){
            expResult = BookingState.BookingStates.CANCELLED;
        
            result = instance.actualState();
            assertEquals(expResult, result);
        }else{
            fail("Not possible to change actual state");
        }
        
        instance = new BookingState();
        if(instance.changeToServed()){
            expResult = BookingState.BookingStates.SERVED;
        
            result = instance.actualState();
            assertEquals(expResult, result);
        }else{
            fail("Not possible to change actual state");
        }
        
        instance = new BookingState();
        if(instance.changeToNotServed()){
            expResult = BookingState.BookingStates.NOT_SERVED;
        
            result = instance.actualState();
            assertEquals(expResult, result);
        }else{
            fail("Not possible to change actual state");
        }
    }

    /**
     * Test of isBookingStateCancelable method, of class BookingState.
     */
    @Test
    public void testIsBookingStateCancelable() {
        System.out.println("isBookingStateCancelable");
        BookingState instance = new BookingState();
        boolean expResult = true;
        boolean result = instance.isBookingStateCancelable();
        assertEquals(expResult, result);
        
        instance = new BookingState();
        instance.changeToCanceled();
        expResult = false;
        result = instance.isBookingStateCancelable();
        assertEquals(expResult, result);
        
        instance = new BookingState();
        instance.changeToNotServed();
        expResult = false;
        result = instance.isBookingStateCancelable();
        assertEquals(expResult, result);
       
        instance = new BookingState();
        instance.changeToServed();
        expResult = false;
        result = instance.isBookingStateCancelable();
        assertEquals(expResult, result);
    }

    /**
     * Test of changeToServed method, of class BookingState.
     */
    @Test
    public void testChangeToServed() {
        System.out.println("changeToServed");
        BookingState instance = new BookingState();
        boolean expResult = true;
        boolean result = instance.changeToServed();
        assertEquals(expResult, result);
        
        instance = new BookingState();
        instance.changeToCanceled();
        expResult = false;
        result = instance.changeToServed();
        assertEquals(expResult, result);
        
        instance = new BookingState();
        instance.changeToNotServed();
        expResult = false;
        result = instance.changeToServed();
        assertEquals(expResult, result);
        
        instance = new BookingState();
        instance.changeToServed();
        expResult = false;
        result = instance.changeToServed();
        assertEquals(expResult, result);
    }

    /**
     * Test of changeToNotServed method, of class BookingState.
     */
    @Test
    public void testChangeToNotServed() {
        System.out.println("changeToNotServed");
        BookingState instance = new BookingState();
        boolean expResult = true;
        boolean result = instance.changeToNotServed();
        assertEquals(expResult, result);
        
        instance = new BookingState();
        instance.changeToCanceled();
        expResult = false;
        result = instance.changeToServed();
        assertEquals(expResult, result);
        
        instance = new BookingState();
        instance.changeToNotServed();
        expResult = false;
        result = instance.changeToServed();
        assertEquals(expResult, result);
        
        instance = new BookingState();
        instance.changeToServed();
        expResult = false;
        result = instance.changeToServed();
        assertEquals(expResult, result);
    }

    /**
     * Test of changeToCanceled method, of class BookingState.
     */
    @Test
    public void testChangeToCanceled() {
        System.out.println("changeToCanceled");
        BookingState instance = new BookingState();
        boolean expResult = true;
        boolean result = instance.changeToNotServed();
        assertEquals(expResult, result);
        
        instance = new BookingState();
        instance.changeToCanceled();
        expResult = false;
        result = instance.changeToServed();
        assertEquals(expResult, result);
        
        instance = new BookingState();
        instance.changeToNotServed();
        expResult = false;
        result = instance.changeToServed();
        assertEquals(expResult, result);
        
        instance = new BookingState();
        instance.changeToServed();
        expResult = false;
        result = instance.changeToServed();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class BookingState.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object other = new BookingState();
        BookingState instance = new BookingState();
        boolean expResult = true;
        boolean result = instance.equals(other);
        assertEquals(expResult, result);
        
        other = null;
        instance = new BookingState();
        expResult = false;
        result = instance.equals(other);
        assertEquals(expResult, result);
        
        instance = new BookingState();
        expResult = true;
        result = instance.equals(instance);
        assertEquals(expResult, result);
        
        other = new BookingState();
        ((BookingState)other).changeToCanceled();
        instance = new BookingState();
        instance.changeToCanceled();
        expResult = true;
        result = instance.equals(other);
        assertEquals(expResult, result);
    }
    
}
