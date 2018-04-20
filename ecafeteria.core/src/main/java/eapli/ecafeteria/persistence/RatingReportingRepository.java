/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence;

import eapli.ecafeteria.domain.booking.Rating;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.framework.persistence.repositories.ReportingRepository;

/**
 *
 * @author Rui Almeida <1160818>
 */
public interface RatingReportingRepository extends ReportingRepository {
    
    public Iterable<Rating> findRatingsByUser(CafeteriaUser user);
}
