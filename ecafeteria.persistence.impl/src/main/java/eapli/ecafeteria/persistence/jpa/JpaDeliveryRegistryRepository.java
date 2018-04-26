package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.pos.DeliveryRegistry;
import eapli.ecafeteria.persistence.DeliveryRegistryRepository;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.Optional;
import javax.persistence.Query;

/**
 *
 * @author PedroEmanuelCoelho 1131485@isep.ipp.pt
 */
public class JpaDeliveryRegistryRepository extends CafeteriaJpaRepositoryBase<DeliveryRegistry, Long> implements DeliveryRegistryRepository{

    /**
     * Delete certain entity of DeliveryRegistry
     * @param entity
     * @throws DataIntegrityViolationException 
     */
    @Override
    public void delete(DeliveryRegistry entity) throws DataIntegrityViolationException {
        
        entityManager().remove(entity);
    }

    /**
     * Delete DeliveryRegistry by id
     * @param entityId
     * @throws DataIntegrityViolationException 
     */
    @Override
    public void delete(Long entityId) throws DataIntegrityViolationException {
        
        String query = "SELECT DeliveryRegistry.*"
                    + "FROM DeliveryRegistry"
                    + "WHERE e.DELIVERY = id";

        final Query q = entityManager().createQuery(query,this.entityClass);

        q.setParameter("id",entityId);

        DeliveryRegistry entity = (DeliveryRegistry) q.getSingleResult();

        entityManager().remove(entity);
    }

    /**
     * Persists a deliveryregistry entity
     * @param entity
     * @return
     * @throws DataConcurrencyException
     * @throws DataIntegrityViolationException 
     */
    @Override
    public DeliveryRegistry save(DeliveryRegistry entity) throws DataConcurrencyException, DataIntegrityViolationException {
       
       entityManager().persist(entity);

       return entity;
    }

    /**
     * Returns a list of all DeliveryRegistry
     * @return 
     */
    @Override
    public Iterable<DeliveryRegistry> findAll() {
       String query = "SELECT DeliveryRegistry.*"
                        + "FROM DeliveryRegistry registry";

        final Query q = entityManager().createQuery(query,this.entityClass);

        return q.getResultList();
    }

    /**
     * Find just one DeliveryRegistry by id
     * @param id
     * @return 
     */
    @Override
    public Optional<DeliveryRegistry> findOne(Long id) {
        return matchOne("e.DELIVERY=id",id,id);
    }

    /**
     * Counts DeliveryRegistry records
     * @return 
     */
    @Override
    public long count() {
       
        return 0;
    }
    
}
