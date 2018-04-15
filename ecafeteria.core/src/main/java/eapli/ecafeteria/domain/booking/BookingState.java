/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.booking;

/**
 *
 * @author jpfr8
 */
public enum BookingState {
    VALIDATED{
        public String toString(){
            return "booking is validated";
        }
    },
    CANCELED{
        public String toString(){
            return "booking is canceled";
        }
    },
    SERVED{
        public String toString(){
            return "booking was already served";
        }
    }
    
    
}
