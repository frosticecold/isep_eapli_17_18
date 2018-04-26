/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.inmemory;

import eapli.ecafeteria.domain.booking.Rating;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.persistence.RatingReportingRepository;
import eapli.framework.persistence.repositories.impl.inmemory.InMemoryRepositoryWithLongPK;

/**
 *
 * @author ruial
 */
public class InMemoryRatingReportingRepository extends InMemoryRepositoryWithLongPK<Rating> implements RatingReportingRepository {

    /**
     * Returns all the ratings stored in the repository
     *
     * @param user
     * @return
     */
    @Override
    public Iterable<Rating> findRatingsByUser(CafeteriaUser user) {
//        return match(e -> e.user().equals(user));
        return null;
    }

}
