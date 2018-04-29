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
    private Map<DishType, Long> mapDishtypeQuantity;
    private Map<DishType, Long> mapWithReservedQuantity;
    private Map<DishType, Long> mapWithRemaningQuantity;
    private Map<DishType, Long> mapServedQuantity;

    public AvailableMealsStatistics(Calendar day, MealType mealType) {
        this.day = day;
        this.mealType = mealType;
        this.mapDishtypeQuantity = new HashMap<>();
        this.mapWithReservedQuantity = new HashMap<>();
        this.mapServedQuantity = new HashMap<>();
    }

    private boolean addToMap(Map<DishType, Long> map, DishType dishType, Long quantity) {
        if (map == null || dishType == null || quantity == null) {
            return false;
        }

        map.put(dishType, quantity);
        return true;
    }

    public boolean addDishTypeQuantity(DishType dt, Long qt) {
        if (!mapDishtypeQuantity.containsKey(dt)) {
            mapDishtypeQuantity.put(dt, (long) 0);
        }
        return addToMap(mapDishtypeQuantity, dt, qt);
    }

    public boolean addDishTypeReservedQuantity(DishType dt, Long qt) {
        if (!mapWithReservedQuantity.containsKey(dt)) {
            mapWithReservedQuantity.put(dt, (long) 0);
        }
        return addToMap(mapWithReservedQuantity, dt, qt);
    }
    
    public boolean addDishTypeServedQuantity(DishType dt, Long qt){
        if (!mapServedQuantity.containsKey(dt)) {
            mapServedQuantity.put(dt, (long) 0);
        }
        return addToMap(mapServedQuantity, dt, qt);
    }

    private Map<DishType, Long> calcRemainingDishes() {
        Map<DishType, Long> map = new LinkedHashMap<>();
        for (DishType dt : mapDishtypeQuantity.keySet()) {
            Long dif = mapDishtypeQuantity.get(dt) - mapServedQuantity.get(dt);
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
        output += "========================================================\n";
        output += "Day: " + DateTime.dayNameFromCalendar(day);
        output += " MealType: " + mealType + "\n";
        output += "========================================================\n";
        for (DishType dt : mapDishtypeQuantity.keySet()) {
            output += String.format(("DishType: %s\nTotal:%d\nReserved:%d\nAvailable:%d\n"),
                    dt, mapDishtypeQuantity.get(dt),
                    mapWithReservedQuantity.get(dt),
                    mapWithRemaningQuantity.get(dt));
            output += "========================================================\n";
        }
        return output;
    }

}
