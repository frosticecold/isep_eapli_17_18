package eapli.ecafeteria.persistence.inmemory;

import eapli.ecafeteria.bootstrapers.ECafeteriaBootstrapper;
import eapli.ecafeteria.persistence.BookingReportingRepository;
import eapli.ecafeteria.persistence.BookingRepository;
import eapli.ecafeteria.persistence.CafeteriaUserRepository;
import eapli.ecafeteria.persistence.DeliveryMealSessionRepository;
import eapli.ecafeteria.persistence.DishReportingRepository;
import eapli.ecafeteria.persistence.DishRepository;
import eapli.ecafeteria.persistence.DishTypeRepository;
import eapli.ecafeteria.persistence.ExecutionRepository;
import eapli.ecafeteria.persistence.MaterialRepository;
import eapli.ecafeteria.persistence.MealRepository;
import eapli.ecafeteria.persistence.MenuPlanRepository;
import eapli.ecafeteria.persistence.MenuRepository;
import eapli.ecafeteria.persistence.POSRepository;
import eapli.ecafeteria.persistence.RatingRepository;
import eapli.ecafeteria.persistence.RepositoryFactory;
import eapli.ecafeteria.persistence.SignupRequestRepository;
import eapli.ecafeteria.persistence.UserRepository;
import eapli.framework.persistence.repositories.TransactionalContext;

/**
 *
 * Created by nuno on 20/03/16.
 */
public class InMemoryRepositoryFactory implements RepositoryFactory {

    static {
        // only needed because of the in memory persistence
        new ECafeteriaBootstrapper().execute();
    }

    @Override
    public UserRepository users(TransactionalContext tx) {
        return new InMemoryUserRepository();
    }

    @Override
    public UserRepository users() {
        return users(null);
    }

    @Override
    public DishTypeRepository dishTypes() {
        return new InMemoryDishTypeRepository();
    }

    @Override
    public CafeteriaUserRepository cafeteriaUsers(TransactionalContext tx) {

        return new InMemoryCafeteriaUserRepository();
    }

    @Override
    public CafeteriaUserRepository cafeteriaUsers() {
        return cafeteriaUsers(null);
    }

    @Override
    public SignupRequestRepository signupRequests() {
        return signupRequests(null);
    }

    @Override
    public SignupRequestRepository signupRequests(TransactionalContext tx) {
        return new InMemorySignupRequestRepository();
    }

    @Override
    public DishRepository dishes() {
        return new InMemoryDishRepository();
    }

    @Override
    public MaterialRepository materials() {
        return new InMemoryMaterialRepository();
    }

    @Override
    public BatchRepository batch() {
        return new InMemoryBatchRepository();
    }

    @Override
    public MenuRepository menus() {
        return new InMemoryMenuRepository();
    }

    @Override
    public TransactionalContext buildTransactionalContext() {
        // in memory does not support transactions...
        return null;
    }

    @Override
    public DishReportingRepository dishReporting() {
        return new InMemoryDishReportingRepository();
    }

    @Override
    public BookingRepository booking() {
        return new InMemoryBookingRepository();
    }

    @Override
    public BookingReportingRepository bookingReporting() {
        return new InMemoryBookingReportingRepository();
    }

    @Override
    public MenuPlanRepository menuPlan() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Returns a new POS repository when persistence is on memory
     *
     * @return
     */
    @Override
    public POSRepository posRepository() {
        return new InMemoryPOSRepository();
    }

    /**
     * Returns a deliveryMealSession repository in persistence is on memory
     *
     * @return
     */
    @Override
    public DeliveryMealSessionRepository deliveryMealRepository() {
        return new InMemoryDeliveryMealSessionRepository();
    }

    @Override
    public RatingRepository rating() {
        return new InMemoryRatingRepository();
    }

    @Override
    public MealRepository meals() {
        return new InMemoryMealRepository();
    }

    @Override
    public ExecutionRepository executions() {
        return new InMemoryExecutionRepository();
    }
}
