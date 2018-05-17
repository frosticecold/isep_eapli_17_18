/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.cafeteriauser.AllergenProfile;
import eapli.ecafeteria.domain.cafeteriauser.Balance;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Query;
import eapli.ecafeteria.persistence.AllergenProfileRepository;

/**
 *
 * @author utilizador
 */
public class JpaAllergenProfileRepository extends CafeteriaJpaRepositoryBase<AllergenProfile, Long> implements AllergenProfileRepository {

    /**
     * Searches an allergen profile in the database belonging to a CafeteriaUser
     *
     * @param user the cafeteria user that the profile belongs to
     *
     * @return User's Allergen Profile if it exists, else, throws
     * NoResultException
     *
     * @author Rui Almeida<1160818>
     */
    @Override
    public AllergenProfile findUserAllergenProfile(CafeteriaUser user) {
        return entityManager().createQuery("SELECT allergenProfile "
                + "FROM AllergenProfile allergenProfile "
                + "WHERE allergenProfile.user = :user", AllergenProfile.class)
                .setParameter("user", user)
                .getSingleResult();
    }

    @Override
    public void saveAlergenProfile(AllergenProfile ap) {
        try {
            save(ap);
        } catch (DataConcurrencyException ex) {
            Logger.getLogger(JpaAllergenProfileRepository.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DataIntegrityViolationException ex) {
            Logger.getLogger(JpaAllergenProfileRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
