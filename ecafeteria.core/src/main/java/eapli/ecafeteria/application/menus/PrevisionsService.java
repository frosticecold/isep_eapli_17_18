package eapli.ecafeteria.application.menus;

import eapli.ecafeteria.persistence.BookingRepository;
import eapli.ecafeteria.persistence.DeliveryRegistryRepository;
import eapli.ecafeteria.persistence.MealRepository;
import eapli.ecafeteria.persistence.MenuPlanRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.ecafeteria.persistence.RatingRepository;
import eapli.framework.application.Controller;

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