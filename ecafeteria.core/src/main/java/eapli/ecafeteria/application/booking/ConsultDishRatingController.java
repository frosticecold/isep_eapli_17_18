package eapli.ecafeteria.application.booking;

import eapli.ecafeteria.domain.booking.Booking;
import eapli.ecafeteria.domain.booking.Rating;
import eapli.ecafeteria.domain.dishes.Dish;
import eapli.ecafeteria.domain.meal.Meal;
import eapli.ecafeteria.persistence.BookingRepository;
import eapli.ecafeteria.persistence.DishRepository;
import eapli.ecafeteria.persistence.MealRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.ecafeteria.persistence.RatingRepository;
import eapli.framework.application.Controller;
import java.util.List;

/**
 *
 * @author JoaoMagalhaes
 */
public class ConsultDishRatingController implements Controller{
    
    private final DishRepository dishRepo = PersistenceContext.repositories().dishes();
    
    private final MealRepository mealRepo = PersistenceContext.repositories().meals();

    private final BookingRepository bookingRepo = PersistenceContext.repositories().booking();
    
    private final RatingRepository ratingRepo = PersistenceContext.repositories().rating();

    /**
     * Gets all Dishes
     * 
     * @return 
     */
    public List<Dish> getDishes(){
        return dishRepo.findListAll(); 
    }
    
    /**
     * Gets a list of Meals from a especific Dish passed as a parameter
     * 
     * @param dish
     * @return 
     */
    public List<Meal> getMealByDish(Dish dish) {
        return mealRepo.getMealByDish(dish);    
    }    

    /**
     * Gets a list of Bookings from a especific Meal passed as a parameter
     * 
     * @param m
     * @return 
     */
    public List<Booking> getAllBookingsFromMealThatAreServed(Meal m){
        return bookingRepo.getAllBookingsFromMealThatAreServed(m);
    }

    /**
     * Gets a rating from a especific Booking passed as a parameter
     * @param b
     * @return 
     */
    public Rating getRatingFromBooking(Booking b) {
        return ratingRepo.getRatingFromBooking(b);
    }
    
    /**
     * Adds a rating passed as a parameter to a list passed as a parameter
     * 
     * @param r
     * @param totalRatingList 
     */
    public void addRatingToList(Rating r, List<Rating> totalRatingList) {
        totalRatingList.add(r);
    }

    /**
     * Calculates the average of ratings
     * 
     * @param totalRatingList
     * @return 
     */
    public double calculateMediaMeal(List<Rating> totalRatingList) {
        double sum=0;
        for(Rating r:totalRatingList){
            sum+=r.getRating();
        }
        return sum/totalRatingList.size();
    }

}
