package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.booking.BookingState;
import eapli.ecafeteria.domain.booking.BookingState.BookingStates;
import eapli.ecafeteria.domain.meal.Meal;
import eapli.ecafeteria.dto.AlertBookingDTO;
import eapli.ecafeteria.persistence.AlertRepositoryBookings;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author DAVID
 */
public class JPAAltertRepositoryBookings extends CafeteriaJpaRepositoryBase<AlertBookingDTO, Long> implements AlertRepositoryBookings{

    @Override
    public List<AlertBookingDTO> getNOBookings() {
        
            BookingState notServed = new BookingState();
            notServed.changeToNotServed();

            Query q = entityManager().createQuery("SELECT meal FROM Meal meal ", Meal.class);

            List<AlertBookingDTO> lista = new ArrayList<>();
            for(Object meal: q.getResultList()){
                
                
                Query q2 = entityManager().
                createQuery("SELECT COUNT(booking) FROM Booking booking"
                + " WHERE booking.meal = :meal"
                + " AND booking.bookingState = :bookingState", Long.class)
                .setParameter("bookingState", notServed)
                .setParameter("meal", (Meal)meal);
                
//                Query q2 = entityManager().
//                createQuery("SELECT COUNT(*) FROM Booking booking"
//                + " JOIN booking.meal m"
//                + " WHERE booking.bookingState.actualBookingState = :bookingState");   
             
                //q.setParameter("bookingState", notServed);
                
                
                
                Query q3 = entityManager().
                createQuery("SELECT mpi.quantityNumber FROM MenuPlanItem mpi"
                + " WHERE mpi.currentMeal = :meal")
                .setParameter("meal", meal);
                
                
                lista.add(new AlertBookingDTO((Meal)meal, (long)q2.getSingleResult(), (long)q3.getSingleResult()));
            }
            
            
            return lista;
//          isto returnava o número de bookings da meal especifica
//                Query q = entityManager().
//                createQuery("SELECT COUNT(*) as N_OF_BOOKINGS FROM Booking booking "
//                + "WHERE b.meal_id=:meal.id "
//                + " AND b.bookingstate = :bookingState");
//
//                q.setParameter("bookingState", BookingState.BookingStates.NOT_SERVED);
//                q.setParameter("meal", meal);

//          isto returnava o número planeado para cada prato
//                Query q = entityManager().
//                createQuery("SELECT mpi.QUANTITY FROM MENUPLANITEM mpi "
//                + "WHERE mpi.meal_id=:meal.id");
//
//                q.setParameter("meal", meal);

          //usando a query que tinhamos ficava algo deste genero:
          
          //procurei uma maneira de conseguir guardar 3 elementos(meal, n_booking, n_planeadas)
          //e só encontrei isto:
          
//          
//        Query q = entityManager().createQuery("SELECT m.id , COUNT(*) , mpi.QUANTITY FROM BOOKING b,  MENUPLANITEM mpi, MEAL m "
//                  + " WHERE b.meal_id = mpi.currentmeal_id"
//                  + " AND b.bookingstate = :bookingState"
//                  + " AND b.meal_id = m.id"
//                  + " GROUP BY m.id");
//        
//
//
//        q.setParameter("bookingState", BookingState.BookingStates.NOT_SERVED);
//        
//        List<AlertBookingDTO> bookingDTOList = new ArrayList<>();
//        
//        for( Object array : q.getResultList()){
//            Object o = array[0];
//            
//        }
//        
//        }
        

//        esta query ia devolver uma List<Object[3]>(pelo que vi na net xd) e trabalhavamos com isso


//          OU--------------Outra proposta minha

//          que tu deste trash talk mas é uma opçao magnifica
//          usar tambem o repositorio das meals e fazer isto:
//          era recebido uma meal por parametro(depois de se ir buscar a lista de meals ao repo das meals)



    }

//    SELECT COUNT(*) FROM BOOKING b,  MENUPLANITEM mpi
//    WHERE b.meal_id = mpi.currentmeal_id
//    AND b.bookingstate = 'NOT_SERVED'

    
}
