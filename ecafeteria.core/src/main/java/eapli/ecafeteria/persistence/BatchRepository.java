package eapli.ecafeteria.persistence;

import eapli.ecafeteria.domain.kitchen.*;
import eapli.framework.persistence.repositories.*;
import java.util.*;

public interface BatchRepository extends DataRepository<Batch, Long> {

    Optional<Batch> findById(long id);

    List<Batch> findAllBatches(String id);
    
    List<Batch> findAll();
}
