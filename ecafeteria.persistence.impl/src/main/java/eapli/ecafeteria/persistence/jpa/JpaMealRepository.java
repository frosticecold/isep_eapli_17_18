/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.jpa;


import eapli.ecafeteria.domain.meal.*;
import eapli.ecafeteria.persistence.*;
import java.util.*;

/**
 * @author MFerreira
 */
public class JpaMealRepository extends CafeteriaJpaRepositoryBase<Meal, Long> implements MealRepository {

    @Override
    public List<Meal> listOfMealsByDateAndMealType(Calendar date, MealType mealType) {
        Iterable<Meal> meals = findAll();
        List<Meal> thisOne = new ArrayList<>();

        for (Meal m : meals) {
            if (m.isOnGivenDate(date) && m.mealtype() == mealType) {
                thisOne.add(m);
            }
        }

        return thisOne;
    }


}