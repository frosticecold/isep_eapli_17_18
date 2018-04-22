/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.booking;

import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.domain.booking.Booking;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.persistence.BookingReportingRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.ecafeteria.persistence.RepositoryFactory;
import eapli.framework.application.Controller;
import javax.persistence.NoResultException;

/**
 *
 * @author João Rocha 1161838
 */
public class ViewNextBookingController implements Controller {

    private BookingReportingRepository bookingRepo;
    private RepositoryFactory repositories;
    private CafeteriaUser user;

    public ViewNextBookingController() {
        this.repositories = PersistenceContext.repositories();
        this.user = getCurrentUser();
        this.bookingRepo = repositories.bookingReporting();
    }

    public CafeteriaUser getCurrentUser() {
        try {
            return repositories.cafeteriaUsers().findByUsername(
                    AuthorizationService.session().authenticatedUser().username())
                    .get();
        } catch (NoResultException ex) {
            System.out.println("No users were found");
            return null;
        }
    }

    /**
     * Gets user next booking
     *
     * @return
     */
    public Booking getNextBooking() throws NoResultException {
        try {
            return bookingRepo.findNextBooking(user);
        } catch (NoResultException ex) {
            throw new NoResultException();
        }
    }
}
