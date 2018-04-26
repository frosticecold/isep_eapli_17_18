/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.backoffice.console.presentation.dishes;

import eapli.ecafeteria.application.dishes.AlergenController;
import eapli.ecafeteria.domain.dishes.Alergen;
import eapli.framework.presentation.console.AbstractListUI;
import eapli.framework.visitor.Visitor;

/**
 *
 * @author Car
 */
public class ListAlergensUI extends AbstractListUI<Alergen>{
    private final AlergenController theController = new AlergenController();
    @Override
    protected Iterable<Alergen> elements() {
        return this.theController.allAlergens();
    }

    @Override
    protected Visitor<Alergen> elementPrinter() {
        return new AlergenPrinter();    
    }

    @Override
    protected String elementName() {
        return "Alergens";
    }

    @Override
    protected String listHeader() {
        return "ALERGENS";
    }
    
}
