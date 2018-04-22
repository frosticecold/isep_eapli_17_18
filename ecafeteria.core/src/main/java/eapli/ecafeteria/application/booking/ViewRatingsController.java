/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

    RatingReportingRepository ratingsRepo = PersistenceContext.repositories().ratingsReporting();

    /**
     * List of ratings
     */
    private List<Rating> ratings;

    /**
     * Repository Factory
     */
    private RepositoryFactory factory;
    /**
     * Cafeteria user
     */
    private CafeteriaUser user = null;

    public ViewRatingsController() {
        this.factory = PersistenceContext.repositories();

        this.user = factory.cafeteriaUsers().findByUsername(
                AuthorizationService.session().authenticatedUser().username())
                .get();
        
        readRatings(user);
    }

    /**
     * Loads all the ratings to an arrayList
     *
     * @param user 
     */
    public void readRatings(CafeteriaUser user) throws NoResultException{
        ratings = new ArrayList<>();
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
    }

    /**
     * Returns the ratings stored in the repository
     *
     * @return
     */
    public List<Rating> ratings() {
        return this.ratings;
    }
    

}
