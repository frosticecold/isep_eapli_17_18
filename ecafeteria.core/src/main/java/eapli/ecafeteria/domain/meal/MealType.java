/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.meal;

/**
 * @author Raúl Correia <1090657@isep.ipp.pt>
 */
public enum MealType {
    LUNCH(0), DINNER(1);

    int id;

    MealType(int i) {
        this.id = i;
    }

    public int mealtype() {
        return this.id;
    }

    public static MealType getMealTypeById(int id) {
        switch (id) {
            case 0:
                return LUNCH;
            case 1:
                return DINNER;
        }
        return null;
    }
}
