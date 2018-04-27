package eapli.ecafeteria.application.pos;

import eapli.ecafeteria.domain.dishes.DishType;
import eapli.ecafeteria.domain.meal.MealType;
import eapli.ecafeteria.domain.pos.AvailableMealsStatistics;
import eapli.ecafeteria.persistence.BookingRepository;
import eapli.ecafeteria.persistence.DishTypeRepository;
import eapli.ecafeteria.persistence.ExecutionRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author PedroEmanuelCoelho 1131485@isep.ipp.pt
 */
public class ListAvailableMealsService {

    private final ExecutionRepository repo = PersistenceContext.repositories().executions();
    private final DishTypeRepository dishRepo = PersistenceContext.repositories().dishTypes();
    private final BookingRepository bookingRepo = PersistenceContext.repositories().booking();

    /**
     * List of Avaliable Meals
     *
     * @param cal
     * @param mealtype
     * @return *
     */
    public AvailableMealsStatistics calcStatistics(Calendar cal, MealType mealtype) {
        Iterable<DishType> activeDishTypes = dishRepo.activeDishTypes();
        AvailableMealsStatistics ams = new AvailableMealsStatistics(cal, mealtype);
        for (DishType dt : activeDishTypes) {
            Long maxNumberOfServings = repo.getMaxNumberOfServings(dt, cal, mealtype);
            ams.addDishTypeQuantity(dt, maxNumberOfServings);
            Long countReservedMealsByDishType = bookingRepo.countReservedMealsByDishType(cal, dt, mealtype);
            ams.addDishTypeReservedQuantity(dt, countReservedMealsByDishType);
            Long servedMealsQuantity = bookingRepo.getNumberOfDeliveredMealsByDishTypeByDayAndMealType(cal, mealtype, dt);
            ams.addDishTypeServedQuantity(dt, servedMealsQuantity);
        }
        return ams;
    }

    public Map<DishType, Long> calcDeliveredStatistics(Calendar cal, MealType mealtype) {
        Map<DishType, Long> map = new LinkedHashMap<>();
        Iterable<DishType> activeDishTypes = dishRepo.activeDishTypes();

        for (DishType dt : activeDishTypes) {
            Long delivered = bookingRepo.getNumberOfDeliveredMealsByDishTypeByDayAndMealType(cal, mealtype, dt);
            map.put(dt, delivered);
        }
        return map;
    }
    
    public Long calcDeliveredTotal(Calendar cal, MealType mealtype) {
        Long total;

        total = bookingRepo.getNumberOfDeliveredMealsByDayAndMealType(cal, mealtype);

        return total;
    }
}
