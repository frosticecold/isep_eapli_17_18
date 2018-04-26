/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.inmemory;

import eapli.ecafeteria.domain.dishes.Alergen;
import eapli.ecafeteria.domain.meal.Meal;
import eapli.ecafeteria.persistence.AlergenRepository;
import eapli.framework.domain.Designation;
import eapli.framework.persistence.repositories.impl.inmemory.InMemoryRepository;

/**
 *
 * @author Car
 */
public class InMemoryAlergenRepository extends InMemoryRepository<Alergen, Designation> implements AlergenRepository {

    @Override
    protected Designation newKeyFor(Alergen entity) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


}
