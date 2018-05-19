/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.booking;

import eapli.ecafeteria.application.cafeteriauser.CafeteriaUserService;
import eapli.ecafeteria.domain.booking.Booking;
import eapli.ecafeteria.domain.cafeteriauser.BalanceLimits;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.domain.cafeteriauser.MecanographicNumber;
import eapli.ecafeteria.persistence.BalanceLimitsRepository;
import eapli.ecafeteria.persistence.BookingRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import java.util.Observable;
import java.util.Observer;
import java.util.Optional;

/**
 *
 * @author Miguel Santos <1161386@isep.ipp.pt>
 */
public class WatchDogAlert extends Observable implements Observer {

    private final BalanceLimitsRepository limitsRepo = PersistenceContext.repositories().balanceLimits();

    @Override
    public void update(Observable o, Object arg) {
        verifyBalanceLimits((Booking) arg);
    }

    private boolean verifyBalanceLimits(Booking booking) {
        CafeteriaUser user = booking.getCafeteriauser();
        MecanographicNumber number = user.mecanographicNumber();
        BalanceLimits balanceLimits = limitsRepo.findUserBalanceLimits(number).get();
        double limits = balanceLimits.checkBalanceLimits();
        boolean verifyBalance = user.currentBalance().balanceIsWithinUserLimits(limits);

        return verifyBalance;
    }

}
