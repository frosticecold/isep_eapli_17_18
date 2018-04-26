/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.booking.Booking;
import eapli.ecafeteria.domain.booking.BookingState;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.domain.meal.MealType;
import eapli.ecafeteria.persistence.BookingReportingRepository;
import eapli.ecafeteria.reporting.booking.BookingPerOption;
import java.util.ArrayList;
import java.util.Calendar;
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

     /**
     * Report booking by input date.
     *
     * @param Calendar iDate - Pretended date
     * @author David Blanquett  <1161018@isep.ipp.pt>
     * 
     * @return list with bookingsDTO
     */
    @Override
    public Iterable<BookingPerOption> showReportByDay(Calendar iDate) {
        
        try{
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
               
        }catch( Exception e){
           

            return new ArrayList<BookingPerOption>();
       }
    }

    /**
     * Report booking by dishType.
     *
     * @param String dish - Dish type by user input
     * @author David Blanquett  <1161018@isep.ipp.pt>
     * 
     * @return list with bookingsDTO
     */
    @Override
    public Iterable<BookingPerOption> showReportByDish(String dish) {

       try{
           
           
            final Query q = entityManager().
              createQuery("SELECT booking "
                          + "FROM Booking booking "
                          + "JOIN booking.meal m "
                          + "JOIN m.dish d "
                          + "JOIN d.dishType dt "
                          + "WHERE dt.acronym = :tp", Booking.class);


                      q.setParameter("tp", dish);


                      List<Booking> bookingList = q.getResultList();

                      List<BookingPerOption> BPOList = new LinkedList<>();

                      for( Booking b : bookingList){ // TRANSFORM BOOKING OBJECT TO DTO

                          BPOList.add(BookingPerOption.fromBookingToDTO(b));

                      }


                      return BPOList;
          
       }catch( Exception e){
           

            return new ArrayList<BookingPerOption>();
       }
        
    
    }

    /**
     * Report booking by dishType.
     *
     * @param MealType meal - Mealtype by user input
     * @author David Blanquett  <1161018@isep.ipp.pt>
     * 
     * @return list with bookingsDTO
     */
    @Override
    public Iterable<BookingPerOption> showReportByMeal(MealType meal) {

            /*
                SELECT b.* FROM BOOKING b, Meal m
                WHERE b.meal_id = m.id
                AND m.mealtype = meal;
            */

        try{
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


       }catch( Exception e){


                    return new ArrayList<BookingPerOption>();
       }

 
  }

}
