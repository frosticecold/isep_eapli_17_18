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
public class MechanographicEmployeeStrategy implements MechanographicStrategy {

    /**
     * Pattern: sigla do funcionário (4 letras)
     * 
     * @param mechanographicNumber mechanographic number
     * @return true if matches, false if not
     */
    @Override
    public boolean isValid(String mechanographicNumber) {
        String pattern = "[A-Z]{4}";
        return mechanographicNumber.matches(pattern);
    }
    
}
