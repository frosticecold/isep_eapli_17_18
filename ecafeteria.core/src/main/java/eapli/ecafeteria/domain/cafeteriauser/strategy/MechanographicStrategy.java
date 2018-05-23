/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.cafeteriauser.strategy;

/**
 *
 * @author Rui Ribeiro <1150344@isep.ipp.pt>
 */
public interface MechanographicStrategy {
    
    /**
     * Method to validate a mechanographic number
     * 
     * @param mechanographicNumber mechanographic number
     * @return true if is valid, false if not
     */
    public boolean isValid(String mechanographicNumber);
}
