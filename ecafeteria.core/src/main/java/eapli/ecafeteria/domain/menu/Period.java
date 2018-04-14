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

    /*
    ============================================================================
                                    Variables
    ============================================================================
     */
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

    private static String DAY_FORMAT = "\\d\\d";
    private static String MONTH_FORMAT = "\\d\\d";
    private static String YEAR_FORMAT = "\\d{4}";
    private static String VALID_DATE_FORMAT = DAY_FORMAT + '-' + MONTH_FORMAT + '-' + YEAR_FORMAT;
    /**
     * 7 Working days (6 is difference between two dates)
     */
    private static final long WORKING_DAYS = 6;
    /**
     * Business Rules of how many hours to consider a Menu Critical
     */
    private static final int HOURS_TO_BE_CRITICAL = 72;

    /*
    ============================================================================
                                    Functions
    ============================================================================
     */
    private Period() {
    }

    /**
     * Constructs a Working week Period based in the dd-MM-yyy format for each
     * date string
     *
     * @author Raúl Correia
     * @param startingDayOfWeek Starting day String in the dd-MM-yyy format
     * @param endingDayOfWeek Ending day String in the dd-MM-yyyy format
     */
    protected Period(final String startingDayOfWeek, final String endingDayOfWeek) throws IllegalArgumentException {
        setWorkingPeriod(startingDayOfWeek, endingDayOfWeek);

    }

    /*
    ============================================================================
                                    Private Functions
    ============================================================================
     */
    /**
     * Method that saves the dates strings the the class member variables
     * Verifies if each string matches with the regex expressions Calls
     * verification if the dates are also valid within business rules
     *
     * @author Raúl Correia
     * @param startingDayOfWeek Starting day String in the dd-MM-yyy format
     * @param endingDayOfWeek Ending day String in the dd-MM-yyy format
     * @throws IllegalArgumentException if any date is invalid
     */
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

    /**
     * Method that validates if a startingdate and endingdate is within business
     * rules
     *
     * @author Raúl Correia
     * @param startingCalendar
     * @param endingCalender
     * @throws IllegalArgumentException
     */
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

    /*
    ============================================================================
                                    Protected Functions
    ============================================================================
     */
    /**
     * Method that checks if a Period is considered Critical within Business
     * Rules
     *
     * @return true if critical (less than 72hours), false ifnot.
     */
    protected boolean isCritical() {
        Calendar now = DateTime.now();
        long hours = DateTime.getDifferenceInHours(now, startingCalendar);
        /*
            ========================================
            To be verified with client if hours >= 0
            or accepts considering critical with negative hours (late publish)
            ========================================
         */
        return hours <= HOURS_TO_BE_CRITICAL;
    }
}
