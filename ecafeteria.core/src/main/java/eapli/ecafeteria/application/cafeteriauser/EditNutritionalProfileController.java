/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.cafeteriauser;

import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.domain.cafeteriauser.NutritionalProfile;
import eapli.ecafeteria.persistence.NutritionalProfileRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import javax.persistence.NoResultException;

/**
 *
 * @author luisdematos
 */
public class EditNutritionalProfileController {

    private NutritionalProfile ap;
    private final NutritionalProfileRepository nutritionalPlanRepo = PersistenceContext.repositories().nutritionalProfiles();

    public EditNutritionalProfileController() {
        ap = nutritionalPlanRepo.findUserNutritionalProfile(getCurrentUser());
    }

    public int getmaxCalW() {
        return ap.maxCalWeek();
    }

    public int getmaxCalD() {
        return ap.maxCalDish();
    }

    public int getmaxSaltW() {
        return ap.maxSaltWeek();
    }

    public int getmaxSaltD() {
        return ap.maxSaltDish();
    }

    public int maxCalDish(int maxcal) {
        return ap.changeMaxCalDish(maxcal);
    }

    public int maxCalWeek(int maxcalw) {
        return ap.changeMaxCalWeek(maxcalw);
    }

    public int maxSaltDish(int maxsalt) {
        return ap.changeMaxSaltDish(maxsalt);
    }

    public int maxSaltWeek(int maxsaltw) {
        return ap.changeMaxSaltWeek(maxsaltw);
    }

    private CafeteriaUser getCurrentUser() {
        try {
            return PersistenceContext.repositories().cafeteriaUsers().findByUsername(
                    AuthorizationService.session().authenticatedUser().username())
                    .get();
        } catch (NoResultException exception) {
            System.out.println("No users were found");
            return null;
        } catch (NullPointerException ex) {
            System.out.println("ERRO");
            return null;
        }
    }

    public void save() {

        nutritionalPlanRepo.saveNutritionalProfile(ap);
    }
}
