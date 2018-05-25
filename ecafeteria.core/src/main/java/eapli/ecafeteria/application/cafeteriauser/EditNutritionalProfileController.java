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

    public String getmaxCalW() {
        return ap.maxCalWeek();
    }

    public String getmaxCalD() {
        return ap.maxCalDish();
    }

    public String getmaxSaltW() {
        return ap.maxSaltWeek();
    }

    public String getmaxSaltD() {
        return ap.maxSaltDish();
    }

    public String maxCalDish(String maxcal) {
        return ap.changeMaxCalDish(maxcal);
    }

    public String maxCalWeek(String maxcalw) {
        return ap.changeMaxCalWeek(maxcalw);
    }

    public String maxSaltDish(String maxsalt) {
        return ap.changeMaxSaltDish(maxsalt);
    }

    public String maxSaltWeek(String maxsaltw) {
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
