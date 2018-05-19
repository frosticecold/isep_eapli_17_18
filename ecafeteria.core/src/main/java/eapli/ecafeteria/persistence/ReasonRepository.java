/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence;

import eapli.ecafeteria.domain.authz.SystemUser;
import eapli.ecafeteria.domain.reasons.Reason;
import eapli.framework.persistence.repositories.DataRepository;
import java.util.Optional;

/**
 *
 * @author Raúl Correia <1090657@isep.ipp.pt>
 */
public interface ReasonRepository extends DataRepository<Reason, Long> {

    /**
     * Given an CafeteriaUser returs the Reason
     * @param user
     * @return 
     */
    Optional<Reason> findReasonByCafeteriaUser(final SystemUser user);

}
