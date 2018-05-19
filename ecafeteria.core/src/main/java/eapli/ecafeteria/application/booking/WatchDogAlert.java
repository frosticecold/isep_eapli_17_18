/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.booking;

import eapli.ecafeteria.application.cafeteriauser.CafeteriaUserService;
import eapli.ecafeteria.domain.booking.Booking;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.domain.cafeteriauser.MecanographicNumber;
import eapli.ecafeteria.persistence.BookingRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author Miguel Santos <1161386@isep.ipp.pt>
 */
public class WatchDogAlert extends Observable implements Observer{
    private final CafeteriaUserService userService = new CafeteriaUserService();
    private final BookingRepository repository = PersistenceContext.repositories().booking();
    
    @Override
    public void update(Observable o, Object arg) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private boolean verifyBalanceLimits(Booking booking){
        CafeteriaUser user = booking.getCafeteriauser();
        MecanographicNumber number = user.mecanographicNumber();
        userService.findCafeteriaUserByMecNumber(number.toString());
        return true;
    }
    
    
}
