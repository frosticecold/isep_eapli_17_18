/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.booking;

import eapli.ecafeteria.domain.booking.Booking;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.persistence.BookingReportingRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.ecafeteria.persistence.RepositoryFactory;
import eapli.framework.application.Controller;

/**
 *
 * @author João Rocha 1161838
 */
public class ViewNextBookingController implements Controller{
    
    private BookingReportingRepository bookingRepo; 
    private RepositoryFactory repositories;
    private CafeteriaUser user;
    
    public ViewNextBookingController(){
        repositories = PersistenceContext.repositories();
        this.bookingRepo = repositories.bookingReporting();
    }
    
    /**
     * Gets user next booking
     * @param user
     * @return 
     */
    public Booking getNextBooking(CafeteriaUser user){
        return bookingRepo.findNextBooking(user);
    }
}
