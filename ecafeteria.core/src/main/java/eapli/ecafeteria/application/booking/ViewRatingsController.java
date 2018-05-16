package eapli.ecafeteria.application.booking;

import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.domain.booking.Rating;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.ecafeteria.persistence.RatingReportingRepository;
import eapli.ecafeteria.persistence.RepositoryFactory;
import eapli.framework.application.Controller;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.NoResultException;

/**
 *
 * @author Rui Almeida <1160818>
 */
public final class ViewRatingsController implements Controller {
    
    public ViewRatingsController() {}

    /**
     * Loads all the ratings to an arrayList
     *
     * @param user 
     */
    public List<Rating> readRatings() throws NoResultException{
        
        /**
         * Open repository factory
         */
        RepositoryFactory factory = PersistenceContext.repositories();
        
        /**
         * Open ratings reporting repo
         */
        RatingReportingRepository ratingsRepo = factory.ratingsReporting();
        
        /**
         * Get current user
         */
        CafeteriaUser user = factory.cafeteriaUsers().findByUsername(
                AuthorizationService.session().authenticatedUser().username())
                .get();
        
        ArrayList<Rating> ratings = new ArrayList<>();
        
        /*
        Adds all ratings to an array list
         */
        try {
            for (Rating rating : ratingsRepo.findRatingsByUser(user)) {
                ratings.add(rating);
            }
        } catch (NoResultException ex) {
            throw new NoResultException();
        }
        
        return ratings;
    }

}
