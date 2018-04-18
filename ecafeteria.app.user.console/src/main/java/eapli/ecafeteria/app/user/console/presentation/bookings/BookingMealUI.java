/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.user.console.presentation.bookings;

import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.application.booking.BookingMealController;
import eapli.ecafeteria.domain.booking.BookingState;
import eapli.ecafeteria.domain.booking.BookingStates;
import eapli.ecafeteria.domain.meal.Meal;
import eapli.ecafeteria.domain.meal.MealType;
import eapli.framework.application.Controller;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.util.Console;
import java.util.Calendar;
import jdk.nashorn.internal.ir.BreakNode;

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
      
      Iterable<Meal> mealList ;
      
       final String mealType = Console.readLine("Insert 1 or 2:\n");
       if(mealType.equals(1)){
         mealList = controller.listMeals(cal,MealType.LUNCH);
       }else if (mealType.equals(2)){
         mealList =  controller.listMeals(cal,MealType.DINNER);
       }else{
           System.out.println("Inválido!");
          return false;
       }
       
        System.out.println("Choose one meal");
        final Long id = Console.readLong("Insert the meal id:\n");
        
        Meal choosedMeal = null;
        
        for(Meal meal : mealList){
            if(meal.id()==id){
               controller.doTransaction(AuthorizationService.session().authenticatedUser().id(), meal);
                choosedMeal = meal;
            }else{
                System.out.println("Id inválido");
            }
        }
        
        BookingStates bookingState =  BookingStates.BOOKED;
       controller.persistBooking(AuthorizationService.session().authenticatedUser().id(), cal.getTime(),  BookingState.BookingStates.BOOKED, choosedMeal);
       
       
       return true;
    }

    @Override
    public String headline() {
        return "eCAFETERIA [@" + AuthorizationService.session().authenticatedUser().id() + "]   ";
    }
    
}
