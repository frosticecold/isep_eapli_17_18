/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.booking;

/**
 *
 * @author David Camelo 1161294@isep.ipp.pt
 */
public enum BookingStates {
    BOOKED{
        public String toString(){
            return "Booking state is booked";
        }
    },
    SERVED{
        public String toString(){
            return "Booking state is served";
        }
    },
    NOT_SERVED{
        public String toString(){
            return "Booking state is not served";
        }
    },
    CANCELED{
        public String toString(){
            return "Booking state is canceled";
        }
    }
}
