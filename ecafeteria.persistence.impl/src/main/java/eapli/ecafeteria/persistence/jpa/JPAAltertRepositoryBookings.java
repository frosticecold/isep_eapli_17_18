/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.booking.BookingState;
import eapli.ecafeteria.dto.AlertBookingDTO;
import eapli.ecafeteria.persistence.AlertRepositoryBookings;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.Optional;

/**
 *
 * @author DAVID
 */
public class JPAAltertRepositoryBookings extends CafeteriaJpaRepositoryBase<AlertBookingDTO, Long> implements AlertRepositoryBookings{

    @Override
    public AlertBookingDTO getNOBookings() {
        
          //usando a query que tinhamos ficava algo deste genero:
          
          //procurei uma maneira de conseguir guardar 3 elementos(meal, n_booking, n_planeadas)
          //e só encontrei isto:
          
//        Query q = entityManager().createQuery("SELECT m.id as MEAL_ID , COUNT(*) as N_OF_BOOKINGS , mpi.QUANTITY  FROM BOOKING b,  MENUPLANITEM mpi, MEAL m"
//                  + " WHERE b.meal_id = mpi.currentmeal_id"
//                  + " AND b.bookingstate = :bookingState"
//                  + " AND b.meal_id = m.id"
//                  + " GROUP BY m.id");

//        q.setParameter("bookingState", BookingState.BookingStates.NOT_SERVED);

//        esta query ia devolver uma List<Object[3]>(pelo que vi na net xd) e trabalhavamos com isso


//          OU--------------Outra proposta minha

//          que tu deste trash talk mas é uma opçao magnifica
//          usar tambem o repositorio das meals e fazer isto:
//          era recebido uma meal por parametro(depois de se ir buscar a lista de meals ao repo das meals)

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


        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

//    SELECT COUNT(*) FROM BOOKING b,  MENUPLANITEM mpi
//    WHERE b.meal_id = mpi.currentmeal_id
//    AND b.bookingstate = 'NOT_SERVED'

    
}
