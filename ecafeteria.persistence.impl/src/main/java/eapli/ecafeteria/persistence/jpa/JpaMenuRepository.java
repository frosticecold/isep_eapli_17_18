
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.meal.Meal;
import eapli.ecafeteria.domain.meal.MealType;
import eapli.ecafeteria.domain.menu.Menu;
import eapli.ecafeteria.domain.menu.MenuState;
import eapli.ecafeteria.persistence.MenuRepository;
import java.util.Calendar;
import java.util.Optional;
import javax.persistence.Query;
import javax.persistence.TemporalType;
import javax.persistence.TypedQuery;

/**
 *
 * @author Rui Ribeiro <1150344@isep.ipp.pt>
 */
public class JpaMenuRepository extends CafeteriaJpaRepositoryBase<Menu, Long> implements MenuRepository {

    @Override
    public Iterable<Menu> listValidMenus() {
        final Query q;
        String where = "e.menuState=:mstate";
        q = entityManager().createQuery("SELECT e FROM Menu e WHERE " + where, this.entityClass);
        
        q.setParameter("mstate", MenuState.UNPUBLISHED);

        return q.getResultList();
    }

    @Override
    public Optional<Menu> findMenuWithinPeriod(final Calendar startDate, final Calendar endDate) {
        String where = "e.period.startingDate=:sdate AND e.period.endingDate=:edate";
        TypedQuery<Menu> query = entityManager().createQuery("SELECT e FROM Menu e WHERE " + where,
                this.entityClass);
        //final Map<String, Object> params = new HashMap<>();
        query.setParameter("sdate", startDate, TemporalType.DATE);
        query.setParameter("edate", endDate, TemporalType.DATE);
        return query.getResultList().stream().findFirst();
    }

    public Optional<Menu> findMenuOnDate(Calendar cal){
        final Query q;
        q = entityManager().createQuery("SELECT e FROM Menu e WHERE :date=>e.period.startingDate AND :date1<=e.period.endingDate",Menu.class);
        q.setParameter("date", cal, TemporalType.DATE);
        q.setParameter("date1", cal, TemporalType.DATE);
        
        return q.getResultList().stream().findFirst();
    }

    @Override
    public Optional<Menu> findLatestMenu(Calendar cal) {
        final Query q;
        q = entityManager().createQuery("SELECT m FROM Menu m WHERE m.period.startingDate-:date=(SELECT MIN(m.period.startingDate-:date) FROM Menu m");
        q.setParameter("date", cal,TemporalType.DATE);
        
        return q.getResultList().stream().findFirst();
    }
}
