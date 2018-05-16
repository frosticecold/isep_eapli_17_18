/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.booking.Booking;
import eapli.ecafeteria.domain.booking.Rating;
import eapli.ecafeteria.persistence.RatingRepository;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import javax.persistence.TypedQuery;

/**
 *
 * @author Joana Oliveira <1161261@isep.ipp.pt>
 */
public class JpaRatingRepository extends CafeteriaJpaRepositoryBase<Rating, Long> implements RatingRepository {

    @Override
    public Rating saveRating(Rating entity) throws DataConcurrencyException, DataIntegrityViolationException {
        return save(entity);
    }

    @Override
    public Rating getRatingFromBooking(Booking b) {
        TypedQuery<Rating> q;
        q = entityManager().createQuery("SELECT r FROM Rating r WHERE r.booking=:b", Rating.class);
        q.setParameter("b",b);
        return q.getSingleResult();
    }
}
