/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.booking.Booking;
import eapli.ecafeteria.domain.booking.BookingState;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.domain.dishes.Dish;
import eapli.ecafeteria.domain.meal.Meal;
import eapli.ecafeteria.persistence.BookingReportingRepository;
import eapli.ecafeteria.reporting.booking.BookingPerOption;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author Rui Almeida <1160818>
 */
public class JpaBookingReportingRepository extends CafeteriaJpaRepositoryBase implements BookingReportingRepository {

    /**
     * Finds a list of bookings given a state
     *
     * @param bookingState
     * @author Rui Almeida <1160818>
     * @return
     */
    @Override
    public Iterable<Booking> findBookingByState(BookingState bookingState) {
        return entityManager().createQuery("SELECT booking "
                        + "FROM Booking booking "
                        + "WHERE booking.bookingState = :bookingState")
                .setParameter("bookingState", bookingState)
                .getResultList();
    }

    /**
     * Finds the users next booking in the booked state
     * @param user
     * @author Joao Rocha 1161838
     * @return 
     */
    @Override
    public Booking findNextBooking(CafeteriaUser user) {
        Booking nextBooking = null;
        BookingState state = new BookingState();

        for (Booking booking : findBookingsByCafeteriaUser(user, state)) {

            long bookingDate1 = booking.getMeal().getMealDate().getTimeInMillis(); 
            long bookingDate2;
            
            if(nextBooking == null){
                bookingDate2 = Long.MAX_VALUE;
            }
            
            else{
                bookingDate2 = nextBooking.getMeal().getMealDate().getTimeInMillis();
            }
            
            if (bookingDate1 < bookingDate2) {
                nextBooking = booking;
            }
        }

        return nextBooking;
    }

    /**
     * Find booking by cafeteria user that are in a specific state
     *
     * @param user user of the cafeteria
     * @param bookingState booking state
     * @author David Camelo <1161294@isep.ipp.pt>
     * 
     * @return list with bookings
     */
    @Override
    public List<Booking> findBookingsByCafeteriaUser(CafeteriaUser user, 
            BookingState bookingState) {
        Query query = entityManager().createQuery("SELECT booking "
                        + "FROM Booking booking "
                        + "WHERE booking.bookingState = :bookingState "
                        + "AND booking.cafeteriaUser = :cafeteriaUser");
        
        query.setParameter("bookingState", bookingState);
        query.setParameter("cafeteriaUser", user);
        
        return query.getResultList();
    }

    @Override
    public Iterable<BookingPerOption> showReportByDay(Date date) {
             
        Date d = new Date(0);
        
        System.out.println("DATE DO SQL: " + date + "\n");
        
        final Query q = entityManager().
        createQuery("SELECT booking "
                        + "FROM Booking booking "
                        + "WHERE booking.date = :date ");
        
       q.setParameter("date", date);
       
        List<BookingPerOption> l = new ArrayList<>();
        
        
        System.out.println("RESULTADO QUERY: " + q.getResultList() + "\n");
        
        for (Iterator it = q.getResultList().iterator(); it.hasNext();) {
            System.out.println(it.next());
            Object b =  it.next();
            
            if( b instanceof Booking){
                Booking booking = (Booking) b;
                
                BookingPerOption b1 = new BookingPerOption(booking.getMeal().toString(), date, "mealDishName", "userName");
                l.add(b1);
                
            }
                
        }
    
   

        return l;

          /* ^QUERY */
          
          
//          List<BookingPerOption> l = new ArrayList<>();
//          l.add(new BookingPerOption("mealType", new Date(), "mealDishName", "userName"));
//          l.add(new BookingPerOption("mealType2", new Date(), "mealDishName4", "userName12"));
//          l.add(new BookingPerOption("mealType3", new Date(), "mealDishName33", "userName4"));
          
          
          
       //   return l;
    }

    @Override
    public Iterable<BookingPerOption> showReportByDish(Dish dish) {
        
          final Query q = entityManager().
        createQuery("SELECT booking "
                        + "FROM Booking booking, Meal meal "
                        + "WHERE booking.meal_id = meal.id"
                        + "AND dishid = :dish");
        
       q.setParameter("dish", dish.id());
       
       
       return q.getResultList();
    }

    @Override
    public Iterable<BookingPerOption> showReportByMeal(Meal meal) {
        
          final Query q = entityManager().
        createQuery("SELECT booking "
                        + "FROM Booking booking "
                        + "WHERE booking.meal_id = :meal ");
        
          
       q.setParameter("meal", meal);
       
       
       return q.getResultList();
    }

 

}
