/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.user.console.presentation.bookings;

import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.application.booking.BookingMealController;
import eapli.ecafeteria.domain.meal.MealType;
import eapli.framework.application.Controller;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.util.Console;
import java.util.Calendar;

/**
 *
 * @author Beatriz Ferreira <1160701@isep.ipp.pt>
 */
public class BookingMealUI extends AbstractUI {

    private final  BookingMealController controller = new BookingMealController();
    
    
    protected Controller controller() {
        return this.controller;
    }

    
    @Override
    protected boolean doShow() {
       final String day = Console.readLine("Insert desired day (DD/MM/YYYY):\n");  
       Calendar cal = Console.readCalendar(day);
       
       
      System.out.println("Choose Meal Type:\n"
                + "1-Lunch\n"
                + "2-Dinner");
    
       final String mealType = Console.readLine("Insert 1 or 2:\n");
       if(mealType.equals(1)){
          controller.listMeals(cal,MealType.LUNCH);
       }else if (mealType.equals(2)){
           controller.listMeals(cal,MealType.DINNER);
       }else{
           System.out.println("Inválido!");
           return false;
       }
       return true;
    }

    @Override
    public String headline() {
        return "eCAFETERIA [@" + AuthorizationService.session().authenticatedUser().id() + "]   ";
    }
    
}
