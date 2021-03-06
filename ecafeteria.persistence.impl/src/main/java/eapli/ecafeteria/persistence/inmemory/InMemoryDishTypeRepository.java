package eapli.ecafeteria.persistence.inmemory;

import java.util.Optional;

import eapli.ecafeteria.domain.dishes.DishType;
import eapli.ecafeteria.persistence.DishTypeRepository;
import eapli.framework.persistence.repositories.impl.inmemory.InMemoryRepositoryWithLongPK;

/**
 * Created by MCN on 29/03/2016.
 */
public class InMemoryDishTypeRepository extends InMemoryRepositoryWithLongPK<DishType> implements DishTypeRepository {

	@Override
	public Iterable<DishType> activeDishTypes() {
		return match(e -> e.isActive());
	}

	@Override
	public Optional<DishType> findByAcronym(String acronym) {
		return matchOne(e -> e.id().equals(acronym));
	}
}
