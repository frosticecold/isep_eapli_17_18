package eapli.ecafeteria.bootstrapers;

import eapli.ecafeteria.domain.authz.SystemUser;
import eapli.ecafeteria.domain.authz.Username;
import eapli.ecafeteria.domain.pos.DeliveryMealSession;
import eapli.ecafeteria.domain.pos.POS;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.actions.Action;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.persistence.repositories.TransactionalContext;
import java.util.logging.Logger;

/**
 *
 * @author PedroEmanuelCoelho 1131485@isep.ipp.pt
 */
public class DeliveryMealSessionBootstrapper implements Action {

    @Override
    public boolean execute() {

        TransactionalContext autoTx = PersistenceContext.repositories().buildTransactionalContext();

        boolean flag = false;

        boolean flag2 = false;

        Username username = new Username("poweruser");

        SystemUser cashier1 = PersistenceContext.repositories().users().findOne(username).get();

        POS pos = new POS(cashier1);
        
        pos.openPOS();

        DeliveryMealSession s = pos.openSession();
        
        s.closeSession();

        try {
            PersistenceContext.repositories().deliveryMealRepository().save(s);
        } catch (final DataIntegrityViolationException | DataConcurrencyException e) {
            // ignoring exception. assuming it is just a primary key violation
            // due to the tentative of inserting a duplicated user
            Logger.getLogger(ECafeteriaBootstrapper.class.getSimpleName())
                    .info("EAPLI-DI001: bootstrapping existing record");
        }

        flag = this.registerPOS(pos, autoTx);

        return flag;
    }

    private boolean registerPOS(POS p, TransactionalContext autoTx) {

        boolean f = false;
        try {

            PersistenceContext.repositories().autoTxPOSRepository(autoTx).saveTransaction(p);

            f = true;

        } catch (final DataIntegrityViolationException | DataConcurrencyException e) {
            // ignoring exception. assuming it is just a primary key violation
            // due to the tentative of inserting a duplicated user
            Logger.getLogger(ECafeteriaBootstrapper.class.getSimpleName())
                    .info("EAPLI-DI001: bootstrapping existing record");
        }
        return f;
    }

}
