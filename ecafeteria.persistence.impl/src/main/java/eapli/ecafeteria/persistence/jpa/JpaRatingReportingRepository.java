/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.booking.Rating;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.persistence.RatingReportingRepository;

/**
 *
 * @author Rui Almeida <1160818>
 */
public class JpaRatingReportingRepository extends CafeteriaJpaRepositoryBase implements RatingReportingRepository {

    /**
     * Returns all the ratings stored in the repository
     *
     * @param user
     * @return
     */
    @Override
    public Iterable<Rating> findRatingsByUser(CafeteriaUser user) {
        return entityManager().createQuery("SELECT rating "
                + "FROM Rating rating, Booking booking "
                + "WHERE rating.booking = booking.idBooking "
                + "AND booking.cafeteriaUser.mecanographicNumber = :user")
                .setParameter("user", user.id())
                .getResultList();
    }
}
