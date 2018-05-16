/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.booking;

import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.ecafeteria.persistence.RatingReportingRepository;
import eapli.ecafeteria.persistence.RepositoryFactory;

/**
 *
 * @author Joana Oliveira <1161261@isep.ipp.pt>
 */
public class RatingCommentNotificationController {

    RatingReportingRepository ratingRepository = PersistenceContext.repositories().ratingsReporting();

    /**
     * Cafeteria user
     */
    private CafeteriaUser user = null;

    /**
     * Repository Factory
     */
    private RepositoryFactory factory;

    public RatingCommentNotificationController() {
        factory = PersistenceContext.repositories();
        user = factory.cafeteriaUsers().findByUsername(
                AuthorizationService.session().authenticatedUser().username())
                .get();

        readComment(user);
    }

    public void readComment(CafeteriaUser user) {

        ratingRepository.countRatingReply(user);
    }
}
