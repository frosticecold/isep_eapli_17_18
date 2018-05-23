/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.ecafeteria.domain.menu;

import eapli.framework.date.DateEAPLI;
import eapli.framework.util.DateTime;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

/**
 *
 * @author Raúl Correia <1090657@isep.ipp.pt>
 */
@Embeddable
public class Period implements Serializable {

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
    @Column(name = "startdate")
    @Temporal(TemporalType.DATE)
    private Calendar startingDate;

    /**
     * Ending date of working Period Business Rules says it should be a sunday
     */
    @Column(name = "enddate")
    @Temporal(TemporalType.DATE)
    private Calendar endingDate;

    /**
     * Business Rules of how many hours to consider a Menu Critical
     */
    @Transient
    private static final int HOURS_TO_BE_CRITICAL = 72;

    /**
     * 7 Working days (6 is difference between two dates)
     */
    @Transient
    public static final long WORKING_DAYS = 6;

    /*
    ============================================================================
                                    Functions
    ============================================================================
     */
    protected Period() {
    }

    /**
     * Constructs a Period for a Working week with Calenders as arguments
     *
     * @author Raúl Correia
     * @param startingOfWeek
     * @param endOfWeek
     *
     */
    protected Period(final Calendar startingOfWeek, final Calendar endOfWeek) throws IllegalArgumentException {
        setWorkingPeriod(startingOfWeek, endOfWeek);
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
     * string Not yet implemented, use simpledataformat
     *
     * @author Raúl Correia
     * @param startingDayOfWeek Starting day String in the dd-MM-yyy format
     * @param endingDayOfWeek Ending day String in the dd-MM-yyyy format
     * @param simpledataformat
     */
    protected Period(final String startingDayOfWeek, final String endingDayOfWeek, String simpledataformat) throws IllegalArgumentException {
        setWorkingPeriod(startingDayOfWeek, endingDayOfWeek, simpledataformat);

    }

    /*
    ============================================================================
                                    Private Functions
    ============================================================================
     */
    private void setWorkingPeriod(final Calendar startCalendar, final Calendar endCalendar) {
        this.startingDate = startCalendar;
        this.endingDate = endCalendar;
        validateBusinessWorkingDays(startingDate, endingDate);

    }

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

        startingDate = DateTime.parseDate(startingDayOfWeek, simpledataformat);
        endingDate = DateTime.parseDate(endingDayOfWeek, simpledataformat);
        validateBusinessWorkingDays(startingDate, endingDate);
    }

    /**
     * Method that validates if a startingdate and endingdate is within business
     * rules
     * <p>
     *  * Must start on a Sunday * Must end on a Saturday * 6 Working Days * *
     * Must not be in the past
     *
     * @author Raúl Correia
     * @param start
     * @param end
     * @throws IllegalArgumentException
     */
    private void validateBusinessWorkingDays(Calendar start, Calendar end) throws IllegalArgumentException {

        if (start.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
            throw new IllegalArgumentException("Starting working day should be SUNDAY.");
        }
        if (end.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY) {
            throw new IllegalArgumentException("Ending working day should be SATURDAY.");
        }
        if (DateTime.isAfterNow(start)) {
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
        long hours = DateTime.getDifferenceInHours(now, startingDate);
        /*
            ========================================
            To be verified with client if hours >= 0
            or accepts considering critical with negative hours (late publish)
            ========================================
         */
        return hours <= HOURS_TO_BE_CRITICAL;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + Objects.hashCode(this.startingDate);
        hash = 71 * hash + Objects.hashCode(this.endingDate);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Period other = (Period) obj;
        if (!Objects.equals(this.startingDate, other.startingDate)) {
            return false;
        }
        if (!Objects.equals(this.endingDate, other.endingDate)) {
            return false;
        }
        return true;
    }

    /**
     * Method that returns a map with the working days of a period, orderered by
     * 0,1,2,3,4,5,6
     * <p>
     * Map index is 0 based;
     * <p>
     * Sunday Monday Tuesday Wednesday Thursday Friday Saturday
     *
     * @return
     */
    public Map<Integer, Calendar> getWorkingDays() {
        Map<Integer, Calendar> map = new LinkedHashMap<>();
        Calendar cal = (Calendar) startingDate.clone();
        int index = 0;
        Calendar end = (Calendar) endingDate.clone();
        end.add(Calendar.DATE, 1);
        while (cal.get(Calendar.DAY_OF_YEAR) != end.get(Calendar.DAY_OF_YEAR)) {
            map.put(index, cal);
            index++;
            cal = (Calendar) cal.clone();
            cal.add(Calendar.DATE, 1);
        }
        return map;

    }

    /**
     * Method that returns an Iterable Of Calendars as a LinkedList
     * <p>
     * METHOD ENSURES DAY ORDER
     * <p>
     * Sunday Monday Tuesday Wednesday Thursday Friday Saturday
     *
     * @return
     */
    public Iterable<Calendar> getWorkingDaysIterable() {
        List<Calendar> list = new LinkedList<>();
        for (Entry<Integer, Calendar> entry : getWorkingDays().entrySet()) {
            list.add(entry.getValue());
        }
        return list;
    }

    @Override
    public String toString() {
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        return "Period{" + "startingDate=" + format.format(startingDate.getTime()) + ", endingDate=" + format.format(endingDate.getTime()) + '}';
    }

}
