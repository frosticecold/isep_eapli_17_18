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

/**
 *
 * @author Car
 */
public class ListAlergenService {
    private final AlergenRepository alergenRepository = PersistenceContext.repositories().alergens();
    public Iterable<Alergen> allAlergens() {
        AuthorizationService.ensurePermissionOfLoggedInUser(ActionRight.MANAGE_MENUS);

        return this.alergenRepository.findAll();
    }
}
