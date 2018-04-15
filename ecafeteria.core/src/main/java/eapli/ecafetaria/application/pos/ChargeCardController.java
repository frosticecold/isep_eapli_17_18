/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafetaria.application.pos;

import eapli.ecafeteria.application.cafeteriauser.CafeteriaUserService;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.framework.application.Controller;
import eapli.framework.domain.money.Money;
import java.util.Optional;

/**
 *
 * @author MarioDias
 */
public class ChargeCardController implements Controller {

    private final CafeteriaUserService service = new CafeteriaUserService();

    public CafeteriaUser findCafeteriaUserByMecanographicNumber(String mecanographicNumber) {
        Optional<CafeteriaUser> user = service.findCafeteriaUserByMecNumber(mecanographicNumber);
        if (user.isPresent()) {
            return user.get();
        }
        return null;
    }

    public boolean chargeCafeteriaUserCard(CafeteriaUser user, Money creditToCharge) {
        return user.addCredits(creditToCharge);
    }
    
    
}
