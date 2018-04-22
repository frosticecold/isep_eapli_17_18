package eapli.ecafeteria.persistence;

import eapli.ecafeteria.domain.kitchen.Batch;
import eapli.framework.persistence.repositories.DataRepository;
import java.util.List;
import java.util.Optional;

public interface BatchRepository extends DataRepository<Batch, String> {

    Optional<Batch> findById(String id);

    List<Batch> findAllBatches(String id);
<<<<<<< HEAD
    
    List<Batch> findAll();
=======

    void removeUsedBatch(Batch calendar, double quantity) throws Exception;
>>>>>>> 039b656c242af05bfe91da3e37d027d1683f064d
}
