/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
package eapli.ecafeteria.application.cafeteriauser;

import eapli.ecafeteria.domain.cafeteriauser.Balance;
import eapli.ecafeteria.domain.cafeteriauser.MecanographicNumber;
import eapli.framework.application.Controller;
import eapli.framework.domain.money.Money;

/**
 *
 * @author mcn
 */
public class CafeteriaUserBaseController implements Controller {

    private final CafeteriaUserService userService = new CafeteriaUserService();

    public Money balance() {
        // TODO get the actual balance of the user
        return Money.euros(0);
    }

    public Balance getBalanceOfUser(MecanographicNumber user) {
        return userService.getBalanceOfUser(user);
    }

}
