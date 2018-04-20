/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.application.booking;

import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.application.cafeteriauser.CafeteriaUserService;
import eapli.ecafeteria.application.menus.MenuService;
import eapli.ecafeteria.domain.authz.ActionRight;
import static eapli.ecafeteria.domain.authz.ActionRight.SELECT_MEAL;
import eapli.ecafeteria.domain.authz.Username;
import eapli.ecafeteria.domain.booking.Booking;
import eapli.ecafeteria.domain.booking.BookingState;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.domain.dishes.NutricionalInfo;
import eapli.ecafeteria.domain.menu.*;
import eapli.ecafeteria.domain.meal.*;
import eapli.ecafeteria.domain.CreditTransaction.DebitBooking;
import eapli.ecafeteria.persistence.*;
import eapli.framework.application.*;
import eapli.framework.date.DateEAPLI;
import eapli.framework.domain.money.Money;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Beatriz Ferreira <1160701@isep.ipp.pt>
 */
public class BookingMealController implements Controller {

    private final MenuService svc = new MenuService();
    private final CafeteriaUserService userService = new CafeteriaUserService();

    private final BookingRepository repository = PersistenceContext.repositories().booking();

    /**
     *
     * @param date
     * @param mealType
     * @return a list with all meals by date
     */
    public Iterable<Meal> listMeals(Calendar date, MealType mealType) {
        return svc.getMealsPublishedByDay(date, mealType);
    }

    public boolean doTransaction(Username id, Meal meal) {
        Money mealPrice = meal.dish().currentPrice();
        Optional<CafeteriaUser> user = userService.findCafeteriaUserByUsername(id);
        if (user.get().hasEnoughCredits(mealPrice)) {
            DebitBooking db = new DebitBooking(user.get(), user.get().currentBalance().currentBalance());
            db.movement(user.get(), mealPrice);
            return true;
        }
        return false;
    }

    public Booking persistBooking(final Username cafeteriaUser, final Calendar date,
            final BookingState bookingState, final Meal meal) throws DataIntegrityViolationException, DataConcurrencyException {

        AuthorizationService.ensurePermissionOfLoggedInUser(ActionRight.SELECT_MEAL);

        Optional<CafeteriaUser> user = userService.findCafeteriaUserByUsername(cafeteriaUser);

        final Booking newBooking = new Booking(meal, bookingState, user.get(), date);

        return this.repository.saveBooking(newBooking);
    }

    public void showNutricionalInfo(Meal meal) {
        meal.dish().nutricionalInfo().toString();

    }

    public void showAlergen(Meal meal) {
        // meal.dish().alergenInDish();
    }

    public boolean is24hBefore(Calendar date) {
        DateEAPLI dt = new DateEAPLI(date);
        if (dt.isTomorrow(date)) {
            return true;
        }
        return false;
    }
}
