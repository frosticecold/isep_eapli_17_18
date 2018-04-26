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

    /**
     * Deletes a certain DeliveryMealSession from persistence
     * @param entity
     * @throws DataIntegrityViolationException 
     */
    @Override
    public void delete(DeliveryMealSession entity) throws DataIntegrityViolationException {
        
        entityManager().remove(entity); 
    }

    /**
     * Deletes a DeliveryMealSession from persistence that has a certain ID
     * @param entityId
     * @throws DataIntegrityViolationException 
     */
    @Override
    public void delete(Long entityId) throws DataIntegrityViolationException {
        
        String query = "SELECT DeliveryMealSession.*"
                    + "FROM DeliverMealSession"
                    + "WHERE e.IDDELIVERYEALSESSION = id";
                     
        final Query q = entityManager().createQuery(query, this.entityClass);
        
        q.setParameter("id",entityId);
        
        DeliveryMealSession entity = (DeliveryMealSession) q.getSingleResult();
        
        entityManager().remove(entity);
    }

    /**
     * Persists a DeliveryMealSession on persistence
     * @param entity
     * @return
     * @throws DataConcurrencyException
     * @throws DataIntegrityViolationException 
     */
    @Override
    public DeliveryMealSession save(DeliveryMealSession entity) throws DataConcurrencyException, DataIntegrityViolationException {
        
        entityManager().persist(entity);
        
        return entity;
    }

    /**
     * Returns a List of all registered DeliveryMealSession
     * @return 
     */
    @Override
    public Iterable<DeliveryMealSession> findAll() {
        String query = "SELECT DeliveryMealSession.*"
                        + "FROM DeliveryMealSession delivery";
        
        final Query q = entityManager().createQuery(query, this.entityClass);
        
        return q.getResultList();
    }

    /**
     * Find one DeliveryMealSession by using the id
     * @param id
     * @return 
     */
    @Override
    public Optional<DeliveryMealSession> findOne(Long id) {
        return matchOne("e.IDDELIVERYEALSESSION=id","id",id);
    }

    /**
     * Count All DeliveryMealSession
     * @return 
     */
    @Override
    public long count() {
        return 0;
    }
    
    /**
     * Returns a List of all DeliveryRegistrys of a certain session
     * @param sessionID
     * @return 
     */
    public Iterable<DeliveryMealSession> findAllOfSession(Long sessionID) {
        
        String query = "SELECT DeliveryRegistry.*"
                    + "FROM DeliveryRegistry dr"
                    + "WHERE dr.IDDELIVERYEALSESSION = sessionid"
                    + "ORDER BY DELIVERY ASC";
        
        final Query q = entityManager().createQuery(query, this.entityClass);
        
        q.setParameter("sessionid", sessionID);
        
        return q.getResultList();
    }     
}
