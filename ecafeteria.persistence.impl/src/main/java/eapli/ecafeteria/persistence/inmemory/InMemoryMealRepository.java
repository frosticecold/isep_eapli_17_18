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
}