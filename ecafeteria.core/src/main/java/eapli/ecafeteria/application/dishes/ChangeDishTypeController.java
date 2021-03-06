/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
package eapli.ecafeteria.application.dishes;

import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.domain.dishes.DishType;
import eapli.ecafeteria.persistence.DishTypeRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;

/**
 *
 * @author Nuno
 */
public class ChangeDishTypeController implements Controller {

    private final ListDishTypeService svc = new ListDishTypeService();

    private final DishTypeRepository repo = PersistenceContext.repositories().dishTypes();

    public DishType changeDishType(DishType theDishType, String newDescription)
            throws DataConcurrencyException, DataIntegrityViolationException {
        AuthorizationService.ensurePermissionOfLoggedInUser(ActionRight.MANAGE_MENUS);

        if (theDishType == null) {
            throw new IllegalArgumentException();
        }

        theDishType.changeDescriptionTo(newDescription);

        return this.repo.save(theDishType);
    }

    /**
     * in the context of this use case only active dish types are meaningful.
     *
     * @return
     */
    public Iterable<DishType> listDishTypes() {
        return this.svc.activeDishTypes();
    }
}
