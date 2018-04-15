/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.meal;

import eapli.framework.util.DateTime;
import java.util.Calendar;

/**
 *
 * @author Beatriz Ferreira <1160701@isep.ipp.pt>
 */
public class Meal {

    /**
     * Date of a Meal
     *
     * @author Raúl Correia
     */
    private Calendar date;

    /**
     * For ORM
     */
    protected Meal() {

    }

    public boolean isOnGivenDate(Calendar givenDate) {
        return DateTime.isSameDate(givenDate, date);
    }

}
