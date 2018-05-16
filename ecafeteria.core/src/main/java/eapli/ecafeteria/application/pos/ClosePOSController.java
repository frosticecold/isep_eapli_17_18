/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.pos;

import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.domain.dishes.DishType;
import eapli.ecafeteria.domain.meal.MealType;
import eapli.ecafeteria.domain.pos.DeliveryMealSession;
import eapli.ecafeteria.domain.pos.POS;
import eapli.ecafeteria.persistence.POSRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.Calendar;
import java.util.Map;
import java.util.Optional;

/**
 *
 * @author André Oliveira 1040862
 */
public class ClosePOSController implements Controller {

    private final ListAvailableMealsService availableMealsService = new ListAvailableMealsService();
    private final POS p = new POS(AuthorizationService.session().authenticatedUser());
    private final DeliveryMealSession d = new DeliveryMealSession(p);
    private final POSRepository jpaPOS = PersistenceContext.repositories().posRepository();
    private POS pointofsale;

    public void closeSession() {
        AuthorizationService.clearSession();
    }

    public MealType checkMealype() {
        if (d.isDinner()) {
            return MealType.DINNER;
        }
        return MealType.LUNCH;
    }

    public String checkDate() {
        return d.sessionDate().toString();
    }

    public boolean checkPoSState() {
        return pointofsale.isClosed();
    }

    public boolean closeDeliveryMealSession() throws DataConcurrencyException, DataIntegrityViolationException {
        Optional<POS> POSinDatabase = jpaPOS.findOne(Long.valueOf("1"));
        if (POSinDatabase.isPresent()) {
            pointofsale = POSinDatabase.get();
            if (!checkPoSState()) {
                pointofsale.changeState();
                jpaPOS.save(pointofsale);
            }
        } else {
            return false;
        }
        return true;
    }

    public Map<DishType, Long> listDeliveredMeals(Calendar cal, MealType mt) {
        Map<DishType, Long> calcDeliveredStatistics = availableMealsService.calcDeliveredStatistics(cal, mt);
        return calcDeliveredStatistics;
    }
}
