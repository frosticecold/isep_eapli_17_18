package eapli.ecafeteria.persistence;

import eapli.ecafeteria.domain.booking.Booking;
import eapli.ecafeteria.domain.booking.BookingState;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.domain.dishes.Dish;
import eapli.ecafeteria.domain.dishes.DishType;
import eapli.ecafeteria.domain.meal.MealType;
import eapli.ecafeteria.reporting.booking.BookingPerOption;
import eapli.framework.persistence.repositories.ReportingRepository;
import java.util.Calendar;
import java.util.List;


/**
 *
 * @author ruial
 */
public interface BookingReportingRepository extends ReportingRepository {

    public Iterable<Booking> findBookingByState(BookingState bookingState);

    public Booking findNextBooking(CafeteriaUser user);

    public List<Booking> findBookingsByCafeteriaUser(CafeteriaUser user, BookingState bookingState);
    
    public Iterable<BookingPerOption> showReportByDay(Calendar date);
     
    public Iterable<BookingPerOption> showReportByDish(String dish);
    
    public Iterable<BookingPerOption> showReportByMeal(MealType meal);
    
   
    
}
