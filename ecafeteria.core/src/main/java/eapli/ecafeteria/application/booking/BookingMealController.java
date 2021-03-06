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
import eapli.ecafeteria.domain.meal.Meal;
import eapli.ecafeteria.domain.dishes.Alergen;
import eapli.ecafeteria.domain.meal.MealType;
import eapli.ecafeteria.persistence.*;
import eapli.framework.application.*;
import eapli.framework.date.DateEAPLI;
import eapli.framework.domain.money.Money;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.persistence.repositories.TransactionalContext;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Observable;
import java.util.Optional;

/**
 *
 * @author Beatriz Ferreira <1160701@isep.ipp.pt>
 */
public class BookingMealController extends Observable implements Controller {

    private final CafeteriaUserService userService = new CafeteriaUserService();
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

        if (!mealList.iterator().hasNext()) {
            return true;
        } else {
            return false;
        }
    }

    //if already exists that booking then return true
    public boolean isAlreadyBooked(Username cafeteriaUser, MealType mealType, Calendar date) {
        CafeteriaUser user = findCafeteriaUserByUsername(cafeteriaUser);
        if (repository.findBooking(user, mealType, date).isEmpty()) {
            return false;
        }
        return true;
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
        if (listMeal.isEmpty()) {
            return false;
        } else {
            return true;
        }

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

    public boolean hasEnoughMoney(Username cafeteriaUser, Meal meal) {
        Money mealPrice = meal.dish().currentPrice();
        CafeteriaUser user = findCafeteriaUserByUsername(cafeteriaUser);
        if (userService.hasEnoughtMoney(user, mealPrice)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean mealHasAlergens(Meal meal) {
        List<Alergen> alergenList = meal.dish().alergenInDish();
        if (alergenList.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }

    public boolean is24hBeforeMeal(Calendar choosedDate, MealType mealType) {
        final String lunchTimeBegin = Application.settings().getLUNCH_TIME_BEGIN();
        final String dinnerTimeBegin = Application.settings().getDiNNER_TIME_BEGIN();

        LocalTime time = null;
        if (mealType == mealType.LUNCH) {
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
        if (dt.isTomorrow(choosedDate)) {
            return true;
        }
        return false;
    }

    CafeteriaUser findCafeteriaUserByUsername(Username username) {
        Optional<CafeteriaUser> user = userService.findCafeteriaUserByUsername(username);
        return user.get();
    }

    /**
     * Confirms reservation and persists changes
     *
     * @return true after conclusion
     * @throws eapli.framework.persistence.DataConcurrencyException
     * @throws eapli.framework.persistence.DataIntegrityViolationException
     */
    public boolean confirmBooking(Username username, Calendar date,
            BookingState bookingState, Meal meal) throws DataConcurrencyException,
            DataIntegrityViolationException {

          CafeteriaUser user = findCafeteriaUserByUsername(username);
        // Add booking movement
        Booking newBooking = new Booking(meal, bookingState, user, date);

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
        Transaction t = new Transaction(user, TransactionType.DEBIT, mealPrice);
        t.movement();
        attr.saveTransaction(t);

        cafer.save(user);
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
     * @param allergenProfile - current authenticated user allergen profile
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
