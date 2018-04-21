package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.kitchen.*;
import eapli.ecafeteria.persistence.*;
import java.util.*;

public class JpaBatchRepository extends CafeteriaJpaRepositoryBase<Batch, String> implements BatchRepository {

    /**
     * Searches for occurrence of a batch
     *
     * @param id : Batch ID
     * @return
     */
    @Override
    public Optional<Batch> findById(String id) {
        return matchOne(id);
    }

    /**
     * Searches for all batches with id
     *
     * @param id : Material ID
     * @return
     */
    @Override
    public List<Batch> findAllBatches(String id) {
        return match(id);
    }
}
