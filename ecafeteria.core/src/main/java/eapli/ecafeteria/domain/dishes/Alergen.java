/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.dishes;

import eapli.framework.domain.*;
import eapli.framework.domain.ddd.*;
import java.io.*;
import javax.persistence.*;

/**
 *
 * @author Car
 */
@Entity
public class Alergen  implements AggregateRoot<Designation>, Serializable  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @EmbeddedId
    private Designation name;


    public Alergen (Designation name){
        this.name=name;
    }

    protected Alergen() {
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

    @Override
    public Designation id() {
       return this.name;
    }

}
