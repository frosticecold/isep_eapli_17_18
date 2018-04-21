/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.meal;

import eapli.ecafeteria.domain.*;
import eapli.ecafeteria.domain.booking.Rating;
import eapli.framework.domain.ddd.AggregateRoot;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Version;

/**
 *
 * @author MFerreira
 */
@Entity
public class Execution implements AggregateRoot<Long>, Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Version
    private Long version;
    
    @OneToOne()
    @JoinColumn(name="mealid")
    private Meal meal;
    
    private MadeMeals madeMeals;
    
    public Execution(Meal meal, MadeMeals madeMeals){
         this.meal = meal;
         this.madeMeals = madeMeals;
    }
    
    protected Execution() {
    }

    @Override
    public boolean sameAs(Object other) {
        if (!(other instanceof Execution)) {
            return false;
        }
        final Execution otherRating = (Execution) other;
        if (this == otherRating) {
            return true;
        }
        if (this.meal.equals(otherRating.meal)) {
            return false;
        }
        if (!this.madeMeals.equals(otherRating.madeMeals)) {
            return false;
        }
        return true;
    }

    @Override
    public Long id() {
         return this.id;
    }

    @Override
    public String toString() {
        return "Meal=" + meal.dish().toString()
                + "\n" + madeMeals.toString();
    }
    
    
}
