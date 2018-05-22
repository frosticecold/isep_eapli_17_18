package eapli.ecafeteria.app.backoffice.console.presentation.kitchen;

import eapli.ecafeteria.app.backoffice.console.presentation.Alert.AlertUI;
import eapli.ecafeteria.application.kitchen.RegisterBatchUsedInMealController;
import eapli.ecafeteria.domain.dishes.Dish;
import eapli.ecafeteria.domain.kitchen.Batch;
import eapli.ecafeteria.domain.kitchen.Material;
import eapli.ecafeteria.domain.meal.Meal;
import eapli.ecafeteria.domain.meal.MealType;
import eapli.framework.application.Controller;
import eapli.framework.util.Console;
import java.util.*;
import java.util.Map.Entry;

public class RegisterBatchUsedInMealUI extends AlertUI {

    private final RegisterBatchUsedInMealController theController = new RegisterBatchUsedInMealController();

    protected Controller controller() {
        return this.theController;
    }

    private Map<Batch, Double> batches = new HashMap<>();

    @Override
    protected boolean doShow() {
        final String mealT;
        int mT = Console.readInteger("Insert meal type (LUNCH - 0 or DINNER - 1):");
        mealT = Objects.requireNonNull(MealType.getMealTypeById(mT)).name();

        Calendar date;
        boolean meals;
        do {
            date = Console.readCalendar("Insert date (DD-MM-YYYY):");
            meals = showMeals(mealT, date);
            if (!meals) {
                System.out.println("There are no meals for the specified date!\n");
            }
        } while (!meals);

        List<Meal> mealList = meals(date, MealType.getMealTypeById(mT));
        boolean marker = Console.readBoolean("Are you sure you want to continue? Y or N");
        if (!marker) {
            return false;
        }
        for (Meal meal : mealList) {
            Dish dish = meal.dish();
            System.out.printf("Meal: %d - Dish: %s, Allergen: %s, Dish Type: %s\n", meal.id(), dish.id(), dish.alergenInDish(), dish.dishType());
            this.theController.setMeal(meal);

            String matAcro;
            while (true) {
                System.out.println("Material List:\n");
                showMaterial();

                matAcro = Console.readLine("Input material acronym or end:");

                if (matAcro != null && matAcro.compareTo("end") == 0) {
                    break;
                }
                boolean stat = showAvailableBatches(matAcro);
                if (stat) {
                    int pk = Console.readInteger("Insert id: ");
                    Batch b = this.theController.getSelectedBatch(pk);
                    double quantity = Console.readDouble("Insert quantity used:");
                    try {
                        this.theController.registerBatchUsedInMeal(b, quantity);
                    } catch (Exception e) {
                        e.printStackTrace();
                        e.getCause();
                    }
                    if (quantity > 0) {
                        batches.put(b, quantity);
                        System.out.println("Batch successfully saved!\n");
                    }
                }
            }

            if (batches.size() > 0) {
                System.out.println("--- BATCHES REGISTERED ON MEAL ---");
                for (Entry<Batch, Double> b : batches.entrySet()) {
                    System.out.printf("Material: %s, Batch Code: %s, Used Quantity: %f Expiration Date: %s\n", b.getKey().material().description(), b.getKey().barcode(), b.getValue(), b.getKey().useByDate().getTime());
                }
                System.out.println("\n");
            }
        }
        return false;
    }

    @Override
    public String headline() {
        return super.headline() + "Register Batch Used in Meal";
    }

    private List<Meal> meals(Calendar date, MealType mealType) {
        return theController.mealList(date, mealType);
    }

    private void showMaterial() {
        List<Material> list = theController.materialList();

        for (Material m : list) {
            System.out.printf("%s, Acronym: %s\n", m.description(), m.id());
        }
    }

    private boolean showMeals(String mealT, Calendar date) {
        MealType mealType = MealType.valueOf(mealT);

        List<Meal> mealList = theController.mealList(date, mealType);

        if (mealList.size() != 0) {
            for (Meal m : mealList) {
                System.out.printf("Meal -> Dish Name:%s, Code: %d\n", m.dish().name(), m.id());
            }
        } else {
            System.out.printf("The are no meals available on: %s!\n", date.getTime().toString());
        }
        return mealList.size() != 0;
    }

    private boolean showAvailableBatches(String materialId) {
        Calendar today = Calendar.getInstance();

        List<Batch> list = (List<Batch>) theController.batches();
        int count = 0;
        for (Batch b : list) {
            if (b.material().id().compareTo(materialId) == 0 && b.isAvailable() && b.useByDate().compareTo(today) > 0) {
                System.out.printf("Id: %d Batch: %s, Quantity Available: %f Expiration: %s\n",
                        b.pk(), b.barcode(), b.availableQuantity(), b.useByDate().getTime().toString());
                count++;
            }
        }
        return count != 0;
    }
}
