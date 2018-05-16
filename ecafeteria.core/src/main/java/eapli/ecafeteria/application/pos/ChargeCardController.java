/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.pos;

import eapli.ecafeteria.application.cafeteriauser.CafeteriaUserService;
import eapli.ecafeteria.domain.CreditTransaction.Transaction;
import eapli.ecafeteria.domain.CreditTransaction.TransactionType;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.persistence.CafeteriaUserRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.ecafeteria.persistence.TransactionRepository;
import eapli.framework.application.Controller;
import eapli.framework.domain.money.Money;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.persistence.repositories.TransactionalContext;
import java.util.Currency;
import java.util.Optional;

/**
 *
 * @author MarioDias
 */
public class ChargeCardController implements Controller {

    private final CafeteriaUserService service = new CafeteriaUserService();
    private final TransactionalContext TxCtx = PersistenceContext.repositories().buildTransactionalContext();
    private final TransactionRepository tr = PersistenceContext.repositories().transactioRepository(TxCtx);
    private final CafeteriaUserRepository cafer = PersistenceContext.repositories().cafeteriaUsers(TxCtx);
    private Transaction t;
    private CafeteriaUser user;

    public String findCafeteriaUserByMecanographicNumber(String mecanographicNumber) {
        Optional<CafeteriaUser> user = service.findCafeteriaUserByMecNumber(mecanographicNumber);
        this.user = user.get();
        return this.user.cafeteriaUserNameAndCurrentBalance();
    }

    public boolean chargeCafeteriaUserCard(double tempCredits) {
        Money credits = new Money(tempCredits, Currency.getInstance("EUR"));
        this.t = new Transaction(this.user, TransactionType.RECHARGE, credits);
        if (!this.user.addCredits(credits)) {
            throw new IllegalArgumentException("Error adding credits");
        }
        return true;
    }

    public String saveCafeteriaUser() {
        CafeteriaUser tempuser = service.save(this.user);
        if (tempuser == null) {
            throw new IllegalStateException("Error Updating cafeteria user");
        }
        return tempuser.cafeteriaUserNameAndCurrentBalance();
    }

    public void saveTransaction() throws DataConcurrencyException, DataIntegrityViolationException {
        this.TxCtx.beginTransaction();

        if (this.tr.save(this.t) == null) {
            throw new IllegalArgumentException("Error Saving transaction");
        }
        
        TxCtx.commit();
    }

}
