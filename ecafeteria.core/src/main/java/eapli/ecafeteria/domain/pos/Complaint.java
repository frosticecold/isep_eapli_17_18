/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.pos;

import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.domain.dishes.Dish;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

/**
 *
 * @author Telmo
 */
@Entity
public class Complaint implements Serializable{
    
    @Id
    @GeneratedValue
    private long id;
   
    private DescriptiveText description;
    
    @OneToOne
    private Dish dish;
    
    @OneToOne
    private CafeteriaUser user;
    
    
    protected Complaint(){
        
    }
    public Complaint(DescriptiveText description,Dish dish, CafeteriaUser user){
        this.description = description;
        this.dish = dish;
        this.user = user;
    }
}
