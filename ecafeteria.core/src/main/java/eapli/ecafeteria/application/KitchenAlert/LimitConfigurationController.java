/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.KitchenAlert;

import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.domain.KitchenAlert.AlertLimit;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.persistence.AlertRepositoryLimits;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;

/**
 *
 * @author Car
 */
public class LimitConfigurationController implements Controller {
     private final AlertRepositoryLimits alertRepositoryLimits = PersistenceContext.repositories().alertRepositoryLimits() ;
      final AlertLimit limit = null;
     
    public void configureYellowLimit(double limitValue) throws DataIntegrityViolationException, DataConcurrencyException, Exception {

        AuthorizationService.ensurePermissionOfLoggedInUser(ActionRight.MANAGE_KITCHEN);

        AuthorizationService.ensurePermissionOfLoggedInUser(ActionRight.MANAGE_KITCHEN);

        
        configureLimit(limit, (float) limitValue);
    }
     public void configureRedLimit(double limitValue) throws DataIntegrityViolationException, DataConcurrencyException, Exception {

        AuthorizationService.ensurePermissionOfLoggedInUser(ActionRight.MANAGE_KITCHEN);

       
        configureLimit(limit, (float) limitValue);

        
    }
     
      public AlertLimit configureLimit(AlertLimit limit,float limitValue) throws DataIntegrityViolationException, DataConcurrencyException, Exception {

        AuthorizationService.ensurePermissionOfLoggedInUser(ActionRight.MANAGE_KITCHEN);

        limit.configureLimitValue(limitValue);

        return this.alertRepositoryLimits.save(limit);
    }
      
     public double getYellowLimit(){
        return getLimit(limit);
    }
    
    
    public double getRedLimit(){
        return getLimit(limit);
    }
    
    private double getLimit(AlertLimit limit){
         return limit.getLimitValue();
    }
}
