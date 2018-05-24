/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.KitchenAlert;

import eapli.framework.domain.ddd.AggregateRoot;
import java.io.Serializable;
import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Car
 */
@Entity
public class LimitConfiguration implements AggregateRoot<Long>, Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Embedded
    @AttributeOverride(name="limitValue", column = @Column(name="YellowLimit"))
    private AlertLimit yellowLimit;
    
    @Embedded
    @AttributeOverride(name="limitValue", column = @Column(name="RedLimit"))
    private AlertLimit redLimit;

    public LimitConfiguration(AlertLimit yellowLimit, AlertLimit redLimit){
        
    }
    
    /**
     * Initializes limits with default values
     */
    public LimitConfiguration() {
        try {
            yellowLimit = new AlertLimit((float) 0.75);
            redLimit = new AlertLimit((float)0.9);
        } catch(Exception e){
            //This exception should not happen
            System.out.println(e.getMessage());
        }
    }
    public boolean configureYellowLimit(float limitValue){
        return configureLimit(yellowLimit, limitValue);
    }
    
    public boolean configureRedLimit(float limitValue){
        return configureLimit(redLimit, limitValue);
    }
    
    private boolean configureLimit(AlertLimit limit, float limitValue){
        try{
            limit.configureLimitValue(limitValue);
            return true;
        } catch(Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean sameAs(Object other) {
        if(other.equals(this.id))
            return true;
        return false;            
    }

    @Override
    public Long id() {
        return this.id;
    }
    
    
    public double getYellowLimit(){
        return getLimit(yellowLimit);
    }
    
    
    public double getRedLimit(){
        return getLimit(redLimit);
    }
    
    private double getLimit(AlertLimit limit){
        try{
            
            return limit.getLimitValue();
        } catch(Exception e) {
            System.out.println(e.getMessage());
            return 0;
        }
    }
}
