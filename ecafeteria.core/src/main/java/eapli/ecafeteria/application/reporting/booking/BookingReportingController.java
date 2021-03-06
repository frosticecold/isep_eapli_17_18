package eapli.ecafeteria.application.reporting.booking;


import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.domain.meal.Meal;
import eapli.ecafeteria.domain.meal.MealType;
import eapli.ecafeteria.persistence.BookingReportingRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.ecafeteria.reporting.booking.BookingPerOption;
import eapli.framework.application.Controller;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


public class BookingReportingController implements Controller {

    private final BookingReportingRepository repo = PersistenceContext.repositories().bookingReporting();

    /**
     * Booking reports sorted by date.
     *
     * in this case we are using a custom reporting DTO
     *
     * @return
     */
    public Iterable<BookingPerOption> reportDishesPerDate(Calendar c) {

        AuthorizationService.ensurePermissionOfLoggedInUser(ActionRight.MANAGE_KITCHEN);

        
        Iterable<BookingPerOption> it = repo.showReportByDay(c);
        
        
        return it;
    }
    
    /**
     * Booking reports sorted by Meal.
     *
     * in this case we are using a custom reporting DTO
     *
     * @return
     */
    public Iterable<BookingPerOption> reportDishesPerMeal(MealType mealType) {

        AuthorizationService.ensurePermissionOfLoggedInUser(ActionRight.MANAGE_KITCHEN);

        Iterable<BookingPerOption> it = repo.showReportByMeal(mealType);
        
        
        return it;
    }
    
    /**
     * Booking reports sorted by DishType.
     *
     * in this case we are using a custom reporting DTO
     *
     * @return
     */
    public Iterable<BookingPerOption> reportDishesPerDishType(String dishType) {

        AuthorizationService.ensurePermissionOfLoggedInUser(ActionRight.MANAGE_KITCHEN);

    
        Iterable<BookingPerOption> it = repo.showReportByDish(dishType);
        
        
        return it;
    }



}
