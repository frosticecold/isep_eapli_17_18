package eapli.ecafeteria.persistence;

import eapli.ecafeteria.domain.pos.DeliveryMealSession;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.persistence.repositories.DataRepository;

/**
 *
 * @author PedroEmanuelCoelho 1131485@isep.ipp.pt
 */
public interface DeliveryMealSessionRepository extends DataRepository<DeliveryMealSession, Long> {
    
    public DeliveryMealSession save(DeliveryMealSession entity) throws DataConcurrencyException, DataIntegrityViolationException;

    public Iterable<DeliveryMealSession> findAllActiveDeliverySessions();
}
