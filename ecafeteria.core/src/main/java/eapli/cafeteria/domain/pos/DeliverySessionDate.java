/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eapli.cafeteria.domain.pos;

import eapli.framework.util.DateTime;
import java.util.Calendar;
import java.util.Date;


/**
 *
 * @author PedroEmanuelCoelho 1131485@isep.ipp.pt
 */

//Value Object 
public class DeliverySessionDate {
    
    /** current hour of the session **/
    private int hour;
    /** current minute of the session **/
    private int minute;
    /** current second of the session **/
    private int second;
    /** current day of the session **/
    private int day;
    /** current month of the session **/
    private int month;
    /** current year of the session **/
    private int year;
    
    public DeliverySessionDate(Calendar ca) {
       this.validateHour(ca.HOUR_OF_DAY);
       this.validateMinute(ca.MINUTE);
       this.validateSeconds(ca.SECOND);
       this.validateYear(ca.YEAR);
       this.validateMonth(ca.MONTH);
       this.validateDay(ca.DAY_OF_MONTH, ca.MONTH, ca.YEAR);
       
    }
    
    /** VALIDATION PRIVATE METHODS OF THIS VALUE OBJECT **/
    
    /**
     * validation of the current hour
     * sets hour to zero if the arg invalid
     * @param hour 
     */
    private void validateHour(int hour) {
        
        if(hour >= 0 && hour < 24)this.hour = hour;
        else this.hour = 0;
    }
    
    /**
     * Validation of the current minutes
     * sets minutes to zero if the arg is invalid
     * @param minute 
     */
    private void validateMinute(int minute) {
        if(minute >= 0 && minute <= 60) this.minute = minute;
        else this.minute = 0;
    }
    
    /**
     * Validation of the current seconds
     * sets seconds to zero if the arg is invalid
     * @param seconds 
     */
    private void validateSeconds(int seconds) {
        if(seconds >= 0 && second <=60) this.second = seconds;
        else this.second = 0;
    }
    
    /**
     * Validation of the current year
     * sets the year to the system date year if arg is invalid
     * @param year 
     */
    private void validateYear(int year) {
        if(year <= DateTime.currentYear()) this.year = year;
        else this.year = DateTime.currentYear();
    }
    
    /**
     * Validation of the current month
     * sets the current to the system date month if arg is invalid
     * @param month 
     */
    private void validateMonth(int month) {
        if(month > 0 && month <=12) this.month = month;
        else this.month = DateTime.currentMonth();
    }
    
    /**
     * Validation of the day using the year and month to confirm it
     * sets the current session day to system day if arg is invalid
     * @param day
     * @param month
     * @param year 
     */
    private void validateDay(int day, int month, int year) {
        
        switch(month) {
            case 1:
                if(this.check31Days(day)) this.day = day;
                else this.day = DateTime.currentWeekNumber();
            break;
            case 2: 
                if((year % 4) == 0) {
                    if(day > 0 && day <= 29) this.day = day;
                    else this.day = DateTime.currentWeekNumber();
                }
                else {
                    if(day > 0 && day <= 28) this.day = day;
                    else this.day = DateTime.currentWeekNumber();
                }
            break;
            case 3:
                if(this.check31Days(day)) this.day = day;
                else this.day = DateTime.currentWeekNumber();
            break;
            case 4:
                if(this.check30Days(day)) this.day = day;
                else this.day = DateTime.currentWeekNumber();
            break;
            case 5:
                if(this.check31Days(day)) this.day = day;
                else this.day = DateTime.currentWeekNumber();
            break;
            case 6:
                if(this.check30Days(day)) this.day = day;
                else this.day = DateTime.currentWeekNumber();
            break;
            case 7:
                if(this.check31Days(day)) this.day = day;
                else this.day = DateTime.currentWeekNumber();
            break;
            case 8:
                if(this.check31Days(day)) this.day = day;
                else this.day = DateTime.currentWeekNumber();
            break;
            case 9:
                if(this.check30Days(day)) this.day = day;
                else this.day = DateTime.currentWeekNumber();
            break;
            case 10:
                if(this.check31Days(day)) this.day = day;
                else this.day = DateTime.currentWeekNumber();
            break;
            case 11:
                if(this.check30Days(day)) this.day = day;
                else this.day = DateTime.currentWeekNumber();
            break;
            case 12:
                if(this.check31Days(day)) this.day = day;
                else this.day = DateTime.currentWeekNumber();
            break;
        }
    }
  
    /** 
     * Validates the day in month with total of 31 days
     * @param day
     * @return 
     */
    private boolean check31Days(int day) { 
        if(day > 0 && day <= 31) return true;
        else return false;
    }
    
    /**
     * Validates the in month with total of 30 days
     * @param day
     * @return 
     */
    private boolean check30Days(int day) {
        if(day > 0 && day <=30) return true;
        else return false;
    }
    
    /** PUBLIC METHODS TO RETURN A SPECIFIC PART OF THIS DATE **/

    /** 
     * returns current sessions year
     * @return 
     */
    public int Year() {
        return this.year;
    }
    
    /**
     * returns current month
     * @return 
     */
    public int Month() {
        return this.month;
    }
    
    /**
     * returns the current sessions day
     * @return 
     */
    public int Day(){
        return this.day;
    }
    
    /**
     * returns the current sessions hour
     * @return 
     */
    public int Hour() {
        return this.hour;
    }
    
    /**
     * returns the current sessions minutes
     * @return 
     */
    public int Minutes() {
        return this.minute;
    }
    
    /**
     * returns the current sessions seconds
     * @return 
     */
    public int Seconds() {
        return this.second;
    }
}
