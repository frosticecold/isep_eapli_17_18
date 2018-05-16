/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.booking.Rating;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.persistence.RatingReportingRepository;
import javax.persistence.Query;

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
                + "FROM Rating rating "
                + "WHERE rating.user = :user")
                .setParameter("user", user)
                .getResultList();
    }

    public int countRatingReply(CafeteriaUser user) {
        final Query q;
        q = entityManager().createQuery("SELECT COUNT(r) FROM Rating r"
                + "WHERE r.booking =: booking"
                + "AND r.cafeteriaUser =: cafeteriaUser"
                + "AND r.reply =: reply");

        q.setParameter("CafeteriaUser", user);
        return (int) q.getSingleResult();
    }

}
