/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.pos;

import eapli.ecafeteria.domain.dishes.DishType;
import eapli.ecafeteria.domain.meal.MealType;
import eapli.framework.util.DateTime;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Class for information about number of available dishes per dish type in a
 * given day and meal type.
 *
 * @author Miguel Santos <1161386@isep.ipp.pt>
 */
public class AvailableMealsStatistics {

    private Calendar day;
    private MealType mealType;
    private Map<DishType, Integer> mapDishtypeQuantity;
    private Map<DishType, Integer> mapWithReservedQuantity;
    private Map<DishType, Integer> mapWithRemaningQuantity;

    public AvailableMealsStatistics(Calendar day, MealType mealType) {
        this.day = day;
        this.mealType = mealType;
        this.mapDishtypeQuantity = new HashMap<>();
        this.mapWithReservedQuantity = new HashMap<>();
    }

    private boolean addToMap(Map<DishType, Integer> map, DishType dishType, Integer quantity) {
        if (map == null || dishType == null || quantity == null) {
            return false;
        }

        map.put(dishType, quantity);
        return true;
    }

    public boolean addDishTypeQuantity(DishType dt, Integer qt) {
        if (!mapDishtypeQuantity.containsKey(dt)) {
            mapDishtypeQuantity.put(dt, 0);
        }
        return addToMap(mapDishtypeQuantity, dt, qt);
    }

    public boolean addDishTypeReservedQuantity(DishType dt, Integer qt) {
        if (!mapWithReservedQuantity.containsKey(dt)) {
            mapWithReservedQuantity.put(dt, 0);
        }
        return addToMap(mapWithReservedQuantity, dt, qt);
    }

    public Map<DishType, Integer> calcRemainingDishes() {
        Map<DishType, Integer> map = new LinkedHashMap<>();
        for (DishType dt : mapDishtypeQuantity.keySet()) {
            Integer dif = mapDishtypeQuantity.get(dt) - mapWithReservedQuantity.get(dt);
            map.put(dt, dif);
        }
        mapWithRemaningQuantity = map;
        return map;
    }

    @Override
    public String toString() {
        if (mapWithRemaningQuantity == null) {
            calcRemainingDishes();
        }
        String output = "";
        output += "Day: " + DateTime.dayNameFromCalendar(day);
        output += "MealType: " + mealType;
        for (DishType dt : mapDishtypeQuantity.keySet()) {
            output += String.format(("DishType: %s\n Total:%d\nReserved:%d\nAvailable:%d"),
                    dt, mapDishtypeQuantity.get(dt),
                    mapWithReservedQuantity.get(dt),
                    mapWithRemaningQuantity.get(dt));
        }
        return output;
    }

}
