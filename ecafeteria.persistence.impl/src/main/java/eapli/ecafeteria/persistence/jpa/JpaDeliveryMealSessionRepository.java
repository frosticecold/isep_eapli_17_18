package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.authz.SystemUser;
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
     * Returns a days respective session which is active
     * @param cashier
     * @return 
     */
    @Override
    public Optional<DeliveryMealSession> findYourSession(SystemUser cashier) {

        return matchOne("e.CASHIER=cashier","cashier",cashier);
    }
}
