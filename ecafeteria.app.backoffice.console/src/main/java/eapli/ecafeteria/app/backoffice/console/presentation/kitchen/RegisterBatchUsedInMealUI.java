package eapli.ecafeteria.app.backoffice.console.presentation.kitchen;

import eapli.ecafeteria.application.kitchen.*;
import eapli.framework.application.*;
import eapli.framework.presentation.console.*;
import eapli.framework.util.*;
import java.util.*;

public class RegisterBatchUsedInMealUI extends AbstractUI {

    private final RegisterBatchUsedInMealController theController = new RegisterBatchUsedInMealController();

    protected Controller controller() {
        return this.theController;
    }

    @Override
    protected boolean doShow() {
        final String mealT = Console.readLine("Insert meal (LUNCH or DINNER):");
        final Calendar date = Console.readCalendar("Insert date (DD-MM-YYYY):");

        this.theController.showMeals(mealT, date);
        this.theController.setMeal();

        String matAcro;
        while (true) {
            System.out.println("Material List:\n");
            this.theController.showMaterial();
            matAcro = Console.readLine("Input material acronym or exit:");

            if (matAcro.compareTo("exit") == 0) {
                break;
            }

            this.theController.showAvailableBatches(matAcro);
//            Long batchId = Console.readLong("Input batch id:");
        }


//            this.theController.registerBatchUsedInMeal(batchCode);


        return false;
    }

    @Override
    public String headline() {
        return "Register Batch Used in Meal";
    }
}
