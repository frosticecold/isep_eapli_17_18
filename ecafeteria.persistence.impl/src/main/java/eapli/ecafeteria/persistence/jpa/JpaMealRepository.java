/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.jpa;


import eapli.ecafeteria.domain.meal.*;
import eapli.ecafeteria.persistence.*;
import eapli.framework.domain.*;
import java.util.*;
import javax.persistence.*;

/**
 * @author Miguel Santos <1161386@isep.ipp.pt>
 */
public class JpaMealRepository extends CafeteriaJpaRepositoryBase<Meal, Long> implements MealRepository {

    @Override
    public List<Meal> listOfMealsByDateAndMealType(Calendar date, MealType mealType) {

        int mealT = MealType.getId(mealType);

        final Query q = entityManager().
                createQuery("SELECT meal FROM Meal meal WHERE mealtype=:mealT AND date=:date", this.entityClass);

        q.setParameter("date", date, TemporalType.DATE);
        q.setParameter("mealT", mealT);

        return q.getResultList();
    }

    @Override
    public Optional<Meal> findMealByDishID(Designation dishid) {
        final Map<String, Object> params = new HashMap<>();
        params.put("dishid", dishid);
        return matchOne("e.dish.id=:dishid", params);
    }

    public Optional<Meal> findOne(Long id) {
        final Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        return matchOne("e.id=:id", params);
    }
}
