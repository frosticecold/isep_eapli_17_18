/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.pos;

import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.persistence.DishReportingRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.ecafeteria.reporting.dishes.DishesPerDishType;
import eapli.framework.application.Controller;

/**
 *
 * @author Oliveira
 */
public class ClosePOSController implements Controller {
    
    private final DishReportingRepository repo = PersistenceContext.repositories().dishReporting();

    public void closeSession() {
        AuthorizationService.clearSession();
    }

    public Iterable<DishesPerDishType> listDeliveredMeals() {
        return repo.dishesPerDishType();
    }
}
