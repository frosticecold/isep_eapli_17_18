/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.booking;

import eapli.ecafeteria.application.cafeteriauser.CafeteriaUserService;
import eapli.ecafeteria.application.menus.ListMenuService;
import eapli.ecafeteria.domain.authz.Username;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.domain.menu.*;
import eapli.ecafeteria.domain.meal.*;
import eapli.ecafeteria.domain.transaction.DebitBooking;
import eapli.ecafeteria.persistence.*;
import eapli.framework.application.*;
import eapli.framework.domain.money.Money;
import java.util.Calendar;
import java.util.Optional;

/**
 *
 * @author Beatriz Ferreira <1160701@isep.ipp.pt>
 */
public class BookingMealController implements  Controller{
    
     private final ListMenuService svc = new ListMenuService();
     private final CafeteriaUserService userService = new CafeteriaUserService();
    
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
        
        public boolean doTransaction(Username id, Meal meal){
            Money mealPrice = meal.dish().currentPrice();
            Optional<CafeteriaUser> user = userService.findCafeteriaUserByUsername(id);
            if(user.get().hasEnoughCredits(mealPrice)){
                 DebitBooking db = new DebitBooking(user.get(), user.get().currentBalance().currentBalance());
                 db.movement(user.get(), mealPrice);
           }
            return false;
        }
     
}
