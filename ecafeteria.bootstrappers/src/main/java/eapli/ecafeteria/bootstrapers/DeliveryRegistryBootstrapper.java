package eapli.ecafeteria.bootstrapers;

import eapli.ecafeteria.application.pos.RegisterMealDeliveryController;
import eapli.ecafeteria.domain.authz.SystemUser;
import eapli.ecafeteria.domain.authz.Username;
import eapli.ecafeteria.domain.booking.Booking;
import eapli.ecafeteria.domain.booking.BookingState;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.domain.cafeteriauser.MecanographicNumber;
import eapli.ecafeteria.domain.pos.DeliveryMealSession;
import eapli.ecafeteria.domain.pos.DeliveryRegistry;
import eapli.ecafeteria.domain.pos.POS;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.actions.Action;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.persistence.repositories.TransactionalContext;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author PedroEmanuelCoelho 1131485@isep.ipp.pt
 */
public class DeliveryRegistryBootstrapper implements Action {

    @Override
    public boolean execute() {
        
        TransactionalContext autoTx = PersistenceContext.repositories().buildTransactionalContext();
        
        boolean f = false;
        
        Username name = new Username("cashier");
        
        SystemUser user = PersistenceContext.repositories().users().findOne(name).get();

        POS p = new POS(user);

        DeliveryMealSession s = p.openSession();

        try {
            PersistenceContext.repositories().autoTxPOSRepository(autoTx).saveTransaction(p);
        } catch (DataConcurrencyException | DataIntegrityViolationException e) {
            Logger.getLogger(ECafeteriaBootstrapper.class.getSimpleName())
                    .log(Level.INFO, "POS\n{0}", e.getMessage());
        }

        try {
            PersistenceContext.repositories().deliveryMealRepository().save(s);
        } catch (DataConcurrencyException | DataIntegrityViolationException e) {
            Logger.getLogger(ECafeteriaBootstrapper.class.getSimpleName())
                    .log(Level.INFO, "DeliveryMealSession\n{0}", e.getMessage());
        }
        if (this.registerDeliveryRegistry(s)) {
            f = true;
        }

        return f;
    }

    private boolean registerDeliveryRegistry(DeliveryMealSession s) {

        boolean f = false;

        MecanographicNumber mNumber = new MecanographicNumber("900330");

        CafeteriaUser client = PersistenceContext.repositories().cafeteriaUsers().findByMecanographicNumber(mNumber).get();

        BookingState state = new BookingState();

        List<Booking> listB = PersistenceContext.repositories().booking().findBookingsByCafeteriaUser(client, state);

        if (!listB.isEmpty()) {
            Booking b = listB.get(0);

            DeliveryRegistry r = new DeliveryRegistry(s, client, b);

            try {
                PersistenceContext.repositories().deliveryRegistryRepository().save(r);
                f = true;
            } catch (DataConcurrencyException | DataIntegrityViolationException e) {
                Logger.getLogger(ECafeteriaBootstrapper.class.getSimpleName())
                        .log(Level.INFO, "DeliveryRegistry\n{0}", e.getMessage());
            }

        }
        return f;
    }
}
