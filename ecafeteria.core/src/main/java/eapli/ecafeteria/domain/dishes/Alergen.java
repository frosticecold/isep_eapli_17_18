/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.dishes;

import eapli.framework.domain.Designation;
import eapli.framework.domain.ddd.AggregateRoot;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Car
 */
@Entity
public class Alergen  implements AggregateRoot<Long>, Serializable  {

    @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    private Designation name;
    
    
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
        return "Alergen{" + "name=" + name + '}';
    }

    @Override
    public boolean sameAs(Object other) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public Long id() {
        return id;
    }
    
}
