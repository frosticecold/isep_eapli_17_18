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
public class MechanographicValidator {
    
    /**
     * Method to verify if, according to a userType, the mechanographic number is valid.
     * 
     * @param userType "Student" or "Employee"
     * @param mechanographicNumber  the mechanographic number
     * @return true if is valid, false if not
     */
    public boolean isValid(String userType, String mechanographicNumber) {
        MechanographicStrategy strategy = MechanographicValidatorFactory.instance().getInstance(userType);
        return strategy.isValid(mechanographicNumber);
    }
}
