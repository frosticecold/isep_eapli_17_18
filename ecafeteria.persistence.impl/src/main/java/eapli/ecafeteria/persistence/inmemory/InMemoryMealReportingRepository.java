/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.inmemory;

import eapli.ecafeteria.domain.meal.Meal;
import eapli.ecafeteria.persistence.MealReportingRepository;
import eapli.framework.persistence.repositories.impl.inmemory.InMemoryRepository;
import java.io.Serializable;

/**
 *
 * @author Rui Almeida <1160818>
 */
public class InMemoryMealReportingRepository extends InMemoryRepository implements MealReportingRepository {

    @Override
    protected Serializable newKeyFor(Object entity) {
       //return x;
        throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public Iterable<Meal> listServedMeals() {
        throw new UnsupportedOperationException("Not implemented");
        //return match(meal -> meal.isServed());
    }


}
