/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.backoffice.console.presentation.dishes;

import eapli.ecafeteria.domain.dishes.Alergen;
import eapli.framework.visitor.Visitor;

/**
 *
 * @author Car
 */
public class AlergenPrinter implements Visitor<Alergen>{

    @Override
    public void visit(Alergen visitee) {
        System.out.printf("%-30s", visitee.getName());
    }
    
}
