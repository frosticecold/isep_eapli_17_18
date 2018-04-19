package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.pos.DeliveryMealSession;
import eapli.ecafeteria.persistence.DeliveryMealSessionRepository;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.Optional;
import javax.persistence.Query;

/**
 *
 * @author PedroEmanuelCoelho 1131485@isep.ipp.pt
 */
public class JpaDeliveryMealSessionRepository extends CafeteriaJpaRepositoryBase<DeliveryMealSession, Long> implements DeliveryMealSessionRepository {
    
    public JpaDeliveryMealSessionRepository() {
        
    }

    @Override
    public void delete(DeliveryMealSession entity) throws DataIntegrityViolationException {
        
        entityManager().remove(entity); 
    }

    @Override
    public void delete(Long entityId) throws DataIntegrityViolationException {
        
        String query = "SELECT DeliveryMealSession.*"
                    + "FROM DeliverMealSession"
                    + "WHERE e.id = id";
                
        final Query q = entityManager().createQuery(query, this.entityClass);
        
        DeliveryMealSession entity = (DeliveryMealSession) q.getSingleResult();
        
        entityManager().remove(entity);
    }

    @Override
    public DeliveryMealSession save(DeliveryMealSession entity) throws DataConcurrencyException, DataIntegrityViolationException {
        
        entityManager().persist(entity);
        
        return entity;
    }

    @Override
    public Iterable<DeliveryMealSession> findAll() {
        String query = "SELECT DeliveryMealSession.*"
                        + "FROM DeliveryMealSession delivery";
        
        final Query q = entityManager().createQuery(query, this.entityClass);
        
        return q.getResultList();
    }

    @Override
    public Optional<DeliveryMealSession> findOne(Long id) {
        return matchOne("e.id=id","id",id);
    }

    @Override
    public long count() {
        return 0;
    }
    
}
