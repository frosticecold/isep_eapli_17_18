/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.pos.Complaint;
import eapli.ecafeteria.persistence.ComplaintRepository;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;

/**
 *
 * @author Telmo
 */
public class JpaComplaintRepository extends CafeteriaJpaRepositoryBase<Complaint, Long> implements ComplaintRepository{

    @Override
    public Complaint saveComplaint(Complaint complaint) throws DataConcurrencyException, DataIntegrityViolationException {
        return save(complaint);
    }
    
}
