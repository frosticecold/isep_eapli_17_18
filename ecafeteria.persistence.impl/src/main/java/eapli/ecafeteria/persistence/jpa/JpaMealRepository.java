/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.jpa;


import eapli.ecafeteria.domain.meal.*;
import eapli.ecafeteria.persistence.MealRepository;
import java.util.Calendar;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.TemporalType;

/**
 *
 * @author Miguel Santos <1161386@isep.ipp.pt>
 */
public class JpaMealRepository extends CafeteriaJpaRepositoryBase<Meal, Long> implements MealRepository{

    @Override
    public List<Meal> listOfMealsByDateAndMealType(Calendar date, MealType mealType) {
        final Query q = entityManager().
                createQuery("SELECT meal.* "
                        + "FROM Meal meal"
                        + "WHERE mealtype=:mealType"
                        + "AND date=:date", this.entityClass);
        
        q.setParameter("date", date, TemporalType.DATE);
        q.setParameter("mealtype", mealType);
        
        return q.getResultList();
    }


}
