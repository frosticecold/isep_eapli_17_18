/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.KitchenAlert;

import static com.google.common.collect.Iterables.limit;
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
     private final ListLimitService svc = new ListLimitService();
     private final AlertRepositoryLimits alertRepositoryLimits = PersistenceContext.repositories().alertRepositoryLimits() ;
      
     public Iterable<AlertLimit> allAlertLimits() {
        return this.svc.allAlertLimits();
    }
    public void configureYellowLimit(float limitValue) throws DataIntegrityViolationException, DataConcurrencyException, Exception {

        AuthorizationService.ensurePermissionOfLoggedInUser(ActionRight.ADMINISTER);

        AuthorizationService.ensurePermissionOfLoggedInUser(ActionRight.ADMINISTER);

        AlertLimit limit=new AlertLimit(0);
        limit.configureLimitValue(limitValue);
    }
     public void configureRedLimit(float limitValue) throws DataIntegrityViolationException, DataConcurrencyException, Exception {

        AuthorizationService.ensurePermissionOfLoggedInUser(ActionRight.ADMINISTER);

       AlertLimit limit=new AlertLimit(0);
        limit.configureLimitValue(limitValue);

        
    }
     
      
    
    }

