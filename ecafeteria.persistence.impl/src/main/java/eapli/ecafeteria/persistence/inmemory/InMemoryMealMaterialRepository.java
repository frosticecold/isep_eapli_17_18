package eapli.ecafeteria.persistence.inmemory;

import eapli.ecafeteria.domain.kitchen.MealMaterial;
import eapli.ecafeteria.persistence.MealMaterialRepository;
import eapli.framework.persistence.repositories.impl.inmemory.InMemoryRepository;
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
}
