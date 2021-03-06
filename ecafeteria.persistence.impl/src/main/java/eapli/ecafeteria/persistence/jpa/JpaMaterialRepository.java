package eapli.ecafeteria.persistence.jpa;

import java.util.Optional;

import eapli.ecafeteria.domain.kitchen.Material;
import eapli.ecafeteria.persistence.MaterialRepository;

/**
 * Created by MCN on 29/03/2016.
 */
class JpaMaterialRepository extends CafeteriaJpaRepositoryBase<Material, Long> implements MaterialRepository {

	@Override
	public Optional<Material> findByAcronym(String acronym) {
		return matchOne("e.acronym=:acronym", "acronym", acronym);
	}
}
