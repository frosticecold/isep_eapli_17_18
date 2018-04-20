/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.menuplan.MenuPlan;
import eapli.ecafeteria.persistence.MenuPlanRepository;
import java.io.Serializable;
import javax.persistence.TypedQuery;


public class JpaMenuPlanRepository extends CafeteriaJpaRepositoryBase<MenuPlan,Long> implements MenuPlanRepository  {

    @Override
    public MenuPlan saveMenuPlan(MenuPlan menuplan) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public MenuPlan getActiveMenuPlan() {
          String where = "mp.closed=:isClosed";
        TypedQuery<MenuPlan> query = entityManager().createQuery("SELECT mp FROM MenuPlan mp WHERE " + where,
                this.entityClass);
        
        query.setParameter("isClosed",false);
        return query.getSingleResult();
    }
    
}
