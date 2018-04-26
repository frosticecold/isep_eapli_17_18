/**
 *
 */
package eapli.ecafeteria.persistence;

import eapli.framework.persistence.repositories.TransactionalContext;

/**
 * @author Paulo Gandra Sousa
 *
 */
public interface RepositoryFactory {

    /**
     * factory method to create a transactional context to use in the
     * repositories
     *
     * @return
     */
    TransactionalContext buildTransactionalContext();

    /**
     *
     * @param autoTx the transactional context to enrol
     * @return
     */
    UserRepository users(TransactionalContext autoTx);

    /**
     * repository will be created in auto transaction mode
     *
     * @return
     */
    UserRepository users();

    DishTypeRepository dishTypes();

    /**
     *
     * @param autoTx the transactional context to enroll
     * @return
     */
    CafeteriaUserRepository cafeteriaUsers(TransactionalContext autoTx);

    /**
     * repository will be created in auto transaction mode
     *
     * @return
     */
    CafeteriaUserRepository cafeteriaUsers();
    
     /**
     * repository will be created in auto transaction mode
     *
     * @return
     */
    BalanceRepository balance();

    /**
     *
     * @param autoTx the transactional context to enroll
     * @return
     */
    SignupRequestRepository signupRequests(TransactionalContext autoTx);

    /**
     * repository will be created in auto transaction mode
     *
     * @return
     */
    SignupRequestRepository signupRequests();

    DishRepository dishes();

    MaterialRepository materials();

    BatchRepository batch();

    BookingRepository booking();

    MenuRepository menus();

    MenuPlanRepository menuPlan();
    
    MenuPlanItemRepository menuPlanItem();

    RatingRepository rating();

    /*
     * ************************
     * reporting ************************
     */
    /**
     * @return
     */
    DishReportingRepository dishReporting();

    /**
     * Booking Reporting Repo
     *
     * @return
     */
    BookingReportingRepository bookingReporting();

    /**
     * POS Repo
     *
     * @return
     */
    POSRepository posRepository();

    /**
     * DeliveryMealSession Repo
     *
     * @return
     */
    DeliveryMealSessionRepository deliveryMealRepository();

    /**
     * DeliveryRegistry Repo
     */
    DeliveryRegistryRepository deliveryRegistryRepository();

    /**
     * Ratings reporting repository
     * @return 
     */
    RatingReportingRepository ratingsReporting();


    MealRepository meals();

    ExecutionRepository executions();

    AlergenRepository alergens();
    
    TransactionRepository transactioRepository();

    TransactionRepository transactioRepository(TransactionalContext autoTx);
        /**
     * MealMaterialRepositorySession Repo
     * @return 
     */
    MealMaterialRepository mealMaterial();
    
    AutoTxBookingRepository autoTxBookingRepository(TransactionalContext autoTx);
    
    AutoTxTransactionRepository autoTxTransactionRepository(TransactionalContext autoTx);
}
