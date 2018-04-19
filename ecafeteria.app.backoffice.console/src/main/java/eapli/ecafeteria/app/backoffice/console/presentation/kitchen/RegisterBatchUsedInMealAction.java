package eapli.ecafeteria.app.backoffice.console.presentation.kitchen;

import eapli.framework.actions.*;

public class RegisterBatchUsedInMealAction implements Action {
    @Override
    public boolean execute() {
        return new RegisterBatchUsedInMealUI().show();
    }
}
