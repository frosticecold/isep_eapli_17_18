package eapli.ecafeteria.app.backoffice.console.presentation.kitchen;

import eapli.ecafeteria.application.kitchen.RegisterBatchUsedInMealController;
import eapli.ecafeteria.domain.kitchen.Batch;
import eapli.ecafeteria.domain.meal.MealType;
import eapli.framework.application.Controller;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.util.Console;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class RegisterBatchUsedInMealUI extends AbstractUI {

    private final RegisterBatchUsedInMealController theController = new RegisterBatchUsedInMealController();

    protected Controller controller() {
        return this.theController;
    }

    private List<Batch> batches = new ArrayList<>();

    @Override

    protected boolean doShow() {
        final String mealT;
        int mT = Console.readInteger("Insert meal type (LUNCH - 0 or DINNER - 1):");
        mealT = MealType.getMealTypeById(mT).name();
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
            int batchId = Console.readInteger("Input batch id:");
            Batch b = this.theController.getBatchSelected(batchId);
            batches.add(b);
            this.theController.registerBatchUsedInMeal(b);
            System.out.println("Batch successfully saved!\n");
        }

        System.out.println("--- BATCHES REGISTERED ON MEAL ---");
        for (Batch b : batches) {
            System.out.printf("Material: %s, Batch Code: %s, Expiration Date: %s\n", b.material().description(), b.barcode(), b.useByDate().getTime());
        }

        return false;
    }

    @Override
    public String headline() {
        return "Register Batch Used in Meal";
    }
}
