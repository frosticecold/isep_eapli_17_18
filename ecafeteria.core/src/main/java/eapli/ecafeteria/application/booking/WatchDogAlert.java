/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.booking;

import eapli.ecafeteria.domain.booking.Booking;
import eapli.ecafeteria.domain.cafeteriauser.BalanceLimits;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.persistence.BalanceLimitsRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author Miguel Santos <1161386@isep.ipp.pt>
 */
public class WatchDogAlert extends Observable implements Observer {

    private final BalanceLimitsRepository limitsRepo = PersistenceContext.repositories().balanceLimits();

    @Override
    public void update(Observable o, Object arg) {
        boolean result = verifyBalanceLimits((Booking) arg);
        setChanged();
        notifyObservers(result);
    }

    private boolean verifyBalanceLimits(Booking booking) {
        CafeteriaUser user = booking.getCafeteriauser();
        BalanceLimits balanceLimits = limitsRepo.findUserBalanceLimits(user);
        double limits = balanceLimits.checkBalanceLimits();
        boolean verifyBalance = user.currentBalance().balanceIsWithinUserLimits(limits);

        return verifyBalance;
    }

}
