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
public class MechanographicStudentStrategy implements MechanographicStrategy {

    @Override
    public boolean isValid(String mechanographicNumber) {
        String pattern = "9\\d\\d{4}|0\\d\\d{4}|1[0-8]\\d{4}";
        return mechanographicNumber.matches(pattern);
    }
    
}
