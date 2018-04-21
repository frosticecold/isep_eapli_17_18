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
    List<Meal> mealList;

    public void registerBatchUsedInMeal(long batchCode) {

    }

    public Optional<Batch> getBatch(long id) {
        return batchRepository.findById(id);
    }

    public void setMeal() {
        long code = Console.readLong("Insert meal code:");

        for (Meal m : mealList) {
            if (m.id() == code) {
                meal = m;
                System.out.println(meal.toString());
                break;
            }
        }
    }

    public void showMeals(String mealT, Date date) {
        MealType mealType = MealType.valueOf(mealT);

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        mealList = mealRepository.listOfMealsByDateAndMealType(cal, mealType);

        System.out.println(mealList.size());
        if (mealList.size() != 0) {
            for (Meal m : mealList) {
                System.out.printf("Meal -> Type:%s,  Dish Name:%s, Code: %d\n", m.mealtype(), m.dish().name(), m.id());
            }
        } else {
            System.out.printf("The are no meals available on: %s!", date.toString());
        }
    }

    public void showMaterial() {
//        List<Material> list = (List<Material>) materialRepository.findByAcronym("");
//
//        for (Material m : list) {
//            System.out.printf("Material: %s, Acronym: %s\n", m.description(), m.id());
//        }
    }

    public void showAvailableBatches(String materialId) {
        List<Batch> list = batchRepository.findAllBatches(materialId);

        for (Batch b : list) {
            System.out.printf("Batch: %d, Use By Date: %s, Material: %s\n", b.barcode(), b.useByDate().toString(), b.material().description());
        }
    }
}
