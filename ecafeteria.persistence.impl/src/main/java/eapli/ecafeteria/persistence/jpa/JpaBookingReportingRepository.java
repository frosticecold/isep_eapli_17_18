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
import eapli.ecafeteria.domain.dishes.DishType;
import eapli.ecafeteria.domain.meal.Meal;
import eapli.ecafeteria.domain.meal.MealType;
import eapli.ecafeteria.persistence.BookingReportingRepository;
import eapli.ecafeteria.reporting.booking.BookingPerOption;
import eapli.ecafeteria.reporting.dishes.DishesPerDishType;
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
    public Iterable<BookingPerOption> showReportByDay(Calendar date) {
             
        Calendar c = new GregorianCalendar(2018,04,19);
        
        
        System.out.println("DATE DO SQL: " + date + "\n");
        
        final Query q = entityManager().
        createQuery("SELECT booking "
                        + "FROM Booking booking "
                        + "WHERE booking.date = :date ");
        
       q.setParameter("date", c);
       
        List<BookingPerOption> l = new ArrayList<>();
        
        
        System.out.println("RESULTADO QUERY: " + q.getResultList() + "\n");
        
        
        
        
        for (Iterator it = q.getResultList().iterator(); it.hasNext();) {
            System.out.println(it.next());
            Object b =  it.next();
            
            if( b instanceof Booking){
                Booking booking = (Booking) b;
                
                BookingPerOption b1 = new BookingPerOption(booking.getMeal().toString(), null, "mealDishName", "userName");
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
    public Iterable<BookingPerOption> showReportByDish(String dish) {
        
         List<BookingPerOption> l = new ArrayList<>();
        /*
        
            SELECT b.* FROM BOOKING b, Meal m, Dish d, Dishtype dt
            WHERE b.meal_id = m.id
            AND m.dishid = d.name AND d.dishtype_pk = dt.pk AND dt.acronym = 'vegie';
        
        */
        System.out.println("OLA: " + dish);
       
          final Query q = entityManager().
        createQuery("SELECT booking.idBooking "
                        + "FROM Booking booking, Meal m, Dish d, DishType dt "
                        + "WHERE booking.meal = m "
                        + "AND m.dish = d "
                        + "AND d.dishType = dt "
                        + "AND dt.acronym = :tp");
         /* 
        createQuery("SELECT booking "
                        + "FROM Booking booking "
                        + "JOIN booking.meal m "
                        + "JOIN m.dish d "
                        + "JOIN d.dishType dt "
                        + "WHERE dt.acronym = :tp");
          
          */
        
        q.setParameter("tp", dish);
        
        System.out.println("RESULTADO::: \n" + q.getResultList() + "\n");
        
        
            for (Iterator it = q.getResultList().iterator(); it.hasNext();) {
            System.out.println(it.next());
            Object b =  it.next();
            
            if( b instanceof Booking){
                Booking booking = (Booking) b;
                
                BookingPerOption b1 = new BookingPerOption(booking.getMeal().toString(), null, "mealDishName", "userName");
                l.add(b1);
                
            }
                
        }
    
   
       
       
       return q.getResultList();
    }

    @Override
    public Iterable<BookingPerOption> showReportByMeal(MealType meal) {
         List<BookingPerOption> l = new ArrayList<>();
        System.out.println("ANTES DA QERYYY!");
        
        /*
            SELECT b.* FROM BOOKING b, Meal m
            WHERE b.meal_id = m.id
            AND m.mealtype = 0;
        */

 
         final Query q = entityManager().
               createQuery("SELECT  b.idBooking "
                        + "FROM Booking b "
                        + "JOIN b.meal m "
                        + "WHERE m.mealtype > -1");
         


      // q.setParameter("mealt", 0);
       
       
        System.out.println("\n\nQUERY RESULT:" + q.getResultList() + "\n");
        
            for (Iterator it = q.getResultList().iterator(); it.hasNext();) {
            System.out.println(it.next());
            Object b =  it.next();
            
            if( b instanceof Booking){
                Booking booking = (Booking) b;
                
                BookingPerOption b1 = new BookingPerOption(booking.getMeal().toString(), null, "mealDishName", "userName");
                l.add(b1);
                
            }
                
        }
    
   
       
       return l;
    }

 

}
