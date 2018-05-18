/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.KitchenAlert.AlertLimit;
import eapli.ecafeteria.persistence.AlertRepositoryLimits;

/**
 *
 * @author DAVID
 */
public class JPAAlertRepositoryLimits extends CafeteriaJpaRepositoryBase<AlertLimit, Long> implements AlertRepositoryLimits {

    @Override
    public float[] getLimits() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
