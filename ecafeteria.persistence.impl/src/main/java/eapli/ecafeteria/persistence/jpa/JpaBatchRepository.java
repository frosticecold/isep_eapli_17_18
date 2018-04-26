package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.kitchen.Batch;
import eapli.ecafeteria.persistence.BatchRepository;
import java.util.List;
import java.util.Optional;
import javax.persistence.Query;

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

    public List<Batch> findAll() {

        return entityManager().createQuery("SELECT b "
                + "FROM Batch b")
                .getResultList();

    }

    @Override
    public void removeUsedBatch(Batch batch, double quantity) throws Exception {

        Batch b = entityManager().find(Batch.class, batch.pk());
        entityManager().getTransaction().begin();
        b.updatePercentageUsed(quantity);
        entityManager().getTransaction().commit();
    }

    @Override
    public Batch findByPk(int id) {
        final Query q;
        q = entityManager().createQuery("SELECT b FROM Batch b WHERE b.pk =:id ");
        q.setParameter("id", id);
        return (Batch) q.getSingleResult();
    }

}
