/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.dishes.DishType;
import eapli.ecafeteria.domain.meal.Execution;
import eapli.ecafeteria.domain.meal.MealType;
import eapli.ecafeteria.persistence.ExecutionRepository;
import java.util.Calendar;
import javax.persistence.Query;
import javax.persistence.TemporalType;

/**
 *
 * @author MFerreira
 */
public class JpaExecutionRepository extends CafeteriaJpaRepositoryBase<Execution, Long> implements ExecutionRepository {

    @Override
    public Iterable<Execution> findMealExecutionByDate(final Calendar cal, final MealType mealType) {
        final Query q;

        q = entityManager().createQuery("SELECT e FROM Execution e "
                + "WHERE e.meal.mealtype=:mealType "
                + "AND e.meal.date=:date", this.entityClass);

        q.setParameter("date", cal, TemporalType.DATE);
        q.setParameter("mealType", mealType);

        return q.getResultList();
    }

    public Integer getMaxNumberOfServings(final DishType dt,final Calendar cal, final MealType mealType) {
        final Query q;

        q = entityManager().createQuery("SELECT SUM(e.madeMeals.madeMeals) FROM Execution e "
                + "WHERE e.meal.mealtype=:mealType "
                + "AND e.meal.date=:date "
                + "AND e.meal.dish.dishType=:dt", Long.class);

        q.setParameter("date", cal, TemporalType.DATE);
        q.setParameter("mealType", mealType);
        q.setParameter("dt", dt);
 
        return (Integer) q.getSingleResult();
    }

}
