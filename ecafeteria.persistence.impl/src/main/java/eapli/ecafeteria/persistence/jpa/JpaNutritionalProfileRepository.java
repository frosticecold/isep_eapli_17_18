/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.domain.cafeteriauser.NutritionalProfile;
import eapli.ecafeteria.persistence.NutritionalProfileRepository;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author luisdematos
 */
public class JpaNutritionalProfileRepository extends CafeteriaJpaRepositoryBase<NutritionalProfile, Long> implements NutritionalProfileRepository {


    @Override
    public NutritionalProfile findUserNutritionalProfile(CafeteriaUser user) {
        return entityManager().createQuery("SELECT nutritionalProfile "
                + "FROM NutritionalProfile nutritionalProfile "
                + "WHERE nutritionalProfile.user = :user", NutritionalProfile.class)
                .setParameter("user", user)
                .getSingleResult();
    }

    @Override
    public void saveNutritionalProfile(NutritionalProfile ap) {
        try {
            save(ap);
        } catch (DataConcurrencyException | DataIntegrityViolationException ex) {
            Logger.getLogger(JpaAllergenProfileRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
