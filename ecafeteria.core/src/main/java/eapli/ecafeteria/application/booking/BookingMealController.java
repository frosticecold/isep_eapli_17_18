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
import eapli.ecafeteria.domain.authz.Username;
import eapli.ecafeteria.domain.booking.Booking;
import eapli.ecafeteria.domain.booking.BookingState;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.domain.meal.*;
import eapli.ecafeteria.domain.CreditTransaction.Transaction;
import eapli.ecafeteria.domain.cafeteriauser.Balance;
import eapli.ecafeteria.persistence.*;
import eapli.framework.application.*;
import eapli.framework.date.DateEAPLI;
import eapli.framework.domain.money.Money;
import eapli.framework.persistence.DataConcurrencyException;
import eapli.framework.persistence.DataIntegrityViolationException;
import java.util.Calendar;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Beatriz Ferreira <1160701@isep.ipp.pt>
 */
public class BookingMealController implements Controller {

    private final CafeteriaUserService userService = new CafeteriaUserService();

    private final BookingRepository repository = PersistenceContext.repositories().booking();

    private final TransactionRepository trepo = PersistenceContext.repositories().transactioRepository();

    /**
     *
     * @param date
     * @param mealType
     * @return a list with all meals by date
     */
    public Iterable<Meal> listMeals(Calendar date, MealType mealType) {
        return MenuService.getMealsPublishedByDay(date, mealType);
    }

    public boolean doTransaction(Username id, Meal meal) throws DataConcurrencyException, DataIntegrityViolationException {
        Money mealPrice = meal.dish().currentPrice();
        Optional<CafeteriaUser> user = userService.findCafeteriaUserByUsername(id);
        if (userService.hasEnoughtMoney(user.get(), mealPrice)) {

            Transaction t = new Transaction(user.get(), mealPrice) {
                @Override
                public boolean movement(CafeteriaUser user, Money obj) {
                    Balance userBalance = userService.getBalanceOfUser(user.mecanographicNumber());
                    Money money = userBalance.currentBalance().subtract(mealPrice);
                    Balance newBalance = new Balance(money);

                    user.setCurrentBalance(newBalance);
                    userService.save(user);
                    return true;
                }
            };
            t.movement(user.get(), mealPrice);
            return true;
        } else {
            System.out.println("USER HASN'T ENOUGH MONEY");
            return false;
        }

    }

    public Booking persistBooking(final Username cafeteriaUser, final Calendar date,
            final BookingState bookingState, final Meal meal) throws DataIntegrityViolationException, DataConcurrencyException {

        AuthorizationService.ensurePermissionOfLoggedInUser(ActionRight.SELECT_MEAL);

        Optional<CafeteriaUser> user = userService.findCafeteriaUserByUsername(cafeteriaUser);

        final Booking newBooking = new Booking(meal, bookingState, user.get(), date);

        return this.repository.saveBooking(newBooking);
    }

    public void showAlergen(Meal meal) {
//         meal.dish().alergenInDish();
    }

    public boolean is24hBefore(Calendar date) {
        DateEAPLI dt = new DateEAPLI(date);
        if (dt.isTomorrow(date)) {
            return true;
        }
        return false;
    }
}
