package eapli.ecafeteria.application.booking;

import eapli.ecafeteria.domain.booking.Booking;
import eapli.ecafeteria.domain.dishes.Dish;
import eapli.ecafeteria.domain.meal.Meal;
import eapli.ecafeteria.persistence.BookingRepository;
import eapli.ecafeteria.persistence.DishRepository;
import eapli.ecafeteria.persistence.MealRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.ecafeteria.persistence.RatingRepository;
import eapli.framework.application.Controller;
import eapli.framework.domain.Designation;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author JoaoMagalhaes
 */
public class ConsultDishRatingController implements Controller{
    
    private final DishRepository dishRepo = PersistenceContext.repositories().dishes();
    
    private final MealRepository mealRepo = PersistenceContext.repositories().meals();

    private final BookingRepository bookingRepo = PersistenceContext.repositories().booking();
    
    private final RatingRepository ratingRepo = PersistenceContext.repositories().rating();

    public List<Dish> getDishes(){
        return dishRepo.findListAll(); 
    }
    
    public List<Meal> getMealByDish(Dish dish) {
        return mealRepo.getMealByDish(dish);    
    }    

    public List<Booking> getAllBookingsFromMealThatAreServed(Meal m){
        return bookingRepo.getAllBookingsFromMealThatAreServed(m);
    }

    
}
