package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.Application;
import eapli.ecafeteria.persistence.*;
import eapli.framework.persistence.repositories.TransactionalContext;
import eapli.framework.persistence.repositories.impl.jpa.JpaAutoTxRepository;

/**
 * Created by nuno on 21/03/16.
 */
public class JpaRepositoryFactory implements RepositoryFactory {

    @Override
    public UserRepository users(TransactionalContext autoTx) {
        return new JpaUserRepository(autoTx);
    }

    @Override
    public UserRepository users() {
        return new JpaUserRepository(Application.settings().getPersistenceUnitName());
    }

    @Override
    public DishTypeRepository dishTypes() {
        return new JpaDishTypeRepository();
    }

    @Override
    public JpaCafeteriaUserRepository cafeteriaUsers(TransactionalContext autoTx) {
        return new JpaCafeteriaUserRepository(autoTx);
    }

    @Override
    public JpaCafeteriaUserRepository cafeteriaUsers() {
        return new JpaCafeteriaUserRepository(Application.settings().getPersistenceUnitName());
    }

    @Override
    public SignupRequestRepository signupRequests(TransactionalContext autoTx) {
        return new JpaSignupRequestRepository(autoTx);
    }

    @Override
    public SignupRequestRepository signupRequests() {
        return new JpaSignupRequestRepository(Application.settings().getPersistenceUnitName());
    }

    @Override
    public DishRepository dishes() {
        return new JpaDishRepository();
    }

    @Override
    public MaterialRepository materials() {
        return new JpaMaterialRepository();
    }

    @Override
    public BatchRepository batch() {
        return new JpaBatchRepository();
    }

    @Override
    public MenuRepository menus() {
        return new JpaMenuRepository();
    }

    @Override
    public TransactionalContext buildTransactionalContext() {
        return JpaAutoTxRepository
                .buildTransactionalContext(Application.settings().getPersistenceUnitName(), Application.settings().getExtendedPersistenceProperties());
    }

    @Override
    public DishReportingRepository dishReporting() {
        return new JpaDishReportingRepository();
    }

    @Override
    public BookingRepository booking() {
        return new JpaBookingRepository();
    }

    @Override
    public BookingReportingRepository bookingReporting() {
        return new JpaBookingReportingRepository();
    }

    @Override
    public MenuPlanRepository menuPlan() {
        return new JpaMenuPlanRepository();
    }

    /**
     * Returns a new POS Repository when persistence is done on JPA
     *
     * @return
     */
    @Override
    public POSRepository posRepository() {
        return new JpaPOSRepository();
    }

    /**
     * Returns a new DeliveryMealSession Repository when persistence is done on
     * JPA
     *
     * @return
     */
    @Override
    public DeliveryMealSessionRepository deliveryMealRepository() {
        return new JpaDeliveryMealSessionRepository();
    }

    @Override
    public RatingRepository rating() {
        return new JpaRatingRepository();
    }

    @Override
    public MealRepository meals() {
        return new JpaMealRepository();
    }

    @Override
    public MealMaterialRepository mealMaterial() {
        return new JpaMealMaterialRepository();
    }

    @Override
    public ExecutionRepository executions() {
        return new JpaExecutionRepository();
    }

    @Override
    public AlergenRepository alergens() {
        return new JpaAlergensRepository();
    }

    @Override
    public JpaTransactionRepository movementTransaction(TransactionalContext autoTx) {
        return new JpaTransactionRepository(autoTx);
    }

    @Override
    public JpaTransactionRepository movementTransactions() {
        return new JpaTransactionRepository(Application.settings().getPersistenceUnitName());
    }

    @Override
    public JpaTransactionReportingRepository transactionsReporting() {
        return new JpaTransactionReportingRepository(Application.settings().getPersistenceUnitName());
    }

    @Override
    public RatingReportingRepository ratingsReporting() {
        return new JpaRatingReportingRepository();
    }

    @Override
    public MenuPlanItemRepository menuPlanItem() {
        return new JpaMenuPlanItemRepository();
    }

    @Override
    public AutoTxBookingRepository autoTxBookingRepository(TransactionalContext autoTx) {
        return new JpaAutoTxBookingRepository(autoTx);
    }

    /**
     * Return JpaDeliveryRegistryRepository
     *
     * @return
     */
    @Override
    public DeliveryRegistryRepository deliveryRegistryRepository() {
        return new JpaDeliveryRegistryRepository();
    }

    @Override
    public BalanceRepository balance() {
        return new JPAUserBalance();
    }

    /**
     * return JpaComplaintRepository
     *
     * @return
     */
    @Override
    public ComplaintRepository complaints() {
        return new JpaComplaintRepository();
    }

    public AllergenProfileRepository allergenProfiles() {
        return new JpaAllergenProfileRepository();
    }

    @Override
    public AlertRepositoryBookings alertRepositoryBookings() {
        return new JPAAltertRepositoryBookings();
    }

    @Override
    public AlertRepositoryLimits alertRepositoryLimits() {
        return new JPAAlertRepositoryLimits();
    }

    @Override
    public AutoTxTransactionRepository autoTxTransactionRepository(TransactionalContext autoTx) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public TransactionReportingRepository transactionReportingRepository(TransactionalContext autoTx) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BalanceLimitsRepository balanceLimits() {
        return new JPABalanceLimitsRepository();
    }

    @Override
    public AutoTxPOSRepository autoTxPOSRepository(TransactionalContext autoTx) {
        return new JpaAutoTxPOSRepository(autoTx);
    }

    @Override
    public DeactivationReasonTypeRepository deactivationReasonTypeRepository() {
        return new JpaDeactivationReasonTypeRepository(buildTransactionalContext());
    }

    @Override
    public DeactivationReasonTypeRepository deactivationReasonTypeRepository(TransactionalContext autoTx) {
        return new JpaDeactivationReasonTypeRepository(autoTx);
    }

    @Override
    public MealRepository mealRepositoryAutoTx(TransactionalContext autoTx) {
        return new JpaMealAutoTxRepository(autoTx);
    }
}
