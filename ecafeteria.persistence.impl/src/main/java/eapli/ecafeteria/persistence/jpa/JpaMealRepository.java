/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.dishes.Dish;
import eapli.ecafeteria.domain.meal.Meal;
import eapli.ecafeteria.domain.meal.MealType;
import eapli.ecafeteria.domain.menu.Menu;
import eapli.ecafeteria.persistence.MealRepository;
import eapli.framework.domain.Designation;
import java.util.*;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;

/**
 * @author Miguel Santos <1161386@isep.ipp.pt>
 */
public class JpaMealRepository extends CafeteriaJpaRepositoryBase<Meal, Long> implements MealRepository {

    @Override
    public List<Meal> listOfMealsByDateAndMealType(Calendar date, MealType mealType) {

        Query q = entityManager().
                createQuery("SELECT meal FROM Meal meal "
                        + "WHERE meal.mealtype=:mealType "
                        + "AND meal.date=:date", Meal.class);

        q.setParameter("date", date);
        q.setParameter("mealType", mealType);
        return q.getResultList();
    }

    @Override
    public Optional<Meal> findMealByDishID(Designation dishid) {
        final Map<String, Object> params = new HashMap<>();
        params.put("dishid", dishid);
        return matchOne("e.dish.id=:dishid", params);
    }

    @Override
    public Optional<Meal> findOne(Long id) {
        final Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        return matchOne("e.id=:id", params);
    }

    @Override
    public List<Meal> findMealsByMenu(Menu m) {
        final Query q;
        q = entityManager().createQuery("SELECT e FROM Meal e WHERE :menu = e.menu", Meal.class);
        q.setParameter("menu", m);
        return q.getResultList();
    }

    @Override
    public Iterable<Meal> listMealsFromMenuByGivenDay(Menu menu, Calendar day) {
        final Map<String, Object> params = new HashMap<>();
        params.put("menu", menu);
        params.put("day", day);
        return match("e.menu=:menu AND e.date =:day", params);
    }

    public int findMealByMenuAndDate(Menu m) {
        final Query q;
        q = entityManager().createQuery("SELECT MIN(CURRENT_DATE-m.date) FROM Meal m WHERE m.menu=:menu  ", Meal.class);
        q.setParameter("menu", m);
        return q.getFirstResult();
    }


    @Override
    public List<Meal> getMealByDate(Calendar cal) {
        TypedQuery<Meal> q;
        q = entityManager().createQuery("SELECT m FROM Meal m WHERE m.date=:cal", Meal.class);
        q.setParameter("cal", cal);
        return q.getResultList();
    }
    
    @Override
    public List<Meal> getMealByDish(Dish dish){
        TypedQuery<Meal> q;
        q = entityManager().createQuery("SELECT m FROM Meal m WHERE m.dish=:dish", Meal.class);
        q.setParameter("dish", dish);
        return q.getResultList(); 
   }

}
