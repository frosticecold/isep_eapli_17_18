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

/**
 *
 * @author MFerreira
 */
public class JpaMealRepository extends CafeteriaJpaRepositoryBase<Meal, Long> implements MealRepository{

    @Override
    public List<Meal> listOfMealsByDateAndMealType(Calendar date, MealType mealType) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


}
