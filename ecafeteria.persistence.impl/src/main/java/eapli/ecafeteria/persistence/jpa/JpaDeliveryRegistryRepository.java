package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.booking.Booking;
import eapli.ecafeteria.domain.pos.DeliveryRegistry;
import eapli.ecafeteria.persistence.DeliveryRegistryRepository;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;
import javax.persistence.Query;

/**
 *
 * @author PedroEmanuelCoelho 1131485@isep.ipp.pt
 */
public class JpaDeliveryRegistryRepository extends CafeteriaJpaRepositoryBase<DeliveryRegistry, Long> implements DeliveryRegistryRepository {

    /**
     * Returns a list of all DeliveryRegistry
     *
     * @return
     */
    @Override
    public Iterable<DeliveryRegistry> findAll() {
        String query = "SELECT DeliveryRegistry.*"
                + "FROM DeliveryRegistry registry";

        final Query q = entityManager().createQuery(query, this.entityClass);

        return q.getResultList();
    }

    /**
     * Find just one DeliveryRegistry by id
     *
     * @param id
     * @return
     */
    @Override
    public Optional<DeliveryRegistry> findOne(Long id) {
        return matchOne("e.DELIVERY=:id", id, id);
    }

    /**
     * Finds all
     */
    /**
     * Counts DeliveryRegistry records
     *
     * @return
     */
    @Override
    public long count() {

        String query = "SELECT COUNT(e) FROM DeliveryRegistry e";

        final Query q = entityManager().createQuery(query, this.entityClass);

        return (long) q.getSingleResult();
    }

    /**
     * Returns all deliveredMeals of a certain date
     * @param date
     * @return 
     */
    @Override
    public List<DeliveryRegistry> deliveredMealsByDate(Calendar date) {
        
        String query = "SELECT e FROM DeliveryRegistry e"
                + " WHERE e.dateMade = :date";
        
        final Query q = entityManager().createQuery(query, this.entityClass);
        
        q.setParameter("date", date);
        
        return q.getResultList();
    }

    /**
     * Returns a single delivery registry of a certain booking
     * @param booking
     * @return 
     */
    @Override
    public Optional<DeliveryRegistry> deliveredMealByBooking(Booking booking) {
        return matchOne("e.DELIVERY=:booking", booking, booking);
    }

}
