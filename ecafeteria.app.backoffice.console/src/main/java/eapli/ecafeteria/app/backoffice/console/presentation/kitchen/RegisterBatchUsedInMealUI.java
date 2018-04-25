package eapli.ecafeteria.app.backoffice.console.presentation.kitchen;

import eapli.ecafeteria.application.kitchen.RegisterBatchUsedInMealController;
import eapli.ecafeteria.domain.kitchen.Batch;
import eapli.ecafeteria.domain.meal.MealType;
import eapli.framework.application.Controller;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.util.Console;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class RegisterBatchUsedInMealUI extends AbstractUI {

    private final RegisterBatchUsedInMealController theController = new RegisterBatchUsedInMealController();

    protected Controller controller() {
        return this.theController;
    }

    private Map<Batch, Double> batches = new HashMap<>();

    @Override
    protected boolean doShow() {
        final String mealT;
        int mT = Console.readInteger("Insert meal type (LUNCH - 0 or DINNER - 1):");
        mealT = MealType.getMealTypeById(mT).name();

        Calendar date;
        do {
            date = Console.readCalendar("Insert date (DD-MM-YYYY):");
        } while ((this.theController.showMeals(mealT, date) == -1));

        int state;
        do {
            state = this.theController.setMeal();
        } while (state != 0);

        String matAcro;
        while (true) {
            System.out.println("Material List:\n");
            this.theController.showMaterial();

            matAcro = Console.readLine("Input material acronym or exit:");

            if (matAcro.compareTo("exit") == 0) {
                break;
            }

            this.theController.showAvailableBatches(matAcro);
            int pk = Console.readInteger("Insert id: ");
            Batch b = this.theController.getSelectedBatch(pk);
            double quantity = Console.readDouble("Insert quantity used:");
            try {
                this.theController.registerBatchUsedInMeal(b, quantity);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (quantity > 0) {
                batches.put(b, quantity);
                System.out.println("Batch successfully saved!\n");
            }
        }

        if (batches.size() > 0) {
            System.out.println("--- BATCHES REGISTERED ON MEAL ---");
            for (Entry<Batch, Double> b : batches.entrySet()) {
                System.out.printf("Material: %s, Batch Code: %s, Used Quantity: %f Expiration Date: %s\n", b.getKey().material().description(), b.getKey().barcode(), b.getValue(), b.getKey().useByDate().getTime());
            }
        }

        return false;
    }

    @Override
    public String headline() {
        return "Register Batch Used in Meal";
    }
}
