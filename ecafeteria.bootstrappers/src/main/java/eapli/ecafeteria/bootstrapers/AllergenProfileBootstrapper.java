/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.bootstrapers;

import eapli.ecafeteria.application.cafeteriauser.CreateAlergenProfileController;
import eapli.ecafeteria.application.dishes.AlergenController;
import eapli.ecafeteria.domain.cafeteriauser.AllergenProfile;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.domain.cafeteriauser.MecanographicNumber;
import eapli.ecafeteria.domain.dishes.Alergen;
import eapli.ecafeteria.persistence.CafeteriaUserRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.actions.Action;
import eapli.framework.domain.Designation;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import eapli.ecafeteria.persistence.AllergenProfileRepository;

/**
 *
 * @author utilizador
 */
public class AllergenProfileBootstrapper implements Action {

    private final AllergenProfileRepository allergenProfileRepo = PersistenceContext.repositories().allergenProfiles();

    /**
     * @author Rui Almeida <1160818> && Ricardo Sousa <>
     */
    @Override
    public boolean execute() {
        try {
            /*
            Repositories
             */
            final CafeteriaUserRepository userRepo = PersistenceContext.repositories().cafeteriaUsers();
            /*
            Users
             */
            final Optional<CafeteriaUser> user1 = userRepo.findByMecanographicNumber(new MecanographicNumber("900330"));
            final Optional<CafeteriaUser> user2 = userRepo.findByMecanographicNumber(new MecanographicNumber("900331"));
            /*
            Profiles
             */
            AllergenProfile profile1 = new AllergenProfile(user1.get());
            profile1.addAlergen(new Alergen(Designation.valueOf(TestDataConstants.ALLERGEN_CRUSTACEOS)));
            profile1.addAlergen(new Alergen(Designation.valueOf(TestDataConstants.ALLERGEN_AMENDOINS)));
            profile1.addAlergen(new Alergen(Designation.valueOf(TestDataConstants.ALLERGEN_AIPO)));

//            AllergenProfile profile2 = new AllergenProfile(user2.get());
//            profile2.addAlergen(new Alergen(Designation.valueOf(TestDataConstants.ALLERGEN_FRUTOS_SECOS)));
//            profile2.addAlergen(new Alergen(Designation.valueOf(TestDataConstants.ALLERGEN_MOLUSCO)));
//            profile2.addAlergen(new Alergen(Designation.valueOf(TestDataConstants.ALLERGEN_GLUTEN)));
            /*
            Register
             */
            register(profile1);
//            register(profile2);
            
        } catch (DataConcurrencyException | DataIntegrityViolationException ex) {
            Logger.getLogger(AllergenProfileBootstrapper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    /**
     * Saves an allergen profile to the database
     *
     * @param profile allergen profile
     * @throws DataConcurrencyException
     * @throws DataIntegrityViolationException
     *
     * @author Rui Almeida <1160818> && Ricardo Sousa <1160900>
     */
    private void register(AllergenProfile profile) throws DataConcurrencyException, DataIntegrityViolationException {
        allergenProfileRepo.save(profile);

    }

}
