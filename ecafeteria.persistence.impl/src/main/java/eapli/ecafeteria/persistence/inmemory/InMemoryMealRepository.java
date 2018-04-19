package eapli.ecafeteria.persistence.inmemory;

import eapli.ecafeteria.domain.meal.*;
import eapli.ecafeteria.persistence.*;
import eapli.framework.persistence.repositories.impl.inmemory.*;
import java.util.*;

public class InMemoryMealRepository extends InMemoryRepositoryWithLongPK<Meal> implements MealRepository {
    @Override
    public List<Meal> listOfMealsByDateAndMealType(Calendar date, MealType mealType) {
        Iterable<Meal> meals = findAll();
        List<Meal> thisOne = new ArrayList<>();

        for (Meal m : meals) {
            if (m.isOnGivenDate(date) && m.mealtype() == mealType) {
                thisOne.add(m);
            }
        }

        return thisOne;
    }

    @Override
    public void delete(Meal entity) throws DataIntegrityViolationException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Long entityId) throws DataIntegrityViolationException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Meal save(Meal entity) throws DataConcurrencyException, DataIntegrityViolationException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Iterable<Meal> findAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Optional<Meal> findOne(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public long count() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Optional<Meal> findMealByDishID(Designation id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
