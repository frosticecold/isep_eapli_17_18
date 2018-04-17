/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.inmemory;

import eapli.ecafeteria.domain.booking.Rating;
import eapli.ecafeteria.persistence.RatingRepository;
import eapli.framework.persistence.repositories.impl.inmemory.InMemoryRepository;

/**
 *
 * @author Joana Oliveira <1161261@isep.ipp.pt>
 */
public class InMemoryRatingRepository extends InMemoryRepository<Rating, Long> implements RatingRepository {

    @Override
    protected Long newKeyFor(Rating rating) {
        return rating.id();
    }

}
