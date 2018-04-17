/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.meal;

import eapli.ecafeteria.domain.booking.Rating;
import eapli.ecafeteria.domain.dishes.Dish;
import eapli.framework.util.DateTime;
import java.io.Serializable;
import java.util.Calendar;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

/**
 *
 * @author Beatriz Ferreira <1160701@isep.ipp.pt>
 */
@Entity
public class Meal implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Version
    private Long version;

    /**
     * Dish of a meal
     */
    private Dish dish;

    /**
     * Type of a meal
     */
    private MealType mealtype;
    /**
     * Date of a Meal
     *
     * @author Raúl Correia
     */
    @Temporal(TemporalType.DATE)
    private Calendar date;
    /*
    * Ratings of the meal
    */
    @OneToMany()
    private List<Rating> ratings;
       
    /**
     * For ORM
     */
    protected Meal() {

    }

    /**
     * Constructor for a Meal
     *
     * @param dish Dish for this meal
     * @param mt Mealtype for this meal
     * @param cal Date for this meal
     */
    public Meal(final Dish dish, final MealType mt, final Calendar cal) {
        setData(dish, mt, cal);
    }

    private void setData(final Dish dish, final MealType mt, final Calendar cal) {
        if (dish == null || mt == null || cal == null) {
            throw new IllegalArgumentException("Arguments can't be null.");
        }
        this.dish = dish;
        this.mealtype = mt;
        this.date = (Calendar) cal.clone();
    }

    public boolean isOnGivenDate(Calendar givenDate) {
        return DateTime.isSameDate(givenDate, date);
    }

    public Long id() {
        return id;
    }

    public Dish dish() {
        return dish;
    }
    
    
    /**
     * Returns Meal actual date
     * @return 
     */
    public Calendar getMealDate(){
        return this.date;
    }
    
    /**
     * Returns the ratings given on said meal
     * @return 
     */
    public Iterable<Rating> ratings() {
        return this.ratings;
    }

    public MealType mealtype() {
        return mealtype;
    }
       
}
