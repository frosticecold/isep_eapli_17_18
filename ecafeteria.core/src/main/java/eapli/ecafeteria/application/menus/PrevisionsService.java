package eapli.ecafeteria.application.menus;

import eapli.ecafeteria.domain.booking.Booking;
import eapli.ecafeteria.domain.dishes.DishType;
import eapli.ecafeteria.domain.meal.Meal;
import eapli.ecafeteria.domain.meal.MealType;
import eapli.ecafeteria.persistence.BookingRepository;
import eapli.ecafeteria.persistence.DeliveryRegistryRepository;
import eapli.ecafeteria.persistence.DishTypeRepository;
import eapli.ecafeteria.persistence.MealRepository;
import eapli.ecafeteria.persistence.MenuPlanRepository;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.ecafeteria.persistence.RatingRepository;
import eapli.framework.application.Controller;
import java.util.Calendar;
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
    private DishTypeRepository dishTypeRepo;

    //Construtor of service
    public PrevisionsService() {
        this.prepareRepositories();
    }

    public Long[][] prepareBookedMealsList() {

        Long[][] matrix = new Long[3][1];

        DishType fish = this.dishTypeRepo.findByAcronym("fish").get();
        DishType meat = this.dishTypeRepo.findByAcronym("meat").get();
        DishType vegie = this.dishTypeRepo.findByAcronym("vegie").get();

        Long qtFish = this.bookingRepo.countReservedMealsByDishType(Calendar.getInstance(), fish, MealType.LUNCH);
        qtFish += this.bookingRepo.countReservedMealsByDishType(Calendar.getInstance(), fish, MealType.DINNER);

        Long qtMeat = this.bookingRepo.countReservedMealsByDishType(Calendar.getInstance(), meat, MealType.LUNCH);
        qtMeat += this.bookingRepo.countReservedMealsByDishType(Calendar.getInstance(), meat, MealType.DINNER);

        Long qtVegie = this.bookingRepo.countReservedMealsByDishType(Calendar.getInstance(), vegie, MealType.LUNCH);
        qtVegie += this.bookingRepo.countReservedMealsByDishType(Calendar.getInstance(), vegie, MealType.DINNER);

        //filling matrix
        matrix[0][0] = qtMeat;
        matrix[1][0] = qtFish;
        matrix[2][0] = qtVegie;

        return matrix;
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
        this.dishTypeRepo = PersistenceContext.repositories().dishTypes();
    }
}
