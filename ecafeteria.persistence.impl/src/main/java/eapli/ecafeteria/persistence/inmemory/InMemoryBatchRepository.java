package eapli.ecafeteria.persistence.inmemory;

import eapli.ecafeteria.domain.kitchen.Batch;
import eapli.ecafeteria.persistence.BatchRepository;
import eapli.framework.persistence.repositories.impl.inmemory.InMemoryRepository;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

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
    public void removeUsedBatch(Batch calendar, double quantity) {

    }

    @Override
    protected String newKeyFor(Batch entity) {
        return null;
    }

    @Override
    public List<Batch> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
