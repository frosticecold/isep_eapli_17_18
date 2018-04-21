package eapli.ecafeteria.persistence;

import eapli.ecafeteria.domain.kitchen.*;
import eapli.framework.persistence.repositories.*;
import java.util.*;

public interface BatchRepository extends DataRepository<Batch, String> {

    Optional<Batch> findById(String id);

    List<Batch> findAllBatches(String id);
}
