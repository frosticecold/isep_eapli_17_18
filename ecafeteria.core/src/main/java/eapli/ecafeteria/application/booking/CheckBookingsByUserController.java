/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.booking;

import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.domain.booking.Booking;
import eapli.ecafeteria.domain.booking.BookingState;
import static eapli.ecafeteria.domain.booking.BookingState.BookingStates.BOOKED;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.persistence.BookingReportingRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.ecafeteria.persistence.RepositoryFactory;
import eapli.framework.application.Controller;
import java.util.List;

/**
 *
 * @author Rafael Teixeira, 1160911
 */
public class CheckBookingsByUserController implements Controller
{

    private BookingReportingRepository bookingRepository;
    private CafeteriaUser user;
    private RepositoryFactory repository;
    private BookingState state;

    public CheckBookingsByUserController()
    {
        this.user = repository.cafeteriaUsers().findByUsername( AuthorizationService.session().authenticatedUser().username()).get();
        this.bookingRepository = repository.bookingReporting();
        repository = PersistenceContext.repositories();
    }

    public List<Booking> findBookingsByCafeteriaUser(CafeteriaUser user, BookingState.BookingStates bookingState)
    {
        return bookingRepository.findBookingsByCafeteriaUser(user, state);

    }

}
