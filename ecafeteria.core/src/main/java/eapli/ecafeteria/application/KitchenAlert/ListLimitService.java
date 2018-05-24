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

/**
 *
 * @author Car
 */
public class ListLimitService {
     private final AlertRepositoryLimits alertRepositoryLimits = PersistenceContext.repositories().alertRepositoryLimits() ;
    //Method to iterate all the alergens in the repository
    public Iterable<AlertLimit> allAlertLimits() {
        AuthorizationService.ensurePermissionOfLoggedInUser(ActionRight.ADMINISTER);

        return this.alertRepositoryLimits.findAll();
    }
}