/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.menu;

import eapli.framework.util.DateTime;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Raúl Correia <1090657@isep.ipp.pt>
 */
public class Period {

    private Calendar start;
    private Calendar end;

    private static String DAY_FORMAT = "\\d\\d)";
    private static String MONTH_FORMAT = "\\d\\d";
    private static String YEAR_FORMAT = "\\d{4}";
    private static String VALID_DATE_FORMAT = DAY_FORMAT + '-' + MONTH_FORMAT + '-' + YEAR_FORMAT;

    private Period() {
    }

    /**
     * Constructs a Working week Period based on the dd-MM-yyy format for each
     * string
     *
     * @param startingDayOfWeek
     * @param endingDayOfWeek
     */
    private Period(final String startingDayOfWeek, final String endingDayOfWeek) {
        start = DateTime.parseDate(startingDayOfWeek);
        end = DateTime.parseDate(endingDayOfWeek);
    }

    private boolean setWorkingPeriod(final String startingDayOfWeek, final String endingDayOfWeek) throws IllegalArgumentException {
        Pattern regex = Pattern.compile(VALID_DATE_FORMAT);
        Matcher match = regex.matcher(startingDayOfWeek);
        if (match.find()) {
            start = DateTime.parseDate(match.group(0));
        }

        match = regex.matcher(endingDayOfWeek);
        if (match.find()) {
            end = DateTime.parseDate(match.group(0));
        }
        
       return true;
    }
}
