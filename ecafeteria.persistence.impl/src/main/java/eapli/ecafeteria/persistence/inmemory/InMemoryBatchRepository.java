package eapli.ecafeteria.persistence.inmemory;

import eapli.ecafeteria.domain.kitchen.*;
import eapli.ecafeteria.persistence.*;
import eapli.framework.persistence.repositories.impl.inmemory.*;
import java.util.*;

public class InMemoryBatchRepository extends InMemoryRepository<Batch, String> implements BatchRepository {

    /**
     * Searches for occurrence of a batch
     *
     * @param id : Batch ID
     * @return
     */
    @Override
    public Optional<Batch> findById(String id) {
        return matchOne(e -> e.barcode() == (id));
    }

    /**
     * Searches for all batches with id
     *
     * @param id : Material ID
     * @return
     */
    @Override
    public List<Batch> findAllBatches(String id) {
        return (List<Batch>) match(e -> Objects.equals(e.material().id(), id));
    }

    @Override
    protected String newKeyFor(Batch entity) {
        return null;
    }
}
