/*
 * To change this template, choose Tools | Templates and open the template in
 * the editor.
 */
package eapli.framework.util;

import java.text.DateFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * utility class for Calendar manipulation and several date and time related
 * functions.
 *
 * @author Paulo Gandra Sousa
 */
public final class DateTime {

    public static final String DEFAULT_SIMPLE_DATA_FORMAT = "dd-MM-yyyy";
    private static final int DAYS_TILL_END_OF_WEEK = 6;

    private DateTime() {
        // to make sure this is an utility class
    }

    /**
     * checks if the two calendar instances represent dates of the same year.
     *
     * @param a
     * @param b
     * @return
     */
    public static boolean isSameYear(final Calendar a, final Calendar b) {
        return a.get(Calendar.YEAR) == b.get(Calendar.YEAR);
    }

    /**
     * checks if the two calendar instances represent dates of the same month
     * (regardless of the year).
     *
     * @param a
     * @param b
     * @return
     */
    public static boolean isSameMonth(final Calendar a, final Calendar b) {
        return a.get(Calendar.MONTH) == b.get(Calendar.MONTH);
    }

    /**
     * returns the current date of the system
     *
     * @return
     */
    public static Calendar now() {
        return new GregorianCalendar();
    }

    /**
     * returns the number of the week in the year given a certain date
     *
     * @param date
     * @return
     */
    public static int weekNumber(final Calendar date) {
        return date.get(Calendar.WEEK_OF_YEAR);
    }

    public static Calendar dateToCalendar(final Date date) {
        final Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal;
    }

    public static int currentWeekNumber() {
        return weekNumber(now());
    }

    /**
     * returns the date of the first day of a week
     *
     * @param year
     * @param week
     * @return
     */
    public static Calendar beginningOfWeek(final int year, final int week) {
        final Calendar date = new GregorianCalendar();
        date.clear();
        date.set(Calendar.YEAR, year);
        date.set(Calendar.WEEK_OF_YEAR, week);
        return date;
    }

    /**
     * returns the date of the last day of a week
     *
     * @param year
     * @param week
     * @return
     */
    public static Calendar endOfWeek(final int year, final int week) {
        final Calendar date = beginningOfWeek(year, week);
        date.add(Calendar.DATE, DAYS_TILL_END_OF_WEEK);
        return date;
    }

    /**
     * returns the date of the last day of the current month
     *
     * @return
     */
    public static Calendar endOfCurrentMonth() {
        return endOfMonth(now());
    }

    /**
     * returns the date of the last day of a certain month
     *
     * @param reference a date to be used as reference month
     * @return
     */
    public static Calendar endOfMonth(final Calendar reference) {
        final Calendar lastDay = new GregorianCalendar();
        lastDay.setTime(reference.getTime());
        final int last = lastDay.getActualMaximum(Calendar.DAY_OF_MONTH);
        lastDay.set(Calendar.DAY_OF_MONTH, last);
        return lastDay;
    }

    public static Calendar endOfMonth(final int year, final int month) {
        final Calendar date = newCalendar(year, month, year);
        return endOfMonth(date);
    }

    public static int currentYear() {
        return now().get(Calendar.YEAR);
    }

    /**
     * returns the current month of the year
     *
     * @return current month (1 - 12) of the year
     */
    public static int currentMonth() {
        return now().get(Calendar.MONTH) + 1;
    }

    /**
     * Creates a new Calendar object set to a specific date
     *
     * @param year the year
     * @param month the month (1 - 12). note that the Calendar class uses months
     * from 0 to 11, not 1 to 12
     * @param day the day of the month
     * @return a newly create Calendar object
     */
    public static Calendar newCalendar(final int year, final int month, final int day) {
        return new GregorianCalendar(year, month - 1, day);
    }

    /**
     * creates a new Calendar instance with the date part set (year, month,
     * date) from the source object
     *
     * @param source
     * @return
     */
    public static Calendar datePart(Calendar source) {
        return newCalendar(source.get(Calendar.YEAR), source.get(Calendar.MONTH) + 1, source.get(Calendar.DATE));
    }

    /**
     * parses a string that contains a date in a certain format
     *
     * @param aDateString
     * @param format
     * @return a Date object or null if there was an error parsing the string
     */
    public static Calendar parseDate(final String aDateString, final String format) {
        try {
            final SimpleDateFormat df = new SimpleDateFormat(format);
            final Date date = df.parse(aDateString);
            return dateToCalendar(date);
        } catch (final ParseException ex) {
            //Logger.getLogger(Console.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public static Calendar parseDate(final String aDateString) {
        return parseDate(aDateString, "dd-MM-yyyy");
    }

    public static String format(final Calendar ocurrs) {
        return format(ocurrs, "YYYY/MM/dd");
    }

    public static String format(final Calendar ocurrs, String dateFormat) {
        final SimpleDateFormat formater = new SimpleDateFormat(dateFormat);
        return formater.format(ocurrs.getTime());
    }

    // TODO create unit tests
    public static String dayNameFromCalendar(Calendar calendar) {
        final String[] dayNames = new DateFormatSymbols().getWeekdays();

        return dayNames[calendar.get(Calendar.DAY_OF_WEEK)];
    }

    public static Calendar yesterday() {
        final Calendar yesterday = now();
        yesterday.add(Calendar.DATE, -1);
        return yesterday;
    }

    public static Calendar tomorrow() {
        final Calendar yesterday = now();
        yesterday.add(Calendar.DATE, 1);
        return yesterday;
    }

    /* ============================================================= */
    /**
     * checks if the two calendar instances represent the same dates, i.e. if
     * both have the same year, month and day.
     *
     * @param a
     * @param b
     * @return
     */
    public static boolean isSameDate(final Calendar a, final Calendar b) {
        return isSameYear(a, b) && isSameMonth(a, b) && isSameDay(a, b);
    }

    /**
     * checks if the two calendar instances represent dates of the same day
     * (regardless of the year and the month).
     *
     * @param a
     * @param b
     * @return
     */
    public static boolean isSameDay(final Calendar a, final Calendar b) {
        return a.get(Calendar.DAY_OF_MONTH) == b.get(Calendar.DAY_OF_MONTH);
    }

    /* ============================================================= */
    /**
     * checks if the calendar date (Year, Month and Day) is before the other
     * calendar.
     *
     * @author Manuel Meireles (2DD, EAPLI 2016/2017)
     * @param a The one calendar.
     * @param b The other calendar.
     * @return It returns "true" if the one calendar is before the other
     * calendar or "false" otherwise.
     */
    public static boolean isBefore(final Calendar a, final Calendar b) {
        return compareDates(a, b) < 0;
    }

    /**
     * checks if the calendar date (Year, Month and Day) is before the other
     * calendar or both have the same date.
     *
     * @author Manuel Meireles (2DD, EAPLI 2016/2017)
     * @param a The one calendar.
     * @param b The other calendar.
     * @return It returns "true" if the one calendar is before/equal the other
     * calendar or "false" otherwise.
     */
    public static boolean isUntil(final Calendar a, final Calendar b) {
        return compareDates(a, b) <= 0;
    }

    /**
     * checks if the calendar date (Year, Month and Day) is before the other
     * calendar.
     *
     * @author Manuel Meireles (2DD, EAPLI 2016/2017)
     * @param a The one calendar.
     * @param b The other calendar.
     * @return It returns "true" if the one calendar is before the other
     * calendar or "false" otherwise.
     */
    public static boolean isAfter(final Calendar a, final Calendar b) {
        return compareDates(a, b) > 0;
    }

    /**
     * checks if the calendar date (Year, Month and Day) is after the other
     * calendar or both have the same date.
     *
     * @author Manuel Meireles (2DD, EAPLI 2016/2017)
     * @param a The one calendar.
     * @param b The other calendar.
     * @return It returns "true" if the one calendar is after/equal the other
     * calendar or "false" otherwise.
     */
    public static boolean isOnwards(final Calendar a, final Calendar b) {
        return compareDates(a, b) >= 0;
    }

    /**
     * checks if the calendar date (Year, Month and Day) is before today.
     *
     * @author Manuel Meireles (2DD, EAPLI 2016/2017)
     * @param a The calendar to check.
     * @return It returns "true" if the calendar is before today or "false"
     * otherwise.
     */
    public static boolean isBeforeToday(final Calendar a) {
        return isBefore(a, now());
    }

    /**
     * checks if the calendar date (Year, Month and Day) is today or before.
     *
     * @author Manuel Meireles (2DD, EAPLI 2016/2017)
     * @param a The calendar to check.
     * @return It returns "true" if the calendar is today or before or "false"
     * otherwise.
     */
    public static boolean isUntilToday(final Calendar a) {
        return isUntil(a, now());
    }

    /**
     * checks if the calendar date (Year, Month and Day) is today.
     *
     * @author Manuel Meireles (2DD, EAPLI 2016/2017)
     * @param a The calendar to check.
     * @return It returns "true" if the calendar is equal to today or "false"
     * otherwise.
     */
    public static boolean isToday(final Calendar a) {
        return isSameDate(a, now());
    }

    /**
     * checks if the calendar date (Year, Month and Day) is today or after.
     *
     * @author Manuel Meireles (2DD, EAPLI 2016/2017)
     * @param a The calendar to check.
     * @return It returns "true" if the calendar is today or after or "false"
     * otherwise.
     */
    public static boolean isTodayOnwards(final Calendar a) {
        return isOnwards(a, now());
    }

    /**
     * checks if the calendar date (Year, Month and Day) is after today.
     *
     * @author Manuel Meireles (2DD, EAPLI 2016/2017)
     * @param a The calendar to check.
     * @return It returns "true" if the calendar is after today or "false"
     * otherwise.
     */
    public static boolean isAfterToday(final Calendar a) {
        return isAfter(a, now());
    }

    /**
     * compares a Calendar field between the two calendars.
     *
     * @author Manuel Meireles (2DD, EAPLI 2016/2017)
     * @param a The one calendar
     * @param b The other calendar.
     * @param dateField The Calendar field to compare (it must be an integer
     * field).
     * @return It returns "1" if the one calendar field is greater, "-1" if
     * lesser or "0" if equal.
     */
    private static int compareDateField(final Calendar a, final Calendar b, int dateField) {
        final int difference = a.get(dateField) - b.get(dateField);
        return (difference > 0) ? 1 : (difference < 0) ? -1 : 0;
    }

    /**
     * it compares the dates (regardless of the time).
     *
     * @author Manuel Meireles (2DD, EAPLI 2016/2017)
     * @param a The one calendar.
     * @param b The other calendar.
     * @return It returns "1" if the one calendar's date is greater, "-1" if
     * lesser or "0" if equal.
     */
    private static int compareDates(final Calendar a, final Calendar b) {
        int result = compareDateField(a, b, Calendar.YEAR);
        if (result == 0) {
            result = compareDateField(a, b, Calendar.MONTH);
            if (result == 0) {
                result = compareDateField(a, b, Calendar.DAY_OF_MONTH);
            }
        }
        return result;
    }

    /**
     * compares a Calendar field between the two calendars.
     *
     * @author Manuel Meireles (2DD, EAPLI 2016/2017)
     * @param a The one calendar
     * @param b The other calendar.
     * @param dateField The Calendar field to compare.
     * @return It returns "1" if the one calendar field is greater, "-1" if
     * lesser or "0" if equal.
     */
    private static int compareCalendarField(final Calendar a, final Calendar b, int dateField) {
        final int difference = a.get(dateField) - b.get(dateField);
        return (difference > 0) ? 1 : (difference < 0) ? -1 : 0;
    }

    /**
     * @author Manuel Meireles (2DD, EAPLI 2016/2017)
     *
     * @param a
     * @param b
     * @return
     */
    private static int compareTimes(final Calendar a, final Calendar b) {
        int result = compareCalendarField(a, b, Calendar.HOUR_OF_DAY);
        if (result == 0) {
            result = compareCalendarField(a, b, Calendar.MINUTE);
            if (result == 0) {
                result = compareCalendarField(a, b, Calendar.SECOND);
                if (result == 0) {
                    result = compareCalendarField(a, b, Calendar.MILLISECOND);
                }
            }
        }
        return result;
    }

    /**
     * checks if the calendar time (Hour, Minute, Second and Millisecond) is
     * before the other calendar.
     *
     * @author Manuel Meireles (2DD, EAPLI 2016/2017)
     * @param a The one calendar.
     * @param b The other calendar.
     * @return It returns "true" if the one calendar is before the other
     * calendar or "false" otherwise.
     */
    public static boolean isBeforeTime(final Calendar a, final Calendar b) {
        return compareTimes(a, b) < 0;
    }

    public static boolean is24hBefore(final Calendar choosedDay, final Calendar today) {
        //86400000millis - 24 hours
        long choosed = choosedDay.getTimeInMillis();
        long day = today.getTimeInMillis();
        if(day + 86400000 <  choosed){
            return true;
            
        }
        return false;
    }

    /**
     * checks if the calendar time (Hour, Minute, Second and Millisecond) is
     * before or equal the other calendar.
     *
     * @author Manuel Meireles (2DD, EAPLI 2016/2017)
     * @param a The one calendar.
     * @param b The other calendar.
     * @return It returns "true" if the one calendar is before or equal the
     * other calendar or "false" otherwise.
     */
    public static boolean isUntilTime(final Calendar a, final Calendar b) {
        return compareTimes(a, b) <= 0;
    }

    /**
     * checks if the calendar time (Hour, Minute, Second and Millisecond) is
     * equal the other calendar.
     *
     * @author Manuel Meireles (2DD, EAPLI 2016/2017)
     * @param a The one calendar.
     * @param b The other calendar.
     * @return It returns "true" if the one calendar is before the other
     * calendar or "false" otherwise.
     */
    public static boolean isSameTime(final Calendar a, final Calendar b) {
        return compareTimes(a, b) == 0;
    }

    /**
     * checks if the calendar time (Hour, Minute, Second and Millisecond) is
     * after the other calendar.
     *
     * @author Manuel Meireles (2DD, EAPLI 2016/2017)
     * @param a The one calendar.
     * @param b The other calendar.
     * @return It returns "true" if the one calendar is before the other
     * calendar or "false" otherwise.
     */
    public static boolean isAfterTime(final Calendar a, final Calendar b) {
        return compareTimes(a, b) > 0;
    }

    /**
     * checks if the calendar time (Hour, Minute, Second and Millisecond) is
     * after or equal the other calendar.
     *
     * @author Manuel Meireles (2DD, EAPLI 2016/2017)
     * @param a The one calendar.
     * @param b The other calendar.
     * @return It returns "true" if the one calendar is after or equal the other
     * calendar or "false" otherwise.
     */
    public static boolean isTimeOnwards(final Calendar a, final Calendar b) {
        return compareTimes(a, b) >= 0;
    }

    /**
     * checks if the calendar time (Hour, Minute, Second and Millisecond) is
     * before the current time.
     *
     * @author Manuel Meireles (2DD, EAPLI 2016/2017)
     * @param a The one calendar.
     * @return It returns "true" if the one calendar is before the current time
     * or "false" otherwise.
     */
    public static boolean isBeforeNow(final Calendar a) {
        return isBeforeTime(a, now());
    }

    public static boolean isTomorrow(final Calendar a) {
        return is24hBefore(a, now());
    }

    /**
     * checks if the calendar time (Hour, Minute, Second and Millisecond) is the
     * current time or before.
     *
     * @author Manuel Meireles (2DD, EAPLI 2016/2017)
     * @param a The one calendar.
     * @return It returns "true" if the one calendar is the current time or
     * before or "false" otherwise.
     */
    public static boolean isUntilNow(final Calendar a) {
        return isUntilTime(a, now());
    }

    /**
     * checks if the calendar time (Hour, Minute, Second and Millisecond) is the
     * current time or before.
     *
     * @author Manuel Meireles (2DD, EAPLI 2016/2017)
     * @param a The one calendar.
     * @return It returns "true" if the one calendar is the current time or
     * before or "false" otherwise.
     */
    public static boolean isNow(final Calendar a) {
        return isSameTime(a, now());
    }

    /**
     * checks if the calendar time (Hour, Minute, Second and Millisecond) is the
     * current time or after.
     *
     * @author Manuel Meireles (2DD, EAPLI 2016/2017)
     * @param a The one calendar.
     * @return It returns "true" if the one calendar is the current time or
     * after or "false" otherwise.
     */
    public static boolean isNowOnwards(final Calendar a) {
        return isTimeOnwards(a, now());
    }

    /**
     * checks if the calendar time (Hour, Minute, Second and Millisecond) is
     * after the current time.
     *
     * @author Manuel Meireles (2DD, EAPLI 2016/2017)
     * @param a The one calendar.
     * @return It returns "true" if the one calendar is after the current time
     * or "false" otherwise.
     */
    public static boolean isAfterNow(final Calendar a) {
        return isAfterTime(a, now());
    }

    /**
     * Returns the difference in days between two dates
     *
     * @author Raúl Correia (2DC, EAPLI 2017/2018)
     * @param start Starting Date
     * @param end Ending Date
     * @return Days in long
     */
    public static long getDifferenceInDays(final Calendar start, final Calendar end) {
        return ChronoUnit.DAYS.between(start.toInstant(), end.toInstant());
    }

    /**
     * Returns the difference in hours between two dates
     *
     * @author Raúl Correia (2DC, EAPLI 2017/2018)
     * @param start Starting Date
     * @param end Ending Date
     * @return Days in long
     */
    public static long getDifferenceInHours(final Calendar start, final Calendar end) {
        return ChronoUnit.HOURS.between(start.toInstant(), end.toInstant());
    }

    /**
     * checks if the difference between two calenders are n number of days
     *
     * @author Raúl Correia (2DC, EAPLI 2017/2018)
     * @param start Calendar starting date
     * @param end Calendar ending date
     * @param n number of days to test
     * @return trues if n working days between dates else false
     *
     */
    public static boolean validateDifferenceInDays(final Calendar start, final Calendar end, final long n) {
        return getDifferenceInDays(start, end) == n;
    }

    public static int getYear(final Calendar cal) {
        return cal.get(Calendar.YEAR);
    }

    public static boolean isBetweenDates(Calendar start, Calendar end, Calendar date) {

        return (DateTime.isSameDate(date, start) || DateTime.isSameDate(date, end))
                || (DateTime.isFutureDate(date, start) && DateTime.
                isPreviousDate(date, end));
    }

    /**
     * Checks if a date is previous to another. Time is not compared!
     *
     * @param a date to be checked
     * @param b date to be checked against
     * @return
     */
    public static boolean isPreviousDate(final Calendar a, final Calendar b) {
        Calendar dateA = new GregorianCalendar(a.get(Calendar.YEAR), a.
                get(Calendar.MONTH), a.
                get(Calendar.DAY_OF_MONTH));
        Calendar dateB = new GregorianCalendar(b.get(Calendar.YEAR), b.
                get(Calendar.MONTH), b.
                get(Calendar.DAY_OF_MONTH));
        return (dateA.before(dateB));
    }

    /**
     * Checks if a date is after another. Time is not compared!
     *
     * @param a date to be checked
     * @param b date to be checked against
     * @return
     */
    public static boolean isFutureDate(final Calendar a, final Calendar b) {
        Calendar dateA = new GregorianCalendar(a.get(Calendar.YEAR), a.
                get(Calendar.MONTH), a.
                get(Calendar.DAY_OF_MONTH));
        Calendar dateB = new GregorianCalendar(b.get(Calendar.YEAR), b.
                get(Calendar.MONTH), b.
                get(Calendar.DAY_OF_MONTH));
        return (dateA.after(dateB));
    }

    public static String convertCalendarToDayMonthYearAndDayName(final Calendar cal) {
        SimpleDateFormat dfmt = new SimpleDateFormat(DateTime.DEFAULT_SIMPLE_DATA_FORMAT + " EEEE");
        return dfmt.format(cal.getTime());

    }
}
