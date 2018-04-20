/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence;

import eapli.ecafeteria.domain.booking.Rating;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.persistence.repositories.DataRepository;

/**
 *
 * @author Joana Oliveira <1161261@isep.ipp.pt>
 */
public interface RatingRepository extends DataRepository<Rating,Long> {
     public Rating saveRating(Rating entity) throws DataConcurrencyException, 
            DataIntegrityViolationException;
}
