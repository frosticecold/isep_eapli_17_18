/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.backoffice.console.presentation.kitchen.reporting;

import eapli.framework.actions.Action;

/**
 *
 * @author simao
 */
public class ListBatchesByMealAction implements Action {
    
    @Override
    public boolean execute() {
        return new ListBatchesByMealUI().show();
    }

}
