package eapli.ecafeteria.persistence.inmemory;

import eapli.ecafeteria.domain.kitchen.Batch;
import eapli.ecafeteria.domain.kitchen.MealMaterial;
import eapli.ecafeteria.domain.meal.Meal;
import eapli.ecafeteria.persistence.MealMaterialRepository;
import eapli.framework.persistence.repositories.impl.inmemory.InMemoryRepository;
import java.util.List;
import java.util.Optional;

public class InMemoryMealMaterialRepository extends InMemoryRepository<MealMaterial, Long> implements MealMaterialRepository {

    @Override
    public void delete(Long entityId) {
        throw new UnsupportedOperationException();
    }

    @Override
    protected Long newKeyFor(MealMaterial entity) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<MealMaterial> findOne(Long id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Meal> getMealsByBatchID(Batch b) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<MealMaterial> getMealMaterialFromMeal(Meal m) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Batch getBatchFromMealMaterial(MealMaterial mm) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
