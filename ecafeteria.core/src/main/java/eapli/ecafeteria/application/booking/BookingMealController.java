/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.booking;

import eapli.ecafeteria.application.menus.ListMenuService;
import eapli.ecafeteria.domain.menu.*;
import eapli.ecafeteria.domain.meal.*;
import eapli.ecafeteria.persistence.*;
import eapli.framework.application.*;
import eapli.framework.presentation.console.Menu;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author Beatriz Ferreira <1160701@isep.ipp.pt>
 */
public class BookingMealController implements  Controller{
    
     private final ListMenuService svc = new ListMenuService();
    
     private final BookingRepository repository = PersistenceContext.repositories().booking();

     /**
	 *
	 * @param date
	 * @param mealType
	 * @return a list with all meals by date
	 */
	public Iterable<Meal> listMeals(Calendar date, MealType mealType) {
           return  svc.getMealsPublishedByDay(date, mealType);
	}
     
}
