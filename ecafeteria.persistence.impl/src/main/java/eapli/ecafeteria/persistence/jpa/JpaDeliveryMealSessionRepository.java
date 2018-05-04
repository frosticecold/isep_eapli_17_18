package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.authz.SystemUser;
import eapli.ecafeteria.domain.pos.DeliveryMealSession;
import eapli.ecafeteria.persistence.DeliveryMealSessionRepository;
import java.util.Optional;
import javax.persistence.Query;

/**
 *
 * @author PedroEmanuelCoelho 1131485@isep.ipp.pt
 */
public class JpaDeliveryMealSessionRepository extends CafeteriaJpaRepositoryBase<DeliveryMealSession, Long> implements DeliveryMealSessionRepository {
    
     /**
     * Returns a List of all registered DeliveryMealSession
     * @return 
     */
    @Override
    public Iterable<DeliveryMealSession> findAll() {
        String query = "SELECT delivery "
                        + "FROM DeliveryMealSession delivery";

        final Query q = entityManager().createQuery(query, this.entityClass);

        return q.getResultList();
    }
    
    /**
     * Returns a List of all registered DeliveryMealSession that are active
     * @return 
     */
    @Override
    public Iterable<DeliveryMealSession> findAllActiveDeliverySessions() {
        String query = "SELECT delivery "
                        + "FROM DeliveryMealSession delivery"
                        + "WHERE ACTIVE=TRUE";

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
        
        String query = "SELECT e FROM DeliveryMealSession e "
                + "WHERE e.cashier=:cashier";
        
        final Query q = entityManager().createQuery(query, this.entityClass);
        
        q.setParameter("cashier",cashier);

        return q.getResultList().stream().findFirst();
    }
}
