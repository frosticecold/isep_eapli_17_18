/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.pos;

import eapli.framework.domain.ddd.ValueObject;

/**
 *
 * @author Telmo
 */
public class DescriptiveText implements ValueObject{
    
    private String description;
    
    public DescriptiveText(String description){
        this.description = description;
    }
    
    public boolean checkDescriptiveText(){
        if(this.description.equals("")){
            return true;
        }else{
            return false;
        }
    }
}
