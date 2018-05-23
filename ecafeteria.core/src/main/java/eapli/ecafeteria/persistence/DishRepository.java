package eapli.ecafeteria.persistence;

import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import java.util.Optional;

import eapli.ecafeteria.domain.dishes.Dish;
import eapli.ecafeteria.domain.dishes.DishType;
import eapli.framework.domain.Designation;
import eapli.framework.persistence.repositories.DataRepository;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author Jorge Santos ajs@isep.ipp.pt 02/04/2016
 */
public interface DishRepository extends DataRepository<Dish, Designation> {

	Optional<Dish> findByName(Designation name);
        
        Iterable<Dish> findByDishType(DishType dishtype);
        
        Iterable<Dish> findServedDishesBetween(CafeteriaUser user,Calendar initialDate,Calendar finalDate);

        List<Dish> findListAll();
}
