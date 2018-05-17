/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.cafeteriauser.AlergenProfile;
import eapli.ecafeteria.domain.cafeteriauser.Balance;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.persistence.AlergenPlanRepository;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.Query;

/**
 *
 * @author utilizador
 */
public class JPAAlergenPlanRepository extends CafeteriaJpaRepositoryBase<AlergenProfile, Long> implements AlergenPlanRepository {

    @Override
    public AlergenProfile findByUser(CafeteriaUser user) {
        Query q = entityManager().createQuery("SELECT a FROM AlergenProfile a"
                + " WHERE user=:user");

        q.setParameter("user", user);

        return (AlergenProfile) q.getSingleResult();
    }

    @Override
    public void saveAlergenProfile(AlergenProfile ap) {
        try {
            save(ap);
        } catch (DataConcurrencyException ex) {
            Logger.getLogger(JPAAlergenPlanRepository.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DataIntegrityViolationException ex) {
            Logger.getLogger(JPAAlergenPlanRepository.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
