package eapli.ecafeteria.persistence;

import eapli.ecafeteria.domain.kitchen.Batch;
import eapli.framework.persistence.repositories.DataRepository;
import java.util.List;
import java.util.Optional;

public interface BatchRepository extends DataRepository<Batch, String> {

    Optional<Batch> findById(String id);

    Batch findByPk(int id);

    List<Batch> findAllBatches(String id);

    List<Batch> findAll();

    void removeUsedBatch(Batch calendar, double quantity) throws Exception;

}
