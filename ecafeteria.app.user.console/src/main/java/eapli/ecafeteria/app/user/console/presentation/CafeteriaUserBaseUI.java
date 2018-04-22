/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates and open the template
 * in the editor.
 */
package eapli.ecafeteria.app.user.console.presentation;

import eapli.ecafeteria.application.cafeteriauser.CafeteriaUserBaseController;
import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.application.cafeteriauser.CafeteriaUserService;
import eapli.ecafeteria.domain.authz.Username;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.ecafeteria.persistence.TransactionRepository;
import eapli.framework.presentation.console.AbstractUI;
import java.util.Optional;

/**
 *
 * @author mcn
 */
public abstract class CafeteriaUserBaseUI extends AbstractUI {

    protected abstract CafeteriaUserBaseController controller();

    private final TransactionRepository repo = PersistenceContext.repositories().transactioRepository();
    private final CafeteriaUserService userService = new CafeteriaUserService();

    Username username = AuthorizationService.session().authenticatedUser().id();
    Optional<CafeteriaUser> user = userService.findCafeteriaUserByUsername(username);

    public String showBalance() {
        return "CURRENT BALANCE OF YOUR USERCARD: " + repo.getBalanceOfUser(user.get().mecanographicNumber());
    }

    @Override
    public String headline() {
        return "eCAFETERIA [@" + AuthorizationService.session().authenticatedUser().id() + "]   " + showBalance();
    }

    @Override
    protected void drawFormTitle(String title) {
        // drawFormBorder();
        final String titleBorder = BORDER.substring(0, 2) + " " + title;
        System.out.println(titleBorder);
        drawFormBorder();
    }
}
