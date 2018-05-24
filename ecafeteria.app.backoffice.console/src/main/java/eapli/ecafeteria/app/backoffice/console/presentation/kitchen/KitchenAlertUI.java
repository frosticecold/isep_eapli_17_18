/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.app.backoffice.console.presentation.kitchen;

import eapli.ecafeteria.Application;
import eapli.ecafeteria.app.backoffice.console.presentation.Alert.AlertUI;
import eapli.ecafeteria.application.KitchenAlert.LimitConfigurationController;
import eapli.ecafeteria.application.authz.AuthorizationService;
import eapli.ecafeteria.domain.authz.ActionRight;
import eapli.framework.actions.ReturnAction;
import eapli.framework.presentation.console.HorizontalMenuRenderer;
import eapli.framework.presentation.console.Menu;
import eapli.framework.presentation.console.MenuItem;
import eapli.framework.presentation.console.MenuRenderer;
import eapli.framework.presentation.console.VerticalMenuRenderer;

/**
 *
 * @author Car
 */
public class KitchenAlertUI extends AlertUI {

    private LimitConfigurationController controller = new LimitConfigurationController();
     private static final int EXIT_OPTION = 0;
    private static final int SET_CHANGE_LIMIT_OPTION = 1;

    @Override
    protected boolean doShow() {
    
        final Menu menu = buildMenu();
        final MenuRenderer renderer;
        if (Application.settings().isMenuLayoutHorizontal()) {
            renderer = new HorizontalMenuRenderer(menu);
        } else {
            renderer = new VerticalMenuRenderer(menu);
        }
        return renderer.show();
    
    }
    public Menu buildMenu(){
        
         final Menu menu = new Menu("Settings >");

        menu.add(new MenuItem(SET_CHANGE_LIMIT_OPTION, "Change Alert Limit",
                new ChangeLimitAction()));
        menu.add(new MenuItem(EXIT_OPTION, "Return ", new ReturnAction()));

        return menu;
    
    }
    @Override
    public String headline() {
        return super.headline() + "Kitchen Alert Configuration";
    }

}
