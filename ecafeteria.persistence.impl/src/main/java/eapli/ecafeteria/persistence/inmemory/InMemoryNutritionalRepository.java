/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.inmemory;

import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.domain.cafeteriauser.NutritionalProfile;
import eapli.ecafeteria.persistence.NutritionalProfileRepository;
import eapli.framework.persistence.repositories.impl.inmemory.InMemoryRepository;

/**
 *
 * @author luisdematos
 */
public class InMemoryNutritionalRepository extends InMemoryRepository<NutritionalProfile, Long> implements NutritionalProfileRepository{

    /**
     *
     * @param entity
     * @return
     */
    @Override
    protected Long newKeyFor(NutritionalProfile entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     * @param user
     * @return
     */
    @Override
    public NutritionalProfile findUserNutritionalProfile(CafeteriaUser user) {
        return matchOne(e -> e.user().equals(user)).get();
    }

    /**
     *
     * @param ap
     */
    @Override
    public void saveNutritionalProfile(NutritionalProfile ap) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
