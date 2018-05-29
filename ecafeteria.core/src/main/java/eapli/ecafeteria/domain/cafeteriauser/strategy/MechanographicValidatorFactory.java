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
public class MechanographicValidatorFactory {
    
    private static MechanographicValidatorFactory factory = null;
    
    private MechanographicValidatorFactory(){};
    
    public static MechanographicValidatorFactory instance() {
        if (factory == null) factory = new MechanographicValidatorFactory();
        return factory;
    }

    /**
     * Method to "decide" the according strategy for the usertype
     * 
     * @param userType the usertype
     * @return the strategy for the usertype
     */
    public MechanographicStrategy getInstance(String userType) {
        if (userType.equalsIgnoreCase("Employee")) {
            return new MechanographicEmployeeStrategy();
        }
        if (userType.equalsIgnoreCase("Student")) {
            return new MechanographicStudentStrategy();
        } else {
            return null;
        }
    }

}
