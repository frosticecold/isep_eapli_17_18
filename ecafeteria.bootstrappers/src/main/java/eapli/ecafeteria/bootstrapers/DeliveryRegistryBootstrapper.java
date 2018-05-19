package eapli.ecafeteria.bootstrapers;

import eapli.ecafeteria.application.pos.RegisterMealDeliveryController;
import eapli.ecafeteria.domain.booking.Booking;
import eapli.ecafeteria.domain.booking.BookingState;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.domain.cafeteriauser.MecanographicNumber;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.actions.Action;
import java.util.List;

/**
 *
 * @author PedroEmanuelCoelho 1131485@isep.ipp.pt
 */
public class DeliveryRegistryBootstrapper implements Action {

    @Override
    public boolean execute() {
        boolean f = false;

        if (this.registerDeliveryRegistry()) {
            f = true;
        }

        return f;
    }

    private boolean registerDeliveryRegistry() {

        boolean f = false;

        RegisterMealDeliveryController ctrl = new RegisterMealDeliveryController();

        MecanographicNumber mNumber = new MecanographicNumber("900330");

        CafeteriaUser client = PersistenceContext.repositories().cafeteriaUsers().findByMecanographicNumber(mNumber).get();

        BookingState state = new BookingState();

        List<Booking> listB = PersistenceContext.repositories().booking().findBookingsByCafeteriaUser(client, state);

        if (!listB.isEmpty()) {
            Booking b = listB.get(0);

            ctrl.registerNewMealDelivery(mNumber.toString(), b.getIdBooking());
            f = true;
        }
        return f;
    }
}
