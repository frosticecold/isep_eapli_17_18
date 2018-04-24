package eapli.ecafeteria.app.backoffice.console.presentation.kitchen;

import eapli.framework.actions.Action;

/**
 *
 * @author JoaoMagalhaes
 */
public class EndShiftAction implements Action {

    @Override
    public boolean execute() {
        return new EndShiftUI().show();
    }

}
