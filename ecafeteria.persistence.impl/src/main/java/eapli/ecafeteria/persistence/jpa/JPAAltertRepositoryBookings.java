package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.booking.BookingState;
import eapli.ecafeteria.domain.meal.Meal;
import eapli.ecafeteria.domain.menuplan.Quantity;
import eapli.ecafeteria.dto.AlertBookingDTO;
import eapli.ecafeteria.persistence.AlertRepositoryBookings;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.NoResultException;
import javax.persistence.Query;


public class JPAAltertRepositoryBookings extends CafeteriaJpaRepositoryBase<AlertBookingDTO, Long> implements AlertRepositoryBookings {

    @Override
    public List<AlertBookingDTO> getNOBookings() {

        

        List<Meal> meals = getMeals();

        List<AlertBookingDTO> lista = new ArrayList<>();

        for (Meal meal : meals) {
            try {
               
                lista.add(new AlertBookingDTO( meal, nBookings(meal), nQuantity(meal)));  
            } catch (NoResultException e) {

               continue;
            }
        }

        return lista;
    }
    
    private List<Meal> getMeals(){
        
        final Query q = entityManager().createQuery("SELECT meal FROM Meal meal ", Meal.class);
        return q.getResultList();
    }
    
    private Long nBookings(Meal currentMeal){
        
        BookingState notServed = new BookingState();
        notServed.changeToNotServed();
        
        
        
        final Query q2 = entityManager().
                        createQuery("SELECT COUNT(booking) FROM Booking booking"
                                + " WHERE booking.meal = :meal"
                                + " AND booking.bookingState = :bookingState", Long.class)
                        .setParameter("bookingState", notServed)
                        .setParameter("meal", currentMeal);
        
        
        
        
   
        return (Long) q2.getSingleResult();
    }
    
    private Long nQuantity(Meal currentMeal){
        
        final Query q3 = entityManager().
                        createQuery("SELECT mpi.quantityNumber FROM MenuPlanItem mpi"
                                + " WHERE mpi.currentMeal = :meal", Quantity.class)
                        .setParameter("meal", currentMeal);
        
        
        
        return ((long) ((Quantity) q3.getSingleResult()).getQuantity());
        
    }
    
    

}