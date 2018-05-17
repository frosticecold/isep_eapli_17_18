/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.bootstrapers;

import eapli.ecafeteria.application.cafeteriauser.CreateAlergenProfileController;
import eapli.ecafeteria.application.dishes.AlergenController;
import eapli.ecafeteria.domain.cafeteriauser.AlergenProfile;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.domain.cafeteriauser.MecanographicNumber;
import eapli.ecafeteria.domain.dishes.Alergen;
import eapli.ecafeteria.persistence.AlergenPlanRepository;
import eapli.ecafeteria.persistence.CafeteriaUserRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.actions.Action;
import eapli.framework.domain.Designation;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author utilizador
 */
public class AlergenProfileBootstrapper implements Action {

    private final AlergenPlanRepository alergenPlanRepo = PersistenceContext.repositories().AlergenPlans();

    @Override
    public boolean execute() {
        try {
            register(TestDataConstants.ALLERGEN_CRUSTACEOS);
        } catch (DataConcurrencyException ex) {
            Logger.getLogger(AlergenProfileBootstrapper.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DataIntegrityViolationException ex) {
            Logger.getLogger(AlergenProfileBootstrapper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    private void register(String name) throws DataConcurrencyException, DataIntegrityViolationException {
        
        final CafeteriaUserRepository userRepo = PersistenceContext.repositories().cafeteriaUsers();
        final Optional<CafeteriaUser> user1 = userRepo.findByMecanographicNumber(new MecanographicNumber("900330"));
        AlergenProfile ap = new AlergenProfile(user1.get());
        alergenPlanRepo.save(ap);
  

    }

}
