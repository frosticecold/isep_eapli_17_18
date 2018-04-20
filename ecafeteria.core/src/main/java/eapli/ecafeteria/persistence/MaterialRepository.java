package eapli.ecafeteria.persistence;

import eapli.ecafeteria.domain.kitchen.*;
import eapli.framework.persistence.repositories.*;
import java.util.*;

/**
 * the repository for Materials.
 *
 */
public interface MaterialRepository extends DataRepository<Material, Long> {

	Optional<Material> findByAcronym(String acronym);
}
