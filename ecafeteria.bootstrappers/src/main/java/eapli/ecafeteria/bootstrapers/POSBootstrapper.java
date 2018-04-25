package eapli.ecafeteria.bootstrapers;

import eapli.ecafeteria.persistence.POSRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.actions.Action;

/**
*
*   @author PedroEmanuelCoelho 1131485@isep.ipp.pt
*/

public class POSBootstrapper implements Action {

    @Override
    public boolean execute() {
        final POSRepository posRepo = PersistenceContext.repositories().posRepository();
        
        return true;
    }
}