/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.bootstrapers;

import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.domain.cafeteriauser.MecanographicNumber;
import eapli.ecafeteria.domain.cafeteriauser.NutritionalProfile;
import eapli.ecafeteria.persistence.CafeteriaUserRepository;
import eapli.ecafeteria.persistence.NutritionalProfileRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.actions.Action;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rafael Teixeira <1160911@isep.ipp.pt>
 */
public class NutritionalProfileBootstrapper implements Action {

    private final NutritionalProfileRepository nutritionalProfileRepo = PersistenceContext.repositories().nutritionalProfiles();

    /**
     * @author Rafael Teixeira
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
            NutritionalProfile profile1 = new NutritionalProfile(user1.get());
            profile1.changeMaxCalDish("1000");
            profile1.changeMaxCalWeek("10000");
            profile1.changeMaxSaltDish("50");
            profile1.changeMaxSaltWeek("5000");

            NutritionalProfile profile2 = new NutritionalProfile(user2.get());
            profile2.changeMaxCalDish("500");
            profile2.changeMaxCalWeek("9000");
            profile2.changeMaxSaltDish("85");
            profile2.changeMaxSaltWeek("7000");
            /*
            Register
             */
            register(profile1);
            register(profile2);
            
        } catch (DataConcurrencyException | DataIntegrityViolationException ex) {
            Logger.getLogger(AllergenProfileBootstrapper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    /**
     * Saves an nutritional profile to the database
     *
     * @param profile nutritional profile
     * @throws DataConcurrencyException
     * @throws DataIntegrityViolationException
     *
     * @author Rafael Teixeira
     */
    private void register(NutritionalProfile profile) throws DataConcurrencyException, DataIntegrityViolationException {
        nutritionalProfileRepo.save(profile);

    }
    
}
