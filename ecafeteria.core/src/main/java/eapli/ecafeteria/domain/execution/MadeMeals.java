/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.execution;

import eapli.framework.domain.ddd.ValueObject;
import java.io.Serializable;
import javax.persistence.Embeddable;

/**
 *
 * @author MFerreira
 */
@Embeddable
public class MadeMeals implements ValueObject, Serializable {

    private static final long serialVersionUID = 12L;
    
    private Integer madeMeals;
 
    public MadeMeals(Integer madeMeals){
        if (madeMeals == null || madeMeals < 0) {
            throw new IllegalArgumentException("Made Meals can't be negative");
        }
        this.madeMeals = madeMeals;
    }
    
     protected MadeMeals() {
        // for ORM
    }

    @Override
    public String toString() {
        return "MadeMeals- "  + madeMeals;
    }

    public Integer madeMeals() {
        return this.madeMeals;
    }
}
