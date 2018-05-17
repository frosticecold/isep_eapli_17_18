package eapli.ecafeteria.bootstrapers;

import eapli.ecafeteria.domain.menu.Menu;
import eapli.ecafeteria.domain.menuplan.MenuPlan;
import eapli.ecafeteria.persistence.PersistenceContext;
import eapli.framework.actions.Action;
import eapli.framework.util.DateTime;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Logger;

/**
 *
 * @author PedroEmanuelCoelho 1131485@isep.ipp.pt
 */
public class MenuPlanBootstrapper implements Action {

    @Override
    public boolean execute() {
        boolean flag = false;
        Calendar start = DateTime.parseDate("01-07-2018");
        Calendar end = DateTime.parseDate("07-07-2018");
        List<Menu> list = PersistenceContext.repositories().menus().findLatestMenus();
        int pos = list.size() - 1;
        Menu menu = list.get(pos);
        MenuPlan menuPlan = new MenuPlan(menu); //set open by default
        
        menuPlan.setClosed(true);

        try {
            PersistenceContext.repositories().menuPlan().save(menuPlan);
            flag = true;
        } catch (Exception e) {
            Logger.getLogger(ECafeteriaBootstrapper.class.getSimpleName())
                    .info("EAPLI-DI001: bootstrapping existing record");
        }

        return flag;
    }

}
