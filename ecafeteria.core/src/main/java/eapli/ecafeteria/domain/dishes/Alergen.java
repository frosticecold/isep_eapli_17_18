/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.dishes;

import eapli.framework.domain.Designation;
import eapli.framework.domain.ddd.AggregateRoot;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

/**
 *
 * @author Car
 */
@Entity
public class Alergen  implements AggregateRoot<Designation>, Serializable  {

    @EmbeddedId
    private Designation name;
    
    protected Alergen(){
        // for ORM only
    }
    
    public Alergen (Designation name){
        this.name=name;
    }

    /**
     * @return the name
     */
    public Designation getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(Designation name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name.toString();
    }

    @Override
    public boolean sameAs(Object other) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Designation id() {
        return name;
    }

    public Object name() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Alergen other = (Alergen) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        return true;
    }
    
    
    
}
