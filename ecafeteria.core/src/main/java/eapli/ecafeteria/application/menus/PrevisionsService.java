package eapli.ecafeteria.application.menus;

import eapli.ecafeteria.domain.booking.Booking;
import eapli.ecafeteria.domain.meal.Meal;
import eapli.ecafeteria.persistence.BookingRepository;
import eapli.ecafeteria.persistence.DeliveryRegistryRepository;
import eapli.ecafeteria.persistence.MealRepository;
import eapli.ecafeteria.persistence.MenuPlanRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.ecafeteria.persistence.RatingRepository;
import eapli.framework.application.Controller;
import java.util.List;

/**
 *
 * @author PedroEmanuelCoelho 1131485@isep.ipp.pt
 */
public class PrevisionsService implements Controller {

    private BookingRepository bookingRepo;
    private DeliveryRegistryRepository deliveryRepo;
    private MealRepository mealsRepo;
    private MenuPlanRepository menuPlanRepo;
    private RatingRepository ratingRepo;

    //Construtor of service
    public PrevisionsService() {
        this.prepareRepositories();
    }

    //prepare a list of booked meals
    public int[][] getBookedMeals() {

        int[][] ret = new int[1000][1000];

        Iterable<Meal> meals = this.mealsRepo.findAll();

        List<Booking> bookings;

        int j = 0;
        int i = 0;

        for (Meal m : meals) {

            bookings = this.bookingRepo.getAllBookingsFromMealThatAreBooked(m);

            while (j < bookings.size() && i < 1000) {
                ret[i][0] = m.id().intValue();
                ret[i][1] = bookings.get(j).getIdBooking().intValue();
                i++;
                j++;
            }
        }

        return ret;
    }

    /**
     * method to go to persistenceContext and extract all the repositories
     * needed
     */
    private void prepareRepositories() {

        this.bookingRepo = PersistenceContext.repositories().booking();
        this.deliveryRepo = PersistenceContext.repositories().deliveryRegistryRepository();
        this.mealsRepo = PersistenceContext.repositories().meals();
        this.menuPlanRepo = PersistenceContext.repositories().menuPlan();
        this.ratingRepo = PersistenceContext.repositories().rating();
    }
}
