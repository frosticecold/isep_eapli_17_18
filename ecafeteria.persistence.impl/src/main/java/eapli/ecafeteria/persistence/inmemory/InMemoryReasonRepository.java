/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.inmemory;

import eapli.ecafeteria.domain.authz.SystemUser;
import eapli.ecafeteria.domain.reasons.Reason;
import eapli.ecafeteria.persistence.ReasonRepository;
import eapli.framework.persistence.repositories.impl.inmemory.InMemoryRepository;
import java.util.Optional;

/**
 *
 * @author Raúl Correia <1090657@isep.ipp.pt>
 */
public class InMemoryReasonRepository extends InMemoryRepository<Reason, Long> implements ReasonRepository{

    @Override
    protected Long newKeyFor(Reason entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Optional<Reason> findReasonBySystemUser(SystemUser user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
