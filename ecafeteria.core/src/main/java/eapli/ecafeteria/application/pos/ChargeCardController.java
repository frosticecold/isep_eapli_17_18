/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.pos;

import eapli.ecafeteria.application.cafeteriauser.CafeteriaUserService;
import eapli.ecafeteria.domain.CreditTransaction.CreditRecharge;
import eapli.ecafeteria.domain.CreditTransaction.Transaction;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.ecafeteria.persistence.TransactionRepository;
import eapli.framework.application.Controller;
import eapli.framework.domain.money.Money;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author MarioDias
 */
public class ChargeCardController implements Controller {

    private final CafeteriaUserService service = new CafeteriaUserService();
    private final TransactionRepository tr = PersistenceContext.repositories().transactioRepository();
    private Transaction t;
    private CafeteriaUser user;

    public CafeteriaUser findCafeteriaUserByMecanographicNumber(String mecanographicNumber) {
        Optional<CafeteriaUser> user = service.findCafeteriaUserByMecNumber(mecanographicNumber);
        if (user.isPresent()) {
            this.user = user.get();
            return user.get();
        }
        return null;
    }

    public boolean chargeCafeteriaUserCard(CafeteriaUser user, Money creditToCharge) {
        this.t = new CreditRecharge(user, creditToCharge);
        saveTransaction(t);
        return user.addCredits(creditToCharge);
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
