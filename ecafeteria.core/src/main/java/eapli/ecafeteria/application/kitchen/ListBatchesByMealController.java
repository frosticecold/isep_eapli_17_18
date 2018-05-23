
package eapli.ecafeteria.application.kitchen;

import eapli.ecafeteria.domain.kitchen.Batch;
import eapli.ecafeteria.domain.kitchen.MealMaterial;
import eapli.ecafeteria.domain.meal.Meal;
import eapli.ecafeteria.persistence.BatchRepository;
import eapli.ecafeteria.persistence.MealMaterialRepository;
import eapli.ecafeteria.persistence.MealRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import java.util.Calendar;
import java.util.List;

public class ListBatchesByMealController {

    private final MealMaterialRepository mealMaterialRepo = PersistenceContext.repositories().mealMaterial();
    private final BatchRepository batchRepo = PersistenceContext.repositories().batch();
    private final MealRepository mealRepo = PersistenceContext.repositories().meals();

    public List<Meal> getMealByDate(Calendar cal) {
        List<Meal> list = this.mealRepo.getMealByDate(cal);
        return list;
    }

        public List<MealMaterial> getMealMaterialFromMeal(Meal m) {
        return mealMaterialRepo.getMealMaterialFromMeal(m);
    }

    public Batch getBatchFromMealMaterial(MealMaterial mm) {
        return mealMaterialRepo.getBatchFromMealMaterial(mm);
    }

}
