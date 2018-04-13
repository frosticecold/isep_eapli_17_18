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

    /**
     * Generic implementation of a working Period between two dates Business
     * rules dictates the starting date should be a Monday and ending date
     * should be a sunday
     * <p>
     *
     */
    /**
     * Starting date of a working Period Business Rules says it should be a
     * monday
     */
    private Calendar startingCalendar;

    /**
     * Ending date of working Period Business Rules says it should be a sunday
     */
    private Calendar endingCalendar;

    private static String DAY_FORMAT = "\\d\\d)";
    private static String MONTH_FORMAT = "\\d\\d";
    private static String YEAR_FORMAT = "\\d{4}";
    private static String VALID_DATE_FORMAT = DAY_FORMAT + '-' + MONTH_FORMAT + '-' + YEAR_FORMAT;
    /**
     * 7 Working days (6 is difference between two dates)
     */
    private static final long WORKING_DAYS = 6;

    private Period() {
    }

    /**
     * Constructs a Working week Period based on the dd-MM-yyy format for each
     * string
     *
     * @param startingDayOfWeek
     * @param endingDayOfWeek
     */
    protected Period(final String startingDayOfWeek, final String endingDayOfWeek) throws IllegalArgumentException {
        try {
            setWorkingPeriod(startingDayOfWeek, endingDayOfWeek);
        } catch (IllegalArgumentException ex) {
            throw new IllegalArgumentException(ex.getMessage());
        }
    }

    private void setWorkingPeriod(final String startingDayOfWeek, final String endingDayOfWeek) throws IllegalArgumentException {
        Pattern regex = Pattern.compile(VALID_DATE_FORMAT);
        Matcher match = regex.matcher(startingDayOfWeek);
        if (match.find()) {
            startingCalendar = DateTime.parseDate(match.group(0));
            startingCalendar.setFirstDayOfWeek(Calendar.MONDAY);
        }

        match = regex.matcher(endingDayOfWeek);
        if (match.find()) {
            endingCalendar = DateTime.parseDate(match.group(0));
            endingCalendar.setFirstDayOfWeek(Calendar.MONDAY);
        }
        if (startingCalendar == null || endingCalendar == null) {
            throw new IllegalArgumentException("Error, date format should be in dd-MM-yyyy");
        }
        validateBusinessWorkingDays(startingCalendar, endingCalendar);
    }

    private void validateBusinessWorkingDays(Calendar startingCalendar, Calendar endingCalender) throws IllegalArgumentException {
        if (startingCalendar.get(Calendar.DAY_OF_WEEK) != Calendar.MONDAY) {
            throw new IllegalArgumentException("Starting working day should be MONDAY.");
        }
        if (endingCalender.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
            throw new IllegalArgumentException("Starting working day should be SUNDAY.");
        }
        if (DateTime.isAfterNow(startingCalendar)) {
            throw new IllegalArgumentException("Can't put a working date before present. You can't time travel... ");
        }
        if (DateTime.isAfterNow(endingCalender)) {
            throw new IllegalArgumentException("Can't put a working date before present. You can't time travel... ");
        }
        if (DateTime.isBefore(startingCalendar, endingCalender)) {
            throw new IllegalArgumentException("Can't put a startingDate before the endingDate.");
        }
        if (!DateTime.validateDifferenceInDays(startingCalendar, endingCalender, WORKING_DAYS)) {
            throw new IllegalArgumentException("Can only select 7 working days, not more.");
        }
    }
}
