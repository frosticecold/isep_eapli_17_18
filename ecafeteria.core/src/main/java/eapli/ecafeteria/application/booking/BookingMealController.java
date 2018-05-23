/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.booking;

import eapli.ecafeteria.Application;
import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.application.cafeteriauser.CafeteriaUserService;
import eapli.ecafeteria.application.menus.MenuService;
import eapli.ecafeteria.domain.CreditTransaction.Transaction;
import eapli.ecafeteria.domain.CreditTransaction.TransactionType;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.domain.authz.Username;
import eapli.ecafeteria.domain.booking.Booking;
import eapli.ecafeteria.domain.booking.BookingState;
import eapli.ecafeteria.domain.cafeteriauser.AllergenProfile;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.domain.dishes.Alergen;
import eapli.ecafeteria.domain.meal.Meal;
import eapli.ecafeteria.domain.meal.MealType;
import eapli.ecafeteria.persistence.*;
import eapli.framework.application.Controller;
import eapli.framework.date.DateEAPLI;
import eapli.framework.domain.money.Money;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.persistence.repositories.TransactionalContext;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.*;

/**
 *
 * @author Beatriz Ferreira <1160701@isep.ipp.pt>
 */
public class BookingMealController extends Observable implements Controller {

    private final CafeteriaUserService userService = new CafeteriaUserService();
     Username cafeteriaUser = AuthorizationService.session().authenticatedUser().id();
     Optional<CafeteriaUser> user = userService.findCafeteriaUserByUsername(cafeteriaUser);

    private final BookingRepository repository = PersistenceContext.repositories().booking();

    /**
     *
     * @param date
     * @param mealType
     * @return a list with all meals by date
     */
    public Iterable<Meal> listMeals(Calendar date, MealType mealType) {
        return MenuService.getMealsPublishedByDay(date, mealType);
    }

    public boolean mealListIsEmpty(Iterable<Meal> mealList) {

        return !mealList.iterator().hasNext();
    }

    //if already exists that booking then return true
    public boolean isAlreadyBooked(MealType mealType, Calendar date) {
        return !repository.findBooking(user.get(), mealType, date).isEmpty();
    }

    public List<Meal> mealListIfMenuIsPublic(Iterable<Meal> mealList) {
        List<Meal> listMeal = new ArrayList<Meal>();
        for (Meal meal : mealList) {
            if (meal.menu().isPublished()) {
                listMeal.add(meal);
            }
        }
        return listMeal;
    }

    public boolean menuOfMealListIsPublic(List<Meal> listMeal) {
        return !listMeal.isEmpty();

    }

    public Meal selectMeal(int idMeal, List<Meal> listMeal) {
        Meal selectedMeal = null;

        if (0 <= idMeal && idMeal < listMeal.size()) {
            selectedMeal = listMeal.get(idMeal);
        } else {
            throw new IllegalArgumentException("Meal id "
                    + idMeal + "is not valid!");
        }

        return selectedMeal;
    }

    public boolean hasEnoughMoney( Meal meal) {
        Money mealPrice = meal.dish().currentPrice();
        return userService.hasEnoughtMoney(user.get(), mealPrice);
    }

    public boolean mealHasAlergens(Meal meal) {
        List<Alergen> alergenList = meal.dish().alergenInDish();
        return !alergenList.isEmpty();
    }

    public boolean is24hBeforeMeal(Calendar choosedDate, MealType mealType) {
        final String lunchTimeBegin = Application.settings().getLUNCH_TIME_BEGIN();
        final String dinnerTimeBegin = Application.settings().getDiNNER_TIME_BEGIN();

        LocalTime time = null;
        if (mealType == MealType.LUNCH) {
            time = LocalTime.parse(lunchTimeBegin, DateTimeFormatter.ofPattern("HH:mm:ss"));
        } else {
            time = LocalTime.parse(dinnerTimeBegin, DateTimeFormatter.ofPattern("HH:mm:ss"));
        }
        int hour = time.get(ChronoField.CLOCK_HOUR_OF_DAY);
        int minute = time.get(ChronoField.MINUTE_OF_HOUR);
        int second = time.get(ChronoField.SECOND_OF_MINUTE);

        choosedDate.set(Calendar.HOUR_OF_DAY, hour);
        choosedDate.set(Calendar.MINUTE, minute);
        choosedDate.set(Calendar.SECOND, second);

        DateEAPLI dt = new DateEAPLI(choosedDate);
        return dt.isTomorrow(choosedDate);
    }

    /**
     * Confirms reservation and persists changes
     *
     * @return true after conclusion
     * @throws eapli.framework.persistence.DataConcurrencyException
     * @throws eapli.framework.persistence.DataIntegrityViolationException
     */
    public boolean confirmBooking(Calendar date,
            BookingState bookingState, Meal meal) throws DataConcurrencyException,
            DataIntegrityViolationException {

        // Add booking movement


        Booking newBooking = new Booking(meal, bookingState, user.get(), date);

        Money mealPrice = meal.dish().currentPrice();

        // Persist
        final TransactionalContext TxCtx
                = PersistenceContext.repositories().buildTransactionalContext();
        final TransactionRepository attr
                = PersistenceContext.repositories().movementTransaction(TxCtx);
        final AutoTxBookingRepository atbr
                = PersistenceContext.repositories().autoTxBookingRepository(TxCtx);
        final CafeteriaUserRepository cafer
                = PersistenceContext.repositories().cafeteriaUsers(TxCtx);

        TxCtx.beginTransaction();

        /* persist here */
        atbr.saveBooking(newBooking);
        Transaction t = new Transaction(user.get(), TransactionType.DEBIT, mealPrice);
        t.movement();
        attr.saveTransaction(t);

        cafer.save(user.get());
        TxCtx.commit();

        setChanged();
        this.notifyObservers(newBooking);

        return true;
    }

    /**
     * used in bootstrapper- tests
     *
     * @param cafeteriaUser
     * @param date
     * @param bookingState
     * @param meal
     * @return
     * @throws DataIntegrityViolationException
     * @throws DataConcurrencyException
     */
    public Booking persistBooking(final Username cafeteriaUser, final Calendar date,
            final BookingState bookingState, final Meal meal) throws DataIntegrityViolationException, DataConcurrencyException {

        AuthorizationService.ensurePermissionOfLoggedInUser(ActionRight.SELECT_MEAL);

        Optional<CafeteriaUser> user = userService.findCafeteriaUserByUsername(cafeteriaUser);

        final Booking newBooking = new Booking(meal, bookingState, user.get(), date);

        return this.repository.saveBooking(newBooking);
    }

    /**
     * Compares the list of allergens present in the meal with the user's
     * allergen profile.
     *
     * @author Rui Almeida <1160818>
     * @param meal - chosen meal for booking
     * @return - List with the matched allergens between the user profile and
     * the meal
     */
    public List<Alergen> matchAllergens(Meal meal) {
        /*
        List with all of the matched allergens
         */
        List<Alergen> allergensList = new ArrayList<>();

        /*
        Get repositories
         */
        RepositoryFactory factory = PersistenceContext.repositories();
        final AllergenProfileRepository alergenPlanRepo = factory.allergenProfiles();
        final CafeteriaUserRepository userRepo = factory.cafeteriaUsers();

        /**
         * Get current user
         */
        CafeteriaUser user = userRepo.findByUsername(
                AuthorizationService.session().authenticatedUser().username())
                .get();

        /*
        * Get user allergen plan repo and get current user allergen profile
         */
        AllergenProfile userAllergenProfile = alergenPlanRepo.findUserAllergenProfile(user);

        /*
        * Iterate through the list and compare the allergens.
        * If we find a matching allergen and allergen isn't already on the list,
        * add it.
         */
        for (Alergen userAllergen : userAllergenProfile.alergens()) {
            for (Alergen mealAllergen : meal.dish().alergenInDish()) {
                if (userAllergen.equals(mealAllergen) && !allergensList.contains(userAllergen)) {
                    allergensList.add(userAllergen);
                }
            }
        }

        return allergensList;
    }

}
