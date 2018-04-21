package eapli.ecafeteria.application.kitchen;

import eapli.ecafeteria.domain.kitchen.*;
import eapli.ecafeteria.domain.meal.*;
import eapli.ecafeteria.persistence.*;
import eapli.framework.application.*;
import eapli.framework.util.*;
import java.util.*;

public class RegisterBatchUsedInMealController implements Controller {

    MealRepository mealRepository = Objects.requireNonNull(PersistenceContext.repositories()).meals();
    MaterialRepository materialRepository = Objects.requireNonNull(PersistenceContext.repositories()).materials();
    BatchRepository batchRepository = Objects.requireNonNull(PersistenceContext.repositories()).batch();

    private Meal meal;
    private List<Meal> mealList;

    public void registerBatchUsedInMeal(Batch batch) {

    }

    public Optional<Batch> getBatch(String id) {
        return batchRepository.findById(id);
    }

    public void setMeal() {
        long code = Console.readLong("Insert meal code:");

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

        System.out.println(mealList.size());
        if (mealList.size() != 0) {
            for (Meal m : mealList) {
                System.out.printf("Meal -> Dish Name:%s, Code: %d\n", m.dish().name(), m.id());
            }
        } else {
            System.out.printf("The are no meals available on: %s!", date.toString());
        }
    }

    public void showMaterial() {
        List<Material> list = (List<Material>) materialRepository.findAll();

        for (Material m : list) {
            System.out.printf("%s, Acronym: %s\n", m.description(), m.id());
        }
    }

    public void showAvailableBatches(String materialId) {
        int count = 0;
        Iterable<Batch> list = batchRepository.findAll();
        for (Batch b : list) {
            if (b.material().id().compareTo(materialId) == 0) {
                System.out.printf("Id: %d Batch: %s, Use By Date: %s\n", count, b.barcode(), b.useByDate().getTime().toString());
                count++;
            }
        }
    }

    public Batch getBatchSelected(int id) {
        List<Batch> list = (List<Batch>) batchRepository.findAll();
        return list.get(id);
    }
}
