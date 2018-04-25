/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.booking;

import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.domain.booking.Booking;
import eapli.ecafeteria.domain.booking.BookingState;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.domain.CreditTransaction.CancelationBooking;
import eapli.ecafeteria.persistence.AutoTxBookingRepository;
import eapli.ecafeteria.persistence.AutoTxTransactionRepository;
import eapli.ecafeteria.persistence.BookingReportingRepository;
import eapli.ecafeteria.persistence.BookingRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.ecafeteria.persistence.RepositoryFactory;
import eapli.framework.domain.money.Money;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.persistence.repositories.TransactionalContext;
import java.util.List;

/**
 *
 * @author David Camelo 1161294@isep.ipp.pt
 */
public class CancelBookingController {

    /**
     * Factory
     */
    RepositoryFactory factory;
    
    /**
     * Bookings repository
     */
    private BookingReportingRepository bookingReportingRepository = null;
    
    /**
     * Bookings reporting repository
     */
    private BookingRepository bookingRepository = null;
    
    /**
     * List with all bookings of the user
     */
    private List<Booking> bookings = null;
    
    /**
     * Selected booking
     */
    private Booking selectedBooking = null;
    
    /**
     * Cafeteria user
     */
    private CafeteriaUser user = null;
    
    /**
     * Refund for the booking
     */
    private Money refund = null;

    /**
     * Constructor
     * 
     */
    public CancelBookingController() {   
        this.factory = PersistenceContext.repositories();
        
        this.user = factory.cafeteriaUsers().findByUsername(
                AuthorizationService.session().authenticatedUser().username())
                .get();
        
        bookingRepository = factory.booking();
        bookingReportingRepository = factory.bookingReporting();
        
        BookingState booked = new BookingState();
        bookings = bookingReportingRepository.
            findBookingsByCafeteriaUser(user, booked);
    }
    
    /**
     * Shows booking that are in the booked state
     * 
     * @return list of bookings
     */
    public List<Booking> showBookings(){
        return bookings;
    }
    
    /**
     * Selects booking from booking list
     * 
     * @param idBooking position of booking in list 
     */
    public void selectBooking(int idBooking){
        if(0 <= idBooking && idBooking < bookings.size())
            this.selectedBooking = bookings.get(idBooking);
        else
            throw new IllegalArgumentException("Booking id " + 
                    idBooking + "is not valid!");
    }
    
    /**
     * Informs user of the possibility to cancel the booking
     * 
     * @return true if changed
     */
    public boolean informBookingState(){
        boolean isCancelable = selectedBooking.isBookingCancelable();
        
        if(isCancelable)
            this.refund = selectedBooking.refundForCancelation();
        else
            this.refund = null;
        
        return isCancelable;
    }
    
    public String informRefundValue(){
        if(refund == null)
            return "No refund found!";
        else
            return "Refund: " + refund.toString();
    }
    
    /**
     * Confirms cancelation and persists changes
     * 
     * @return true after conclusion 
     * @throws eapli.framework.persistence.DataConcurrencyException 
     * @throws eapli.framework.persistence.DataIntegrityViolationException 
     */
    public boolean confirmCancelation() throws DataConcurrencyException, 
            DataIntegrityViolationException{
        //Change state
        selectedBooking.getBookingState().changeToCanceled();
        // Add cancelation movement
        CancelationBooking cb = new CancelationBooking(user, refund);
        
        // Persist
        final TransactionalContext TxCtx 
                = PersistenceContext.repositories().buildTransactionalContext();
        final AutoTxTransactionRepository attr = 
                PersistenceContext.repositories().autoTxTransactionRepository(TxCtx);
        final AutoTxBookingRepository atbr = 
                PersistenceContext.repositories().autoTxBookingRepository(TxCtx);
        
        TxCtx.beginTransaction();
        
        /* persist here */
        atbr.saveBooking(selectedBooking);
        if(refund != null)
            attr.saveTransaction(cb);
        else
            attr.saveTransaction(cb);
        TxCtx.commit();
        
        return true;
    }
}
