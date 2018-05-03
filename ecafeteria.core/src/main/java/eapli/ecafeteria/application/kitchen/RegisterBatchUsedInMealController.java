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
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

public class RegisterBatchUsedInMealController implements Controller {

    private MealRepository mealRepository = Objects.requireNonNull(PersistenceContext.repositories()).meals();
    private MaterialRepository materialRepository = Objects.requireNonNull(PersistenceContext.repositories()).materials();
    private BatchRepository batchRepository = Objects.requireNonNull(PersistenceContext.repositories()).batch();
    private MealMaterialRepository mealMaterialRepository = Objects.requireNonNull(PersistenceContext.repositories()).mealMaterial();

    private Meal meal;

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

    public void setMeal(Meal meal) {
        this.meal = meal;
    }

    public List<Material> materialList() {
        return (List<Material>) materialRepository.findAll();
    }

    public Iterable<Batch> batches() {
        return batchRepository.findAll();
    }

    public Batch getSelectedBatch(int id) {
        List<Batch> list = (List<Batch>) batches();
        for (Batch b : list) {
            if (b.pk() == id) {
                return b;
            }
        }
        return null;
    }

    public List<Meal> mealList(Calendar date, MealType mealType) {
        return mealRepository.listOfMealsByDateAndMealType(date, mealType);
    }
}
