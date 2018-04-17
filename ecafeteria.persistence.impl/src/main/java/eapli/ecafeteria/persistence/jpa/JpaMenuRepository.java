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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
    
    public Iterable<Meal> listMealsPublishedMenu(Calendar date, MealType mealType){
        
        final Query q = entityManager().
                createQuery("SELECT meal.* "
                        + "FROM Menu menu, Meal meal"
                        + "WHERE menu.menuState=:state"
                        + "AND :date BETWEEN (menu.period.startingDatesdate AND menu.period.endingDate)"
                        + "AND :mealtype = meal.mealtype", this.entityClass);
        
        q.setParameter("date", date, TemporalType.DATE);
        q.setParameter("state", MenuState.PUBLISHED);
        q.setParameter("mealtype", mealType);
        
        return q.getResultList();
    }
}
