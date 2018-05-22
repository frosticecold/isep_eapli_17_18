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
    
    private final MechanographicValidatorFactory factory = new MechanographicValidatorFactory();
    
    public boolean isValid(String userType, String mechanographicNumber) {
        MechanographicStrategy strategy = factory.getInstance(userType);
        return strategy.isValid(mechanographicNumber);
    }
}
