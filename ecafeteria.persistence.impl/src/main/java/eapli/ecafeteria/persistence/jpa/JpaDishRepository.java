package eapli.ecafeteria.persistence.jpa;

import eapli.ecafeteria.domain.booking.BookingState;
import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import eapli.ecafeteria.domain.dishes.Dish;
import eapli.ecafeteria.domain.dishes.DishType;
import eapli.ecafeteria.persistence.DishRepository;
import eapli.framework.domain.Designation;
import java.util.Calendar;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author Jorge Santos ajs@isep.ipp.pt 02/04/2016
 */
class JpaDishRepository extends CafeteriaJpaRepositoryBase<Dish, Designation> implements DishRepository {

    @Override
    public Optional<Dish> findByName(Designation name) {
        final Map<String, Object> params = new HashMap<>();
        params.put("name", name);
        return matchOne("e.name=:name", params);
    }

    @Override
    public Iterable<Dish> findByDishType(DishType dishtype) {
        final Map<String, Object> params = new HashMap<>();
        params.put("dishtype", dishtype);
        return match("e.dishType=:dishtype", params);
    }

    @Override
    public List<Dish> findListAll() {
        String query = "SELECT d "
                + "FROM Dish d";

        final Query q = entityManager().createQuery(query, this.entityClass);

        return q.getResultList();

    }

    /**
     * Returns a list of dishes that were served to a user between a period of time
     *
     * @param initialDate
     * @param finalDate
     * @return
     */
    public Iterable<Dish> findServedDishesBetween(CafeteriaUser user, Calendar initialDate, Calendar finalDate) {
        BookingState state = new BookingState();
        state.changeToServed();
        
        Query query = entityManager().createQuery(
                "SELECT b.meal.dish FROM Booking b "
                + "WHERE b.bookingState.actualBookingState=:bsl "
                + "AND b.meal.date >= :initialdate "
                + "AND b.meal.date <= :finaldate "
                + "AND b.cafeteriaUser = :user");

        query.setParameter("bsl", state.actualState());
        query.setParameter("initialdate", initialDate);
        query.setParameter("finaldate", finalDate);
        query.setParameter("user",user);

        return query.getResultList();
    }
}
