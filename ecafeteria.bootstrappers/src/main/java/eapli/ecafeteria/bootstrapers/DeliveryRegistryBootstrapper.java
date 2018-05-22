package eapli.ecafeteria.bootstrapers;

import eapli.ecafeteria.domain.authz.SystemUser;
import eapli.ecafeteria.domain.authz.Username;
import eapli.ecafeteria.domain.booking.Booking;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.domain.pos.DeliveryMealSession;
import eapli.ecafeteria.domain.pos.DeliveryRegistry;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.actions.Action;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.logging.Logger;

/**
 *
 * @author PedroEmanuelCoelho 1131485@isep.ipp.pt
 */
public class DeliveryRegistryBootstrapper implements Action {

    @Override
    public boolean execute() {

        SystemUser cashier = PersistenceContext.repositories().users().findOne(new Username("poweruser")).get();

        DeliveryMealSession s = PersistenceContext.repositories().deliveryMealRepository().findYourSession(cashier).get();
        
        this.registerDeliveryRegistrys(s, cashier);

        return true;
    }

    private boolean registerDeliveryRegistrys(DeliveryMealSession s, SystemUser cashier) {

        boolean flag = false;

        Booking b = PersistenceContext.repositories().booking().findOne(new Long(84)).get();

        Booking b2 = PersistenceContext.repositories().booking().findOne(new Long(85)).get();

        CafeteriaUser client = PersistenceContext.repositories().cafeteriaUsers().findByMecanographicNumber(b.getCafeteriauser().mecanographicNumber()).get();

        CafeteriaUser client2 = PersistenceContext.repositories().cafeteriaUsers().findByMecanographicNumber(b2.getCafeteriauser().mecanographicNumber()).get();

        DeliveryRegistry r1 = new DeliveryRegistry(s, client, b);

        DeliveryRegistry r2 = new DeliveryRegistry(s, client2, b2);

        try {
            PersistenceContext.repositories().deliveryRegistryRepository().save(r2);

            PersistenceContext.repositories().deliveryRegistryRepository().save(r1);
        } catch (final DataIntegrityViolationException | DataConcurrencyException e) {
            // ignoring exception. assuming it is just a primary key violation
            // due to the tentative of inserting a duplicated user
            Logger.getLogger(ECafeteriaBootstrapper.class.getSimpleName())
                    .info("Msg : " + e.getMessage() + "\nCause" + e.getCause());
        }

        return flag;

    }

}
