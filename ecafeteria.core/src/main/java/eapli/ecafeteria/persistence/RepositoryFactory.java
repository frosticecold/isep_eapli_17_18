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

    BalanceLimitsRepository balanceLimits();
    
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
    
    AutoTxPOSRepository autoTxPOSRepository(TransactionalContext autoTx);

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
     *
     * @return
     */
    RatingReportingRepository ratingsReporting();

    MealRepository meals();

    ExecutionRepository executions();

    AlergenRepository alergens();

    TransactionRepository movementTransactions();

    TransactionRepository movementTransaction(TransactionalContext autoTx);

    TransactionReportingRepository transactionsReporting();

    /**
     * TransactionRepository transactioRepository(TransactionalContext autoTx);
     *
     * /**
     * MealMaterialRepositorySession Repo
     *
     * @return
     */
    MealMaterialRepository mealMaterial();

    /**
     *
     * @param autoTx
     * @return
     */
    AutoTxBookingRepository autoTxBookingRepository(TransactionalContext autoTx);

    AutoTxTransactionRepository autoTxTransactionRepository(TransactionalContext autoTx);

    TransactionReportingRepository transactionReportingRepository(TransactionalContext autoTx);

    ComplaintRepository complaints();

    AllergenProfileRepository allergenProfiles();

    /**
     * Reasons Repository
     *
     * @author Raúl Correia
     *
     * @return
     */
    ReasonRepository reasons();

    /**
     * Reason Repository with TransationalContext
     *
     * @author Raúl Correia
     * @param autoTx
     * @return
     */
    ReasonRepository reasons(TransactionalContext autoTx);

    AlertRepositoryBookings alertRepositoryBookings();

    AlertRepositoryLimits alertRepositoryLimits();
}
