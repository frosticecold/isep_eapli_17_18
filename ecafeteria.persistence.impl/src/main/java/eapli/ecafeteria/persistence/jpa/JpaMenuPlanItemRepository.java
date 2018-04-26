/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.menuplan.MenuPlan;
import eapli.ecafeteria.domain.menuplan.MenuPlanItem;
import eapli.ecafeteria.persistence.MenuPlanItemRepository;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

/**
 *
 * @author pedro
 */
public class JpaMenuPlanItemRepository extends CafeteriaJpaRepositoryBase<MenuPlanItem, Long> implements MenuPlanItemRepository {

    @Override
    public List<MenuPlanItem> getMenuPlanItemFromMenuPlan(MenuPlan mp) {
           String where = "mpi.menuplan=:menuPlan";
        TypedQuery<MenuPlanItem> query = entityManager().createQuery("SELECT mpi FROM MenuPlanItem mpi WHERE " + where,
                this.entityClass);

        query.setParameter("menuPlan",mp);
        return query.getResultList();
    }


}
