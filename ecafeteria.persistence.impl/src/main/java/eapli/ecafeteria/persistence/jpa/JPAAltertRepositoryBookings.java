/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.dto.AlertBookingDTO;
import eapli.ecafeteria.persistence.AlertRepositoryBookings;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.Optional;

/**
 *
 * @author DAVID
 */
public class JPAAltertRepositoryBookings extends CafeteriaJpaRepositoryBase<AlertBookingDTO, Long> implements AlertRepositoryBookings{

    @Override
    public AlertBookingDTO getNOBookings() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    
}
