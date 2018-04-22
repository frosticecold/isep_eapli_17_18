/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.CreditTransaction;

import eapli.ecafeteria.application.cafeteriauser.CafeteriaUserService;
import eapli.ecafeteria.application.pos.ChargeCardController;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.ecafeteria.persistence.TransactionRepository;
import eapli.framework.domain.money.Money;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Beatriz Ferreira <1160701@isep.ipp.pt>
 */
public class DebitBooking extends Transaction {

    private final CafeteriaUserService service = new CafeteriaUserService();
    private final TransactionRepository tr = PersistenceContext.repositories().transactioRepository();
    private Transaction t;
    private CafeteriaUser user;

    /**
     * This class is inherited from the generic class Transaction, eith the
     * method movement
     *
     * @param user the user that will lost credits to its current balance
     * @param credits The amount of credits that will deleted from the user
     * account
     */
    public DebitBooking(CafeteriaUser user, Money credits) {
        super(user, credits);
    }

    public CafeteriaUser findCafeteriaUserByMecanographicNumber(String mecanographicNumber) {
        Optional<CafeteriaUser> user = service.findCafeteriaUserByMecNumber(mecanographicNumber);
        if (user.isPresent()) {
            this.user = user.get();
            return user.get();
        }
        return null;
    }

    public boolean movement(CafeteriaUser user, Money credits) {
        this.t = new DebitBooking(user, credits);
        saveTransaction(t);
        if (user.removeCredits(credits)) {
            saveCafeteriaUser(user);
            return true;

        }
        return false;
    }

    public CafeteriaUser saveCafeteriaUser(CafeteriaUser user) {
        return service.save(user);
    }

    private void saveTransaction(Transaction t) {
        try {
            this.tr.save(this.t);
        } catch (DataConcurrencyException ex) {
            Logger.getLogger(ChargeCardController.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DataIntegrityViolationException ex) {
            Logger.getLogger(ChargeCardController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }



}
