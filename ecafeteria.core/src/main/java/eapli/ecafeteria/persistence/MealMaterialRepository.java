package eapli.ecafeteria.persistence;

import eapli.ecafeteria.domain.kitchen.Batch;
import eapli.ecafeteria.domain.kitchen.MealMaterial;
import eapli.ecafeteria.domain.meal.Meal;
import eapli.framework.persistence.repositories.DataRepository;
import java.util.List;

public interface MealMaterialRepository extends DataRepository<MealMaterial, Long> {
    
 public List<Meal> getMealsByBatchID(Batch b);
 
 public List<MealMaterial> getMealMaterialFromMeal(Meal m);
 
 public Batch getBatchFromMealMaterial(MealMaterial mm);
}
