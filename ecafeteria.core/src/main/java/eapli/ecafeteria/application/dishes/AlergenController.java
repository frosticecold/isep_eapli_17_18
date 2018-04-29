/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.dishes;

import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.domain.dishes.Alergen;
import eapli.ecafeteria.persistence.AlergenRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;
import eapli.framework.domain.Designation;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;

/**
 *
 * @author Car
 */
public class AlergenController implements Controller{
    
    private final ListAlergenService svc = new ListAlergenService();
    private final AlergenRepository alergenRepository = PersistenceContext.repositories().alergens();
    //method that return an interable with all the alergens that exist in the reository
    public Iterable<Alergen> allAlergens() {
        return this.svc.allAlergens();
    }
    
    public Alergen newAlergen(String name)throws DataIntegrityViolationException, DataConcurrencyException {

        AuthorizationService.ensurePermissionOfLoggedInUser(ActionRight.MANAGE_MENUS);
        final Alergen newAlergen = new Alergen(Designation.valueOf(name));
        return this.alergenRepository.save(newAlergen);
    }
}
