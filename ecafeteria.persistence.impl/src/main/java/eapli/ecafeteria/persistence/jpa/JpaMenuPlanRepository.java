/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.menu.Menu;
import eapli.ecafeteria.domain.menuplan.MenuPlan;
import eapli.ecafeteria.persistence.MenuPlanRepository;
import java.io.Serializable;
import javax.persistence.TypedQuery;


public class JpaMenuPlanRepository extends CafeteriaJpaRepositoryBase<MenuPlan,Long> implements MenuPlanRepository  {

    @Override
    public MenuPlan getActiveMenuPlan() {
          String where = "mp.closed=:isClosed";
        TypedQuery<MenuPlan> query = entityManager().createQuery("SELECT mp FROM MenuPlan mp WHERE " + where,
                this.entityClass);
        
        query.setParameter("isClosed",false);
        return query.getSingleResult();
    }

    @Override
    public MenuPlan getMenuPlanFromMenu(Menu m) {
          TypedQuery<MenuPlan> q;
        q = entityManager().createQuery("SELECT mp FROM MenuPlan mp WHERE mp.selectedMenu=:menu", MenuPlan.class);
        q.setParameter("menu", m);
        return q.getSingleResult();
    }
    
}
