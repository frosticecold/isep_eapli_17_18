package eapli.ecafeteria.bootstrapers;

import eapli.ecafeteria.domain.authz.RoleType;
import eapli.ecafeteria.domain.authz.SystemUser;
import eapli.ecafeteria.domain.authz.SystemUserBuilder;
import eapli.ecafeteria.domain.pos.POS;
import eapli.ecafeteria.persistence.POSRepository;
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
        
        final POSRepository posRepo = PersistenceContext.repositories().posRepository();
        
        final SystemUserBuilder builder = new SystemUserBuilder();
        
        builder.withRole(RoleType.CASHIER);
        
        final SystemUser cashier1 = builder.build();
        
        final POS pos1 = new POS(cashier1);
        
        
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