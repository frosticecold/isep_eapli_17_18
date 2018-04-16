/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.menu.Menu;
import eapli.ecafeteria.persistence.MenuRepository;
import java.util.Calendar;
import java.util.Optional;
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

}
