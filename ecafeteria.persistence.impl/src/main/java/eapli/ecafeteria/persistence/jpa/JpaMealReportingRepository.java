/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.meal.Meal;
import eapli.ecafeteria.persistence.MealReportingRepository;
import javax.persistence.TypedQuery;


/**
 *
 * @author Rui Almeida <1160818>
 */
public class JpaMealReportingRepository extends CafeteriaJpaRepositoryBase<Meal, Long> implements MealReportingRepository {

    @Override
    public Iterable<Meal> listServedMeals() {
        final TypedQuery<Meal> query = entityManager().createQuery(
                "Select * FROM Meal",
                Meal.class);

        return query.getResultList();
    }
    
}
