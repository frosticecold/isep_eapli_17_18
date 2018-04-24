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

    @Override
    public void delete(DeliveryRegistry entity) throws DataIntegrityViolationException {
        
        entityManager().remove(entity);
    }

    @Override
    public void delete(Long entityId) throws DataIntegrityViolationException {
        
        String query = "SELECT DeliveryRegistry.*"
                    + "FROM DeliveryRegistry"
                    + "WHERE e.id = id";

        final Query q = entityManager().createQuery(query,this.entityClass);

        q.setParameter("id",entityId);

        DeliveryRegistry entity = (DeliveryRegistry) q.getSingleResult();

        entityManager().remove(entity);
    }

    @Override
    public DeliveryRegistry save(DeliveryRegistry entity) throws DataConcurrencyException, DataIntegrityViolationException {
       
       entityManager().persist(entity);

       return entity;
    }

    @Override
    public Iterable<DeliveryRegistry> findAll() {
       String query = "SELECT DeliveryRegistry.*"
                        + "FROM DeliveryRegistry registry";

        final Query q = entityManager().createQuery(query,this.entityClass);

        return q.getResultList();
    }

    @Override
    public Optional<DeliveryRegistry> findOne(Long id) {
        return matchOne("e.id=id",id,id);
    }

    @Override
    public long count() {
       
        return 0;
    }
    
}
