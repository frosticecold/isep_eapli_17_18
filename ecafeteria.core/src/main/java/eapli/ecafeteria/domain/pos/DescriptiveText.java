/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.pos;

import eapli.framework.domain.ddd.ValueObject;
import java.io.Serializable;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 *
 * @author Telmo
 */
@Embeddable
public class DescriptiveText implements ValueObject, Serializable {
    
    
    private static final long serialVersionUID = 900L;
    
    
    private String description;
    
    public DescriptiveText(String description){
        this.description = description;
    }
    
    public DescriptiveText(){
        
    }
    
    public boolean checkDescriptiveText(){
        if(this.description.equals("")){
            return false;
        }else{
            return true;
        }
    }

    @Override
    public String toString() {
        return description; //To change body of generated methods, choose Tools | Templates.
    }
}
