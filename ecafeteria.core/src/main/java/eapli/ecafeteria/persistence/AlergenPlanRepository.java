/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence;

import eapli.ecafeteria.domain.cafeteriauser.AlergenProfile;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.domain.dishes.Alergen;
import eapli.framework.domain.Designation;
import eapli.framework.persistence.repositories.DataRepository;

/**
 *
 * @author utilizador
 */
public interface AlergenPlanRepository extends DataRepository<AlergenProfile, Integer> {

    AlergenProfile findByUser(CafeteriaUser user);

    void saveAlergenProfile(AlergenProfile ap);
}
