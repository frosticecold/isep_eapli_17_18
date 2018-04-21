/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
package eapli.ecafeteria.application.cafeteriauser;

import java.util.Optional;

import eapli.ecafeteria.domain.authz.Username;
import eapli.ecafeteria.domain.cafeteriauser.Balance;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.domain.cafeteriauser.MecanographicNumber;
import eapli.ecafeteria.persistence.CafeteriaUserRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.ecafeteria.persistence.TransactionRepository;
import eapli.framework.domain.money.Money;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mcn
 */
public class CafeteriaUserService {

    private final CafeteriaUserRepository repo = PersistenceContext.repositories().cafeteriaUsers();
    
    private final TransactionRepository trepo= PersistenceContext.repositories().transactioRepository();


    public Optional<CafeteriaUser> findCafeteriaUserByMecNumber(String mecNumber) {
        return this.repo.findByMecanographicNumber(new MecanographicNumber(mecNumber));
    }

    public Optional<CafeteriaUser> findCafeteriaUserByUsername(Username user) {
        return this.repo.findByUsername(user);
    }

    public CafeteriaUser save(CafeteriaUser user) {
        CafeteriaUser updateUser = null;
        try {
            updateUser = this.repo.save(user);
        } catch (DataConcurrencyException ex) {
            Logger.getLogger(CafeteriaUserService.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DataIntegrityViolationException ex) {
            Logger.getLogger(CafeteriaUserService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return updateUser;
    }

    public boolean hasEnoughtMoney(CafeteriaUser user, Money money) {
        Balance userBalance = trepo.getBalanceOfUser(user.mecanographicNumber());
        if (money.lessThanOrEqual(userBalance.currentBalance())) {
                System.out.println("USER HAS ENOUGH MONEY");
            return true;
        } else {
            return false;
        }

    }

}
