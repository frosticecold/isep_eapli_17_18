/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence;

import eapli.ecafeteria.domain.pos.Complaint;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.persistence.repositories.DataRepository;

/**
 *
 * @author Telmo
 */
public interface ComplaintRepository extends DataRepository<Complaint,Long> {
    public Complaint saveComplaint(Complaint complaint)throws DataConcurrencyException, 
            DataIntegrityViolationException;
}
