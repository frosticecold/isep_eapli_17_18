package eapli.ecafeteria.application.pos;

import eapli.ecafeteria.domain.meal.Execution;
import eapli.ecafeteria.persistence.ExecutionRepository;
import eapli.ecafeteria.persistence.PersistenceContext;

/**
 *
 * @author PedroEmanuelCoelho 1131485@isep.ipp.pt
 */
public class ListingService {
    
    public ListingService() {
        
    }
    
    /** List of Avaliable Meals **/
    public Iterable<Execution> AvaliableMeals() {
        
       ExecutionRepository repo = PersistenceContext.repositories().executions();
       
       return repo.findAll();
    }
}
