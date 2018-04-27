package eapli.ecafeteria.persistence;

import eapli.ecafeteria.domain.authz.SystemUser;
import eapli.ecafeteria.domain.pos.DeliveryMealSession;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.persistence.repositories.DataRepository;
import java.util.Optional;

/**
 *
 * @author PedroEmanuelCoelho 1131485@isep.ipp.pt
 */
public interface DeliveryMealSessionRepository extends DataRepository<DeliveryMealSession, Long> {
    
    public Optional<DeliveryMealSession> findYourSession(SystemUser cashier);

    public Iterable<DeliveryMealSession> findAllActiveDeliverySessions();
    
    public Iterable<DeliveryMealSession> findAll();
}
