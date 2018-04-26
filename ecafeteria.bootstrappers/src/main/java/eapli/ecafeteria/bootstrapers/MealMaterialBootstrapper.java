package eapli.ecafeteria.bootstrapers;

import eapli.ecafeteria.domain.kitchen.Batch;
import eapli.ecafeteria.domain.kitchen.MealMaterial;
import eapli.ecafeteria.domain.meal.Meal;
import eapli.ecafeteria.persistence.BatchRepository;
import eapli.ecafeteria.persistence.MealMaterialRepository;
import eapli.ecafeteria.persistence.MealRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.actions.Action;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;

public class MealMaterialBootstrapper implements Action {
    @Override
    public boolean execute() {

        MealRepository mealRepository = PersistenceContext.repositories().meals();
        BatchRepository batchRepository = PersistenceContext.repositories().batch();
        MealMaterialRepository mealMaterialRepository = PersistenceContext.repositories().mealMaterial();

        Batch b1 = batchRepository.findById("5601252106554").get();
        Meal m1 = mealRepository.findOne((long) 27).get();

        MealMaterial mm = new MealMaterial(b1.material(), m1, b1, 5);

        try {
            mealMaterialRepository.save(mm);
        } catch (DataConcurrencyException e) {
            e.printStackTrace();
        } catch (DataIntegrityViolationException e) {
            e.printStackTrace();
        }

        return true;
    }
}
