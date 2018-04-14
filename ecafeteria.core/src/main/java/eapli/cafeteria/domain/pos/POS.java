/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.cafeteria.domain.pos;

import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.framework.domain.ddd.AggregateRoot;
import java.io.Serializable;
import javax.persistence.*;

/**
 *
 * @author PedroEmanuelCoelho 1131485@isep.ipp.pt
 */

//Entity that represents a session start in a point of sale

@Entity
public class POS implements AggregateRoot<Long>, Serializable{

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="IDPOS")
    private long idPOS; 
    
    @OneToMany
    @JoinColumn(name="IDUSER")
    private CafeteriaUser posUser;

    protected POS () {
        //for ORM only
    }
    
    public POS (CafeteriaUser posUser) {
        
        this.posUser = posUser;
    }

    /**
     * Compares this object with another
     * @param other
     * @return 
     */
    @Override
    public boolean sameAs(Object other) {
        
        boolean finalResult = false;
        
        if(other.getClass() == this.getClass()) { //check if "other" is a POS entity aswell
           //checks if it has same ID (only atribute of entity that will be unique)
           POS p = (POS)other;
           if(p.id() == this.id()) {
               finalResult = true;
           }
        }
        
        return finalResult;
    }

    /**
     * Returns the id of POS
     * @return 
     */
    @Override
    public Long id() {
        return this.idPOS;
    }
    
    /**
     * 
     * @return 
     */
    @Override
    public String toString(){ 
        return "POS id :" + idPOS;
    }
 }
