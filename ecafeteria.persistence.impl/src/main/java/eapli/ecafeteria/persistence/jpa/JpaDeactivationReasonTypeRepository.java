/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.deactivationreasons.DeactivationReasonType;
import eapli.ecafeteria.persistence.DeactivationReasonTypeRepository;
import eapli.framework.persistence.repositories.TransactionalContext;
import eapli.framework.persistence.repositories.impl.jpa.JpaAutoTxRepository;
import java.io.Serializable;

/**
 *
 * @author Raúl Correia <1090657@isep.ipp.pt>
 */
public class JpaDeactivationReasonTypeRepository extends JpaAutoTxRepository<DeactivationReasonType, Long> implements DeactivationReasonTypeRepository {

    public JpaDeactivationReasonTypeRepository(TransactionalContext tx) {
        super(tx);
    }

}
