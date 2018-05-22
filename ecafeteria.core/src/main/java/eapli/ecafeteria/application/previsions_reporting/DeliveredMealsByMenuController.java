package eapli.ecafeteria.application.previsions_reporting;

import eapli.ecafeteria.domain.menu.Menu;
import eapli.ecafeteria.domain.pos.DeliveryRegistry;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.application.Controller;
import java.util.List;

/**
 *
 * @author PedroEmanuelCoelho 1131485@isep.ipp.pt
 */
public class DeliveredMealsByMenuController implements Controller {

    private PrevisionsService service;

    public DeliveredMealsByMenuController() {

      this.service = new PrevisionsService();
    }

    /**
     * Checks if a certain menu exists by receiving its id
     *
     * @param m
     * @return
     */
    public boolean checkMenuExistence(Long idMenu) {

        boolean f = false;

        try {

            Menu m = PersistenceContext.repositories().menus().findOne(idMenu).get();
            f = true;
        } catch (Exception e) {

            f = false;
        }

        return f;
    }

    private Menu getMenu(Long idMenu) {

        return PersistenceContext.repositories().menus().findOne(idMenu).get();
    }

    /**
     * Returns a list of all the delivered meals by a certain menu
     *
     * @param menu
     * @return
     */
    public String prepareDeliveredMealsByMenuList(Long idMenu) {

        String msg = "============================================== DELIVERED MEALS BY MENU ===============================================\n";

        Menu menu = this.getMenu(idMenu);

        msg += "CURRENT MENU : " + menu.toString() + "\n\n";

        List<DeliveryRegistry> list = this.service.deliveredMealsByMenu(menu);

        if (list.isEmpty()) {

            msg += "Nothing to show \n";
        } else {

            for (int i = 0; i < list.size(); i++) {

                msg +=list.get(i).booking().getMeal().toString() + "\n" +  list.get(i).toString() + "\n\n";
            }

            msg += "\nTOTAL OF " + list.size() + "  DELIVERED MEALS LISTED\n";
        }

        msg += "===================================================================================================================================\n";

        return msg;
    }

}
