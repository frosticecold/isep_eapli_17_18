/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence;

import eapli.ecafeteria.domain.cafeteriauser.BalanceLimits;
import eapli.framework.persistence.repositories.DataRepository;
import eapli.ecafeteria.domain.KitchenAlert.*;

/**
 *
 * @author DAVID
 */
public interface AlertRepositoryLimits extends DataRepository<AlertLimit, Long> {
    
    float[] getLimits();
    
}
