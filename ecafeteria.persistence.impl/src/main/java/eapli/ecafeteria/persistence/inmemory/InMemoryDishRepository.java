package eapli.ecafeteria.persistence.inmemory;

import eapli.ecafeteria.domain.cafeteriauser.CafeteriaUser;
import java.util.Optional;

import eapli.ecafeteria.domain.dishes.Dish;
import eapli.ecafeteria.domain.dishes.DishType;
import eapli.ecafeteria.persistence.DishRepository;
import eapli.framework.domain.Designation;
import eapli.framework.persistence.repositories.impl.inmemory.InMemoryRepository;
import java.util.Calendar;
import java.util.List;

/**
 * Created by MCN on 29/03/2016.
 */
public class InMemoryDishRepository extends InMemoryRepository<Dish, Designation> implements DishRepository {

    @Override
    public Optional<Dish> findByName(Designation name) {
        return matchOne(e -> e.name().equals(name));
    }

    @Override
    protected Designation newKeyFor(Dish entity) {
        return entity.id();
    }

    @Override
    public Iterable<Dish> findByDishType(DishType dishtype) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Iterable<Dish> findServedDishesBetween(CafeteriaUser user,Calendar initialDate, Calendar finalDate) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Dish> findListAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
