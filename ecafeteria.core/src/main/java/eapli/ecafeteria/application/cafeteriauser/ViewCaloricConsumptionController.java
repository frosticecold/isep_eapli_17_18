/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.cafeteriauser;

import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.domain.dishes.Dish;
import eapli.ecafeteria.persistence.CafeteriaUserRepository;
import eapli.ecafeteria.persistence.DishRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author Joao Rocha 1161838
 */
public class ViewCaloricConsumptionController implements Controller {

    /**
     * DishRepository instance
     */
    private final DishRepository dishRepo;
    
    /**
     * CafeteriaUserRepository instance
     */
    private final CafeteriaUserRepository userRepository;
    
    /**
     * CafeteriaUser instance
     */
    private final CafeteriaUser user;
    
    /**
     * Class constructor
     */
    public ViewCaloricConsumptionController(){
        this.dishRepo       = PersistenceContext.repositories().dishes();
        this.userRepository = PersistenceContext.repositories().cafeteriaUsers();
        this.user           = userRepository.findByUsername(AuthorizationService.session().authenticatedUser().id()).get();
    }

    /**
     * Method that gets a list with the dishes that were served between
     * @param initialDate
     * @param finalDate
     * @return 
     */
    public Iterable<Dish> findServedDishBetween(Calendar initialDate, Calendar finalDate) {
        return dishRepo.findServedDishesBetween(user,initialDate, finalDate);
    }

    /**
     * Method that calculates the total of calories consumed by a user between two dates
     * @param initialDate
     * @param finalDate
     * @return 
     */
    public double getCaloricConsuption(Calendar initialDate, Calendar finalDate) {
        double calories = 0;
        for(Dish dish : findServedDishBetween(initialDate,finalDate)){
            calories += dish.nutricionalInfo().calories();
        }
        
        return calories;
    }

}
