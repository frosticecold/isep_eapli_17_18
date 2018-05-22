/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence;

import eapli.ecafeteria.domain.deactivationreasons.DeactivationReasonType;
import eapli.framework.persistence.repositories.DataRepository;

/**
 *
 * @author Raúl Correia <1090657@isep.ipp.pt>
 */
public interface DeactivationReasonTypeRepository extends DataRepository<DeactivationReasonType, Long> {

    /**
     * DeactivationReasonType Repository
     * <p>
     * Default methods are enough</p>
     */
}
