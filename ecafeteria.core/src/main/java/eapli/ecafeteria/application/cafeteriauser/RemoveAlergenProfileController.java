/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.cafeteriauser;

import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.domain.cafeteriauser.AllergenProfile;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.domain.dishes.Alergen;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.domain.Designation;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.NoResultException;
import eapli.ecafeteria.persistence.AllergenProfileRepository;
import eapli.ecafeteria.persistence.AlergenRepository;

/**
 *
 * @author utilizador
 */
public class RemoveAlergenProfileController {

    private final AlergenRepository alergenRepo = PersistenceContext.repositories().alergens();
    private AllergenProfile ap;
    private final AllergenProfileRepository alergenPlanRepo = PersistenceContext.repositories().allergenProfiles();

    public RemoveAlergenProfileController() {
        ap = alergenPlanRepo.findUserAllergenProfile(getCurrentUser());
    }

    public List<Alergen> getAllAlergensNotInAP() {
        List<Alergen> a = alergenRepo.findAll();
        List<Alergen> b = new ArrayList<>();
        if (!(ap.alergens().isEmpty())) {
            for (Alergen c : a) {
                for (Alergen d : ap.alergens()) {
                    if (c.equals(d)) {
                        b.add(d);
                    }
                }

            }
            for (Alergen al : b) {
                a.remove(al);
            }

        }
        return a;
    }

    public List<Alergen> getApAlergens() {
        return ap.alergens();
    }

    public boolean addAlergen(Alergen a) {
        return ap.addAlergen(a);
    }

    public Alergen getAlergenByDesc(String desc) {

        return alergenRepo.findByName(Designation.valueOf(desc)).get();
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

        alergenPlanRepo.saveAlergenProfile(ap);
    }

    public boolean remove(Alergen a) {
        return ap.removeAlergen(a);
    }
}
