package eapli.ecafeteria.persistence;

import eapli.ecafeteria.domain.booking.Booking;
import eapli.ecafeteria.domain.booking.BookingState;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
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
    
         /**
     * Report booking by input date.
     *
     * @param Calendar iDate - Pretended date
     * @author David Blanquett  <1161018@isep.ipp.pt>
     * 
     * @return list with bookingsDTO
     */
    public Iterable<BookingPerOption> showReportByDay(Calendar date);
    
     /**
     * Report booking by dishType.
     *
     * @param String dish - Dish type by user input
     * @author David Blanquett  <1161018@isep.ipp.pt>
     * 
     * @return list with bookingsDTO
     */ 
    public Iterable<BookingPerOption> showReportByDish(String dish);
    
    /**
     * Report booking by dishType.
     *
     * @param MealType meal - Mealtype by user input
     * @author David Blanquett  <1161018@isep.ipp.pt>
     * 
     * @return list with bookingsDTO
     */
    public Iterable<BookingPerOption> showReportByMeal(MealType meal);
    
   
    
}
