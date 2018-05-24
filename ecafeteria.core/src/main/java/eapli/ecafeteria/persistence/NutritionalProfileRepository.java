/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence;

import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.domain.cafeteriauser.NutritionalProfile;
import eapli.framework.persistence.repositories.DataRepository;

/**
 *
 * @author Rafael Teixeira
 */
public interface NutritionalProfileRepository extends DataRepository<NutritionalProfile, Long> {

    
    NutritionalProfile findUserNutritionalProfile(CafeteriaUser user);

    void saveNutritionalProfile(NutritionalProfile ap);
}
