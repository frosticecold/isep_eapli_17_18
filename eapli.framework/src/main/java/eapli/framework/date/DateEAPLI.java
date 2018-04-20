/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.framework.date;

import eapli.framework.util.DateTime;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Thin Date implementation for EAPLI Ecafeteria Project use
 * <p>
 * Name is odd so it doesnt get mismatched with depreciated Java Class Date
 * <p>
 * Column names are odd because if conflicts with SQL variable names
 *
 * @author Raúl Correia <1090657@isep.ipp.pt>
 */
public class DateEAPLI {

    /*
    ============================================================================
                                    Variables
    ============================================================================
     */
    private int m_day;
    private int m_month;
    private int m_year;

    public static final String SIMPLE_DATA_FORMAT = "dd-MM-yyyy";
    public static final String DEFAULT_DATE_FORMAT_REGEX = "\\d{2}-\\d{2}-\\d{4}";

    /*
    ============================================================================
                                    Functions
    ============================================================================
     */
    /**
     * Private constructor for inner functions that does no validation
     *
     * @author Raúl Correia
     * @param year year
     * @param month month
     * @param day day
     * @param novalidationhere Implying there is no validation
     */
    private DateEAPLI(final int year, final int month, final int day, int novalidationhere) {
        m_year = year;
        m_month = month;
        m_day = day;

    }

    public DateEAPLI(final Calendar calendar) {
        m_day = calendar.get(Calendar.DAY_OF_MONTH);
        m_month = calendar.get(Calendar.MONTH) + 1;
        m_year = calendar.get(Calendar.YEAR);

    }

    /**
     * Constructor for a DateEAPLI that receives a date and a custom regex
     * format if format is null, it uses the default dd-MM-yyyy format
     *
     * @author Raúl Correia
     * @param date String representing a date
     * @param simpledataformat Format that the date is represented in
     */
    public DateEAPLI(final String date, final String simpledataformat) throws IllegalArgumentException {
        parseDateAsString(date);

    }

    /**
     * Constructor for a DateEAPLI that receives a date as year, month, day
     *
     * @author Raúl Correia
     * @param year integer representing Year
     * @param month integer representing Month
     * @param day integer representing Day
     */
    public DateEAPLI(final int year, final int month, final int day) throws IllegalArgumentException {
        parseDateAsNumbers(year, month, day);
    }

    /*
    ============================================================================
                                    Private Functions
    ============================================================================
     */
    /**
     * Method that parses a given date string with a given format
     *
     * @author Raúl Correia
     * @param date
     * @param format
     * @throws IllegalArgumentException
     */
    private void parseDateAsString(final String date) throws IllegalArgumentException {
        String finalformat = "[0-9]{1,2}-[0-9]{1,2}-[0-9]{4}";
        Pattern p = Pattern.compile(finalformat);
        Matcher m = p.matcher(date);

        if (!m.matches()) {
            throw new IllegalArgumentException("Invalid date. Please check valid input format.");
        }
        Calendar cal = DateTime.parseDate(date, SIMPLE_DATA_FORMAT);
        m_day = cal.get(Calendar.DAY_OF_MONTH);
        m_month = cal.get(Calendar.MONTH);
        m_year = cal.get(Calendar.YEAR);
    }

    /**
     * Method that parses a given date with year,month,day as arguments
     *
     * @author Raúl Correia
     * @param year Integer with year
     * @param month Integer with month
     * @param day Integer with day
     * @throws IllegalArgumentException
     */
    private void parseDateAsNumbers(final int year, final int month, final int day) throws IllegalArgumentException {

        if (month <= 0 && month >= 13) {
            throw new IllegalArgumentException("Invalid month.");
        }
        if (day <= 0 && day >= 32) {
            throw new IllegalArgumentException("Invalid day.");
        }
        Calendar cal = new GregorianCalendar(year, month + 1, day);
        int calday = cal.get(Calendar.DAY_OF_MONTH), calmonth = cal.get(Calendar.MONTH), calyear = cal.get(Calendar.YEAR);

        if (year == calyear) {
            m_year = year;
        } else {
            throw new IllegalArgumentException("Invalid year.");
        }

        if (month - 1 == calmonth) {
            m_month = month;
        } else {
            throw new IllegalArgumentException("Invalid month.");
        }
        if (day == calday) {
            m_day = day;
        } else {
            throw new IllegalArgumentException("Invalid day.");
        }
    }

    /*
    ============================================================================
                                    Public Functions
    ============================================================================
     */
    /**
     * Returns an DateEAPLI object with todays date
     *
     * @return
     */
    public static DateEAPLI today() {
        return new DateEAPLI(DateTime.now());
    }

    /**
     * Converts current date toCalendar
     *
     * @return Calendar with current date
     */
    public final Calendar toCalendar() {
        return new GregorianCalendar(m_year, m_month - 1, m_day);
    }

    /**
     * Is date after today?
     *
     * @return
     */
    public final boolean isAfterToday() {
        return DateTime.isAfterNow(toCalendar());
    }

    /**
     * Is date before today?
     *
     * @return
     */
    public final boolean isBeforeToday() {
        return DateTime.isBeforeNow(toCalendar());
    }

    /**
     * Is tomorroe?
     *
     * @return
     */
    public final boolean isTomorrow(Calendar cal) {
        return DateTime.isTomorrow(cal);
    }

    /**
     * Returns the day of the week
     *
     * @return
     */
    public final int getDayOfWeek() {
        return toCalendar().get(Calendar.DAY_OF_WEEK);
    }

    /**
     * Returns the day of the month
     *
     * @return
     */
    public final int getDayOfMonth() {
        return m_day;
    }

    /**
     * Returns the day of the year
     *
     * @return
     */
    public final int getDayOfYear() {
        return toCalendar().get(Calendar.DAY_OF_YEAR);
    }

    /**
     * Returns the month
     *
     * @return
     */
    public final int getMonth() {
        return m_month;
    }

    /**
     * Returns the year
     *
     * @return
     */
    public final int getYear() {
        return toCalendar().get(Calendar.YEAR);
    }

    /**
     * Returns a specific day after this date. Constants are defined in Calendar
     * Class
     *
     * @param dayToFind Day Constant from Calendar
     * @return A date with the next specific day
     */
    public DateEAPLI getNextSpecificDay(int dayToFind) {
        Calendar start = toCalendar();
        do {
            start.add(Calendar.DATE, 1);
        } while (start.get(Calendar.DAY_OF_WEEK) != dayToFind);
        return new DateEAPLI(start);
    }

    public DateEAPLI getStartingDayOfWeek() {
        final Calendar cal = toCalendar();
        final int weeks = cal.getWeeksInWeekYear();
        cal.clear();
        cal.setFirstDayOfWeek(Calendar.SUNDAY);
        cal.set(Calendar.YEAR, m_year);
        cal.set(Calendar.WEEK_OF_YEAR, weeks);

        return new DateEAPLI(cal);
    }

    public DateEAPLI getEndingDayOfWeek() {
        final int DAYS_TILL_END_OF_WEEK = 6;
        final Calendar cal = getStartingDayOfWeek().toCalendar();
        cal.add(Calendar.DATE, DAYS_TILL_END_OF_WEEK);
        return new DateEAPLI(cal);

    }

    /**
     * Returns if a day is a specific type of day
     * <p>
     * Specific day Constants are define in Calendar class
     *
     * @param typeOfDay Constant of Calendar type of day
     * @return true or false
     */
    public boolean isWhatTypeOfDay(int typeOfDay) {
        return getDayOfWeek() == typeOfDay;
    }
}
