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

    public MechanographicStrategy getInstance(String userType) {
        if (userType.equalsIgnoreCase("Employee")) {
            return new MechanographicEmployeeStrategy();
        }
        if (userType.equalsIgnoreCase("Student")) {
            return new MechanographicEmployeeStrategy();
        } else {
            return null;
        }
    }

}
