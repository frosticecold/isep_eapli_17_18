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

    public void registerBatchUsedInMeal(Batch batch) {
        materialRepository.findByAcronym(batch.material().id());
        MealMaterial m = new MealMaterial(batch.material(), meal, batch);

        try {
            mealMaterialRepository.save(m);
            batchRepository.removeUsedBatch(batch);
        } catch (DataConcurrencyException | DataIntegrityViolationException e) {
            e.printStackTrace();
        }
    }

    public void setMeal() {
        long code = Console.readLong("Insert dish code:");

        if (code == -1) {
            meal = null;
            return;
        }

        for (Meal m : mealList) {
            if (m.id() == code) {
                meal = m;
                break;
            }
        }
    }

    public void showMeals(String mealT, Calendar date) {
        MealType mealType = MealType.valueOf(mealT);

        mealList = mealRepository.listOfMealsByDateAndMealType(date, mealType);

        if (mealList.size() != 0) {
            for (Meal m : mealList) {
                System.out.printf("Meal -> Dish Name:%s, Code: %d\n", m.dish().name(), m.id());
            }
        } else {
            System.out.printf("The are no meals available on: %s!", date.getTime().toString());
        }
    }

    public void showMaterial() {
        List<Material> list = (List<Material>) materialRepository.findAll();

        for (Material m : list) {
            System.out.printf("%s, Acronym: %s\n", m.description(), m.id());
        }
    }

    public void showAvailableBatches(String materialId) {
        Iterable<Batch> list = batchRepository.findAll();
        for (Batch b : list) {
            if (b.material().id().compareTo(materialId) == 0 && b.isAvailable()) {
                System.out.printf("Id: %d Batch: %s, Expiration: %s\n", b.pk(), b.barcode(), b.useByDate().getTime().toString());
            }
        }
    }

    public Batch getBatchSelected(int id) {
        List<Batch> list = (List<Batch>) batchRepository.findAll();
        for (Batch b : list) {
            if ((int) b.pk() == id) {
                return b;
            }
        }
        return null;
    }
}
