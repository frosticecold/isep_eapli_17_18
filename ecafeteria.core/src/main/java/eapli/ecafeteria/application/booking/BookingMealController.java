/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.booking;

import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.application.cafeteriauser.CafeteriaUserService;
import eapli.ecafeteria.application.menus.MenuService;
import eapli.ecafeteria.domain.CreditTransaction.Debit;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.ecafeteria.domain.authz.Username;
import eapli.ecafeteria.domain.booking.Booking;
import eapli.ecafeteria.domain.booking.BookingState;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.domain.meal.Meal;
import eapli.ecafeteria.domain.cafeteriauser.Balance;
import eapli.ecafeteria.domain.dishes.Alergen;
import eapli.ecafeteria.domain.meal.MealType;
import eapli.ecafeteria.persistence.*;
import eapli.framework.application.*;
import eapli.framework.date.DateEAPLI;
import eapli.framework.domain.money.Money;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import eapli.framework.persistence.repositories.TransactionalContext;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Beatriz Ferreira <1160701@isep.ipp.pt>
 */
public class BookingMealController implements Controller {

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

    public List<Booking> listBookingsOfUser(Username cafeteriaUser) {

        Optional<CafeteriaUser> user = userService.findCafeteriaUserByUsername(cafeteriaUser);
        BookingState booked = new BookingState();
        return repository.findBookingsByCafeteriaUser(user.get(), booked);

    }

    public boolean isAlreadyBooked(List<Booking> bookings, Booking newBooking) {
        for (Booking booking : bookings) {
            if (booking.equals(newBooking)) {
                return true;
            }
        }
        return false;
    }

    public List<Booking> isAlreadyBooked(Username cafeteriaUser) {

        Optional<CafeteriaUser> user = userService.findCafeteriaUserByUsername(cafeteriaUser);
        BookingState booked = new BookingState();
        return repository.findBookingsByCafeteriaUser(user.get(), booked);

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

    public boolean hasEnoughMoney(Username id, Meal meal) {
        Money mealPrice = meal.dish().currentPrice();
        Optional<CafeteriaUser> user = userService.findCafeteriaUserByUsername(id);
        if (userService.hasEnoughtMoney(user.get(), mealPrice)) {
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

    public boolean is24hBefore(Calendar date) {
        DateEAPLI dt = new DateEAPLI(date);
        if (dt.isTomorrow(date)) {
            return true;
        }
        return false;
    }

    /**
     * Confirms reservation and persists changes
     *
     * @return true after conclusion
     * @throws eapli.framework.persistence.DataConcurrencyException
     * @throws eapli.framework.persistence.DataIntegrityViolationException
     */
    public boolean confirmBooking(Username cafeteriaUser, Calendar date,
            BookingState bookingState, Meal meal,List<Booking> bookings) throws DataConcurrencyException,
            DataIntegrityViolationException {

        // Add booking movement
        Optional<CafeteriaUser> user = userService.findCafeteriaUserByUsername(cafeteriaUser);
        Booking newBooking = new Booking(meal, bookingState, user.get(), date);
       
        //check if user as already booked this meal
        for (Booking booking : bookings) {
            if(booking.equals(newBooking)){
                return false;
            }
        }
        
        Money mealPrice = meal.dish().currentPrice();
        Balance userBalance = userService.getBalanceOfUser(user.get().mecanographicNumber());
        Money money = userBalance.currentBalance().subtract(mealPrice);
        Balance newBalance = new Balance(money);

        user.get().updateUserBalance(newBalance);

        // Persist
        final TransactionalContext TxCtx
                = PersistenceContext.repositories().buildTransactionalContext();
        final AutoTxTransactionRepository attr
                = PersistenceContext.repositories().autoTxTransactionRepository(TxCtx);
        final AutoTxBookingRepository atbr
                = PersistenceContext.repositories().autoTxBookingRepository(TxCtx);
        final CafeteriaUserRepository cafer
                = PersistenceContext.repositories().cafeteriaUsers(TxCtx);

        TxCtx.beginTransaction();

        /* persist here */
        atbr.saveBooking(newBooking);

        attr.saveTransaction(new Debit(user.get(), mealPrice));

        cafer.save(user.get());
        TxCtx.commit();

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

}
