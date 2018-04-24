/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.booking.Booking;
import eapli.ecafeteria.domain.booking.BookingState;
import eapli.ecafeteria.domain.booking.BookingState.BookingStates;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.domain.meal.MealType;
import eapli.ecafeteria.persistence.BookingReportingRepository;
import eapli.ecafeteria.reporting.booking.BookingPerOption;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.Query;

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
    public Iterable<BookingPerOption> showReportByDay(Calendar iDate) {
        
        final Query q = entityManager().
        createQuery("SELECT booking "
                        + "FROM Booking booking "
                        + "WHERE booking.date = :idate ", Booking.class);
        
       q.setParameter("idate", iDate);
       List<Booking> bookingList = q.getResultList();
       
       List<BookingPerOption> BPOList = new LinkedList<>();
       
       for( Booking b : bookingList){
        
           BPOList.add(BookingPerOption.fromBookingToDTO(b));
       
       }
       
       
       return BPOList;
    }

    @Override
    public Iterable<BookingPerOption> showReportByDish(String dish) {

        
        
         List<BookingPerOption> l = new ArrayList<>();

       
          final Query q = entityManager().
            createQuery("SELECT booking "
                        + "FROM Booking booking "
                        + "JOIN booking.meal m "
                        + "JOIN m.dish d "
                        + "JOIN d.dishType dt "
                        + "WHERE dt.acronym = :tp", Booking.class);
         /* 
        createQuery("SELECT booking "
                        + "FROM Booking booking "
                        + "JOIN booking.meal m "
                        + "JOIN m.dish d "
                        + "JOIN d.dishType dt "
                        + "WHERE dt.acronym = :tp");
          
          */
        
        q.setParameter("tp", dish);
        

       List<Booking> bookingList = q.getResultList();
       
       List<BookingPerOption> BPOList = new LinkedList<>();
       
       for( Booking b : bookingList){
        
           BPOList.add(BookingPerOption.fromBookingToDTO(b));
       
       }
       
       
       return BPOList;



       
       
    }

    @Override
    public Iterable<BookingPerOption> showReportByMeal(MealType meal) {
        
        
        System.out.println("REPORT BY MEAL\n");
 
        
        /*
            SELECT b.* FROM BOOKING b, Meal m
            WHERE b.meal_id = m.id
            AND m.mealtype = 0;
        */

 
         final Query q = entityManager().
               createQuery("SELECT b "
                        + "FROM Booking b "
                        + "JOIN b.meal m "
                        + "WHERE m.mealtype = :q", Booking.class);
         
        q.setParameter("q", meal);

       List<Booking> bookingList = q.getResultList();
       
       List<BookingPerOption> BPOList = new LinkedList<>();
       
       for( Booking b : bookingList){
        
           BPOList.add(BookingPerOption.fromBookingToDTO(b));
       
       }
       
       
       return BPOList;
    }

 

}
