package eapli.ecafeteria.persistence;

import eapli.ecafeteria.domain.kitchen.Batch;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.persistence.repositories.DataRepository;
import java.util.List;
import java.util.Optional;

public interface BatchRepository extends DataRepository<Batch, String> {

    Optional<Batch> findById(String id);

    List<Batch> findAllBatches(String id);

    void removeUsedBatch(Batch calendar) throws DataIntegrityViolationException;
}
