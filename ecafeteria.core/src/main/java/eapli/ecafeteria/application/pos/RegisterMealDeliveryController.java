package eapli.ecafeteria.application.pos;

import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.domain.authz.SystemUser;
import eapli.ecafeteria.domain.booking.Booking;
import eapli.ecafeteria.domain.booking.BookingState;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.domain.cafeteriauser.MecanographicNumber;
import eapli.ecafeteria.domain.pos.DeliveryMealSession;
import eapli.ecafeteria.domain.pos.DeliveryRegistry;
import eapli.ecafeteria.persistence.BookingRepository;
import eapli.ecafeteria.persistence.CafeteriaUserRepository;
import eapli.ecafeteria.persistence.DeliveryRegistryRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;
import eapli.framework.persistence.repositories.TransactionalContext;
import java.util.Calendar;

/**
 *
 * @author PedroEmanuelCoelho 1131485@isep.ipp.pt
 */
public class RegisterMealDeliveryController extends ViewAvailableMealsController implements Controller {

    /**
     * Constructor which shall receive a entity of a open session of a certain
     * pos from the UC User Interface *
     */
    private DeliveryMealSession session;
    private SystemUser cashier;
    private final DeliveryRegistryRepository deliveryRegistryRepo = PersistenceContext.repositories().deliveryRegistryRepository();
    private final BookingRepository bookingsRepo = PersistenceContext.repositories().booking();
    private final CafeteriaUserRepository cafeteriaUsersRepo = PersistenceContext.repositories().cafeteriaUsers(); //doesnt user transaction context because it wont change or persist any changes on database

    public RegisterMealDeliveryController() {
        this.cashier = AuthorizationService.session().authenticatedUser();
        session = null;
    }

    /**
     * Method that will focus on recording a new meal delivery on persistence
     * saving the booking which will be delivered, the client who made the
     * booking and the POS where it was delivered
     *
     * @param idClient
     * @param idBooking Booking which will be delivered
     */
    public void registerNewMealDelivery(String number, long idBooking) {

        this.useSession();

        //transform string into MecanographicNumber
        MecanographicNumber mNumber = new MecanographicNumber(number);

        //obtain the client
        CafeteriaUser client = this.cafeteriaUsersRepo.findByMecanographicNumber(mNumber).get();

        //get booking by the number of the user
        Booking booking = this.bookingsRepo.findOne(idBooking).get();

        //add new record of the delivery just made on DeliveryRegistry
        DeliveryRegistry registry = new DeliveryRegistry(this.session, client, booking);

        //change state of the booking just recorded - to served
        try {
            this.deliveryRegistryRepo.save(registry);
        } catch (Exception e) {

        }

        Booking b = this.bookingsRepo.findOne(idBooking).get();

        b.getBookingState().changeToServed();

        try {

            this.bookingsRepo.save(b);
        } catch (Exception e) {

        }

    }

    /**
     * Validates booking by searching on the repository
     *
     * @param idBooking
     * @return
     */
    public boolean validatesBooking(long idBooking) {

        boolean flag = false;

        //get booking by id
        if (this.bookingsRepo.findOne(idBooking).isPresent()) {
            flag = true;
        }

        return flag;
    }

    /**
     * Validate if user exists
     *
     * @param number
     * @return
     */
    public boolean validateClient(String number) {

        boolean flag = false;

        MecanographicNumber mNumber = new MecanographicNumber(number);

        if (this.cafeteriaUsersRepo.findOne(mNumber).isPresent()) {
            flag = true;
        }

        return flag;
    }

    /**
     * Returns booking by finding the user who payed for it
     */
    public boolean getBooking(long idBooking) {

        Booking b = this.bookingsRepo.findOne(idBooking).get();

        if (b == null) {
            return false;
        } else {
            return true;
        }
    }

    /**
     * Check if booking is already delivered
     *
     * @param idBooking
     * @return
     */
    public boolean canServeBooking(long idBooking) {

        boolean flag = false;

        Booking booking = this.bookingsRepo.findOne(idBooking).get();

        if (booking.getBookingState().actualState() == BookingState.BookingStates.BOOKED) { //booking can only be delivered if is booked
            if (booking.getMeal().getMealDate() == Calendar.getInstance()) { //checks if booking is booked for today
                flag = true;
            }
        }

        return flag;
    }

    /**
     * References the correct session to proceed
     *
     * @param cashier
     */
    private void useSession() {

        DeliveryMealSession s = PersistenceContext.repositories().deliveryMealRepository().findYourSession(this.cashier).get();

        if (s != null) {
            this.session = s;
        }
    }
}
