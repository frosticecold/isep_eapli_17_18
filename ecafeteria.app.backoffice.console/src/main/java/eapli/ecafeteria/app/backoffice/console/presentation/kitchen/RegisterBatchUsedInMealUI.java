package eapli.ecafeteria.app.backoffice.console.presentation.kitchen;

import eapli.ecafeteria.application.kitchen.*;
import eapli.framework.application.*;
import eapli.framework.presentation.console.*;
import eapli.framework.util.*;
import java.util.*;
import java.util.logging.*;

public class RegisterBatchUsedInMealUI extends AbstractUI {

    private final RegisterBatchUsedInMealController theController = new RegisterBatchUsedInMealController();

    protected Controller controller() {
        return this.theController;
    }

    @Override
    protected boolean doShow() {
        final String mealT = Console.readLine("Insert meal (Lunch or Dinner):");
        final Date date = Console.readDate("Insert meal date:", "DD/mm/YYYY");

        try {
            this.theController.showMeals(mealT, date);
            this.theController.setMeal();
            int op = -1;
            while (op != 0) {
                System.out.println("Material List:\n");
                this.theController.showMaterial();
                String matAcro = Console.readLine("Input material acronym:");

                this.theController.showAvailableBatch(matAcro);
                Long batchId = Console.readLong("Input batch id:");
            }


//            this.theController.registerBatchUsedInMeal(batchCode);
        } catch (Exception e) {
            Logger.getLogger("Kitchen").log(Level.WARNING, String.valueOf(e.getStackTrace()));
        }

        return false;
    }

    @Override
    public String headline() {
        return "Register Batch Used in Meal";
    }
}
