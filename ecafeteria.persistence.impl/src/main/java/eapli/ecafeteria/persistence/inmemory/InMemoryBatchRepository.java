package eapli.ecafeteria.persistence.inmemory;

import eapli.ecafeteria.domain.kitchen.*;
import eapli.ecafeteria.persistence.*;
import eapli.framework.persistence.repositories.impl.inmemory.*;
import java.util.*;

public class InMemoryBatchRepository extends InMemoryRepositoryWithLongPK<Batch> implements BatchRepository {

    /**
     * Searches for occurrence of a batch
     *
     * @param id : Batch ID
     * @return
     */
    @Override
    public Optional<Batch> findById(long id) {
        return matchOne(e -> e.barcode() == (id));
    }

    @Override
    public List<Batch> findAllBatches(String id) {
        return (List<Batch>) match(e -> Objects.equals(e.material().id(), id));
    }

    /**
     * Searches for all batches with id
     *
     * @param id : Material ID
     * @return
     */
    public List<Batch> findAllBatches(long id) {
        return (List<Batch>) match(e -> e.barcode() == (id));
    }
}
