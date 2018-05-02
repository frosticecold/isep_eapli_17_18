/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.booking;

import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.domain.booking.Booking;
import eapli.ecafeteria.domain.booking.BookingState;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.persistence.BookingReportingRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.ecafeteria.persistence.RepositoryFactory;
import eapli.framework.application.Controller;
import java.util.List;
import javax.persistence.NoResultException;

/**
 *
 * @author Rafael Teixeira, 1160911
 */
public class CheckBookingsByUserController implements Controller
{

    private BookingReportingRepository bookingRepository;
    private CafeteriaUser user;
    private RepositoryFactory repository;

    public CheckBookingsByUserController()
    {
        this.user = getCurrentUser();
        this.bookingRepository = repository.bookingReporting();
        this.repository = PersistenceContext.repositories();
    }

    public CafeteriaUser getCurrentUser()
    {
        try
        {
            return repository.cafeteriaUsers().findByUsername(
                    AuthorizationService.session().authenticatedUser().username())
                    .get();
        } catch (NoResultException exception)
        {
            System.out.println("No users were found");
            return null;
        }
    }

    public List<Booking> findBookingsByCafeteriaUser(CafeteriaUser user, BookingState bookingState)
    {
        try
        {
            return bookingRepository.findBookingsByCafeteriaUser(user, bookingState);
        } catch (NoResultException exception)
        {
            throw new NoResultException();
        }
    }

}
