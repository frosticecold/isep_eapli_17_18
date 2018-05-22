package eapli.ecafeteria.persistence;

import eapli.ecafeteria.domain.booking.Booking;
import eapli.ecafeteria.domain.pos.DeliveryRegistry;
import eapli.framework.persistence.repositories.DataRepository;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author PedroEmanuelCoelho 1131485@isep.ipp.pt
 */
public interface DeliveryRegistryRepository extends DataRepository<DeliveryRegistry, Long> {

    /**
     * method that returns a list of all delivered meals of a certain date
     *
     * @param date
     * @return
     */
    public List<DeliveryRegistry> deliveredMealsByDate(Calendar date);
    
    /**
     * method that returns a Optional of a delivered meal search by booking id
     */
    public List<DeliveryRegistry> deliveredMealByBooking(Booking booking);
}
