package eapli.ecafeteria.application.kitchen;

import eapli.ecafeteria.domain.kitchen.Batch;
import eapli.ecafeteria.domain.kitchen.Material;
import eapli.ecafeteria.domain.kitchen.MealMaterial;
import eapli.ecafeteria.domain.meal.Meal;
import eapli.ecafeteria.domain.meal.MealType;
import eapli.ecafeteria.persistence.*;
import eapli.framework.application.Controller;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.util.Console;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

public class RegisterBatchUsedInMealController implements Controller {

    MealRepository mealRepository = Objects.requireNonNull(PersistenceContext.repositories()).meals();
    MaterialRepository materialRepository = Objects.requireNonNull(PersistenceContext.repositories()).materials();
    BatchRepository batchRepository = Objects.requireNonNull(PersistenceContext.repositories()).batch();
    MealMaterialRepository mealMaterialRepository = PersistenceContext.repositories().mealMaterial();

    private Meal meal;
    private List<Meal> mealList;

    public Meal meal() {
        return this.meal;
    }

    public void registerBatchUsedInMeal(Batch batch, double quantity) throws Exception {
        materialRepository.findByAcronym(batch.material().id());
        MealMaterial m = new MealMaterial(batch.material(), meal, batch, quantity);

        try {
            if (quantity > 0) {
                mealMaterialRepository.save(m);
                batchRepository.removeUsedBatch(batch, quantity);
            }
        } catch (DataConcurrencyException | DataIntegrityViolationException e) {
            e.getCause();
        }
    }

    public int setMeal() {
        long code = Console.readLong("Insert dish code:");

        if (code == -1) {
            meal = null;
            return -1;
        }

        for (Meal m : mealList) {
            if (m.id() == code) {
                meal = m;
                return 0;
            }
        }
        return -1;
    }

    public int showMeals(String mealT, Calendar date) {
        MealType mealType = MealType.valueOf(mealT);

        mealList = mealRepository.listOfMealsByDateAndMealType(date, mealType);

        if (mealList.size() != 0) {
            for (Meal m : mealList) {
                System.out.printf("Meal -> Dish Name:%s, Code: %d\n", m.dish().name(), m.id());
            }
        } else {
            System.out.printf("The are no meals available on: %s!\n", date.getTime().toString());
            return -1;
        }
        return 0;
    }

    public void showMaterial() {
        List<Material> list = (List<Material>) materialRepository.findAll();

        for (Material m : list) {
            System.out.printf("%s, Acronym: %s\n", m.description(), m.id());
        }
    }

    public void showAvailableBatches(String materialId) {
        Calendar today = Calendar.getInstance();


        Iterable<Batch> list = batchRepository.findAll();
        for (Batch b : list) {
            if (b.material().id().compareTo(materialId) == 0 && b.isAvailable() && b.useByDate().compareTo(today) > 0) {
                System.out.printf("Id: %d Batch: %s, Quantity Available: %f Expiration: %s\n",
                        b.pk(), b.barcode(), b.availableQuantity(), b.useByDate().getTime().toString());
            }
        }
    }

    public Batch getSelectedBatch(int id) {
        List<Batch> list = batchRepository.findAll();
        for (Batch b : list) {
            if ((int) b.pk() == id) {
                return b;
            }
        }
        return null;
    }
}
