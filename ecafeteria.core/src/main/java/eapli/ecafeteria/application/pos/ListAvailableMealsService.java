package eapli.ecafeteria.application.pos;

import eapli.ecafeteria.domain.meal.Execution;
import eapli.ecafeteria.domain.meal.MealType;
import eapli.ecafeteria.persistence.ExecutionRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import java.util.Calendar;

/**
 *
 * @author PedroEmanuelCoelho 1131485@isep.ipp.pt
 */
public class ListAvailableMealsService {
    
    ExecutionRepository repo = PersistenceContext.repositories().executions();
    
    /** List of Avaliable Meals
     * @param cal
     * @return  **/
    public Iterable<Execution> findExecutionsPerDate(Calendar cal, MealType mealtype) {
       return repo.findMealExecutionByDate(cal, mealtype);
    }
}
