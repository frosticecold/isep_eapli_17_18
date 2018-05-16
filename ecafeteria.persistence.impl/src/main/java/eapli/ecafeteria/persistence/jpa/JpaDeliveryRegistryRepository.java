package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.pos.DeliveryRegistry;
import eapli.ecafeteria.persistence.DeliveryRegistryRepository;
import java.util.Optional;
import javax.persistence.Query;

/**
 *
 * @author PedroEmanuelCoelho 1131485@isep.ipp.pt
 */
public class JpaDeliveryRegistryRepository extends CafeteriaJpaRepositoryBase<DeliveryRegistry, Long> implements DeliveryRegistryRepository{
   
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
