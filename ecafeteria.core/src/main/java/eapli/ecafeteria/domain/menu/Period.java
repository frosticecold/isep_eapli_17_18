/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.menu;

import eapli.framework.date.DateEAPLI;
import eapli.framework.util.DateTime;
import java.util.Calendar;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Transient;

/**
 *
 * @author Raúl Correia <1090657@isep.ipp.pt>
 */
@Embeddable
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
    @AttributeOverrides({
        @AttributeOverride(name = "dday", column = @Column(name = "start_day"))
        ,
    @AttributeOverride(name = "dmonth", column = @Column(name = "start_month"))
        ,
    @AttributeOverride(name = "dyear", column = @Column(name = "start_year"))
    })
    private DateEAPLI startingDate;

    /**
     * Ending date of working Period Business Rules says it should be a sunday
     */
    @AttributeOverrides({
        @AttributeOverride(name = "dday", column = @Column(name = "end_day"))
        ,
    @AttributeOverride(name = "dmonth", column = @Column(name = "end_month"))
        ,
    @AttributeOverride(name = "dyear", column = @Column(name = "end_year"))
    })
    private DateEAPLI endingDate;

    /**
     * 7 Working days (6 is difference between two dates)
     */
    @Transient
    private static final long WORKING_DAYS = 6;
    /**
     * Business Rules of how many hours to consider a Menu Critical
     */
    @Transient
    private static final int HOURS_TO_BE_CRITICAL = 72;

    /*
    ============================================================================
                                    Functions
    ============================================================================
     */
    private Period() {
    }

    /**
     * Constructs a Period for a Working week based in the dd-MM-yyy format for
     * each date string
     *
     * @author Raúl Correia
     * @param startingDayOfWeek Starting day String in the dd-MM-yyy format
     * @param endingDayOfWeek Ending day String in the dd-MM-yyyy format
     */
    protected Period(final String startingDayOfWeek, final String endingDayOfWeek) throws IllegalArgumentException {
        setWorkingPeriod(startingDayOfWeek, endingDayOfWeek, DateEAPLI.SIMPLE_DATA_FORMAT);

    }

    /**
     * Constructs a Working week Period based in a custom format for each date
     * string
     * Not yet implemented, use simpledataformat
     *
     * @author Raúl Correia
     * @param startingDayOfWeek Starting day String in the dd-MM-yyy format
     * @param endingDayOfWeek Ending day String in the dd-MM-yyyy format
     * @param dataFormatRegex
     */
    protected Period(final String startingDayOfWeek, final String endingDayOfWeek, String simpledataformat) throws IllegalArgumentException {
        setWorkingPeriod(startingDayOfWeek, endingDayOfWeek, simpledataformat);

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
     * @param startingDayOfWeek Starting day String
     * @param endingDayOfWeek Ending day String
     * @throws IllegalArgumentException if any date is invalid
     */
    private void setWorkingPeriod(final String startingDayOfWeek, final String endingDayOfWeek, final String simpledataformat) throws IllegalArgumentException {

        startingDate = new DateEAPLI(startingDayOfWeek,simpledataformat);
        endingDate = new DateEAPLI(endingDayOfWeek,simpledataformat);
        validateBusinessWorkingDays(startingDate, endingDate);
    }

    /**
     * Method that validates if a startingdate and endingdate is within business
     * rules
     *
     * @author Raúl Correia
     * @param startingDate
     * @param endingDate
     * @throws IllegalArgumentException
     */
    private void validateBusinessWorkingDays(DateEAPLI startingDate, DateEAPLI endingDate) throws IllegalArgumentException {
        final Calendar start = startingDate.toCalendar();
        final Calendar end = endingDate.toCalendar();

        if (!startingDate.isWhatTypeOfDay(Calendar.SUNDAY)) {
            throw new IllegalArgumentException("Starting working day should be SUNDAY.");
        }
        if (!endingDate.isWhatTypeOfDay(Calendar.SATURDAY)) {
            throw new IllegalArgumentException("Starting working day should be SATURDAY.");
        }
        if (DateTime.isAfterNow(start)) {
            throw new IllegalArgumentException("Can't put a working date before present. You can't time travel... ");
        }
        if (DateTime.isAfterNow(end)) {
            throw new IllegalArgumentException("Can't put a working date before present. You can't time travel... ");
        }
        if (DateTime.isBefore(end, start)) {
            throw new IllegalArgumentException("Can't put a endingDate before the startingDate.");
        }
        if (!DateTime.validateDifferenceInDays(start, end, WORKING_DAYS)) {
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
        long hours = DateTime.getDifferenceInHours(now, startingDate.toCalendar());
        /*
            ========================================
            To be verified with client if hours >= 0
            or accepts considering critical with negative hours (late publish)
            ========================================
         */
        return hours <= HOURS_TO_BE_CRITICAL;
    }
}
