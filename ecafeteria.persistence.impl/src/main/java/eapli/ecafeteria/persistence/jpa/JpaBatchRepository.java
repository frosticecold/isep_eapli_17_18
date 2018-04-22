package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.kitchen.Batch;
import eapli.ecafeteria.persistence.BatchRepository;
import java.util.List;
import java.util.Optional;

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

    @Override
<<<<<<< HEAD
    public List<Batch> findAll() {
        return entityManager().createQuery("SELECT batch.*  "
                + "FROM Batch batch ")
                .getResultList();
=======
    public void removeUsedBatch(Batch batch, double quantity) throws Exception {
        Batch b = entityManager().find(Batch.class, batch.pk());
        entityManager().getTransaction().begin();
        b.updatePercentageUsed(quantity);
        entityManager().getTransaction().commit();
>>>>>>> 039b656c242af05bfe91da3e37d027d1683f064d
    }
}
