package eapli.ecafeteria.bootstrapers;

import eapli.ecafeteria.domain.authz.SystemUser;
import eapli.ecafeteria.domain.authz.Username;
import eapli.ecafeteria.domain.pos.POS;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.actions.Action;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.logging.Logger;

/**
*
*   @author PedroEmanuelCoelho 1131485@isep.ipp.pt
*/

public class POSBootstrapper implements Action {

    @Override
    public boolean execute() {
       
       Username username = new Username("cashier");
        
       SystemUser cashier1 = PersistenceContext.repositories().users().findOne(username).get();
               
       POS pos = new POS(cashier1);

       this.register(pos);
           
       return true;
    }
    
    /**
     * private method to register on persistence
     */
    private void register(POS pos) {
        
            try {
                PersistenceContext.repositories().posRepository().save(pos);
            } catch (final DataIntegrityViolationException | DataConcurrencyException e) {
            // ignoring exception. assuming it is just a primary key violation
            // due to the tentative of inserting a duplicated user
            Logger.getLogger(ECafeteriaBootstrapper.class.getSimpleName())
                    .info("EAPLI-DI001: bootstrapping existing record");
        }
    }
}