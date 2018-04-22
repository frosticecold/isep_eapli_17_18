package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.kitchen.*;
import eapli.ecafeteria.persistence.*;
import java.util.*;

public class JpaBatchRepository extends CafeteriaJpaRepositoryBase<Batch, Long> implements BatchRepository {

    /**
     * Searches for occurrence of a batch
     *
     * @param id : Batch ID
     * @return
     */
    @Override
    public Optional<Batch> findById(long id) {
        return matchOne(String.valueOf(id));
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

    @Override
    public List<Batch> findAll() {
        return entityManager().createQuery("SELECT batch.*  "
                + "FROM Batch batch ")
                .getResultList();
    }
}
