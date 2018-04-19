package eapli.ecafeteria.application.pos;

import eapli.ecafeteria.persistence.DishReportingRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.ecafeteria.reporting.dishes.DishesPerDishType;
import eapli.framework.application.Controller;

/**
 *
 * @author PedroEmanuelCoelho 1131485@isep.ipp.pt
 */
public class ListingService {
    
    public ListingService() {
        
    }
    
    /** List Avaliable Meals
     * @return  **/
    public Iterable<DishesPerDishType> showDishesPerDishType() {
        
        DishReportingRepository repo = PersistenceContext.repositories().dishReporting();
        
        return repo.dishesPerDishType();
    }
}
