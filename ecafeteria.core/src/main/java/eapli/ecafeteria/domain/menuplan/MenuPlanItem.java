/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.menuplan;

import eapli.ecafeteria.domain.meal.Meal;
import eapli.framework.domain.ddd.AggregateRoot;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Version;

@Entity
public class MenuPlanItem implements AggregateRoot<Long>,Serializable {
    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name = "id_menu_plan_item")
    private long id;
    
    @Version
    private Long version;
    
    @OneToOne
    private Meal currentMeal;
   
    @Embedded
    private Quantity quantityNumber;
    
    protected MenuPlanItem(){
    
    }

    public Quantity getQuantityNumber() {
        return quantityNumber;
    }

    public MenuPlanItem(Meal currentMeal, Quantity quantity) {
        this.currentMeal = currentMeal;
        this.quantityNumber = quantity;
    }

    @Override
    public boolean sameAs(Object other) {
        if(!(other instanceof MenuPlan )){
            return false;
        }
        final MenuPlanItem mpi=(MenuPlanItem) other;
        
        if(this==mpi){
            return true;
        }
        
        return this.id==mpi.id();
    }

    @Override
    public Long id() {
        return id;
    }

    public Meal getCurrentMeal() {
        return currentMeal;
    }
  
}
