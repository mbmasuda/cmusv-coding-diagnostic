/**
 * DatePickerController.java
 * Feb. 19, 2012
 * Copyright © 2012 
 * @version 1.0
 * @author Mari Masuda
 */

package org.marimasuda.cmusvdiagnostic;

import java.util.GregorianCalendar;

/**
 * A model that keeps track of a date and whether the year, month, and 
 * day of month fields of that date have been set manually by the user.
 */
public class DatePickerModel {
   /** A GregorianCalendar to hold the date information */
   private GregorianCalendar calendar;

   /** a flag indicating whether the year field was set by the user in order
    * to differentiate from when the year field gets set as a side effect of 
    * other operations on the calendar
    */
   private boolean isYearSet;
   
   /** a flag indicating whether the month field was set by the user in order
    * to differentiate from when the month field gets set as a side effect of 
    * other operations on the calendar
    */
   private boolean isMonthSet;
   
   /** a flag indicating whether the day field was set by the user in order
    * to differentiate from when the day field gets set as a side effect of 
    * other operations on the calendar
    */
   private boolean isDaySet;
   
   /**
    * Creates a new GregorianCalendar to hold the date data and initializes 
    * the flags indicating whether certain fields have been manually set.
    */
   public DatePickerModel() {
      calendar = new GregorianCalendar();
      calendar.clear();
      calendar.setLenient(false);
      isYearSet = false;
      isMonthSet = false;
      isDaySet = false;
   }

   /**
    * gets a deep copy of the calendar contained in this DatePickerModel 
    * so that other objects can access the values without having to worry 
    * about them changing the "real" data in the model
    * @return
    */
   public GregorianCalendar getCopyOfCalendar() {
      return (GregorianCalendar) calendar.clone();
   }
   
   /**
    * wrapper to add an amount to a specific field in the model's calendar
    * @param field an int constant indicating which field of the 
    *              GregorianCalendar should be updated
    * @param amount a signed int indicating the amount to add
    */
   public void add(int field, int amount) {
      calendar.add(field, amount);
   }
   
   /**
    * wrapper to get the actual minimum value of the specified field in the
    * model's calendar
    * @param field an int constant indicating which field of the 
    *              GregorianCalendar should be retrieved
    * @return an int representing the minimum value that the specified 
    *         field can hold
    */
   public int getActualMinimum(int field) {
      return calendar.getActualMinimum(field);
   }
   
   /**
    * wrapper to get the actual maximum value of the specified field in the
    * model's calendar
    * @param field an int constant indicating which field of the 
    *              GregorianCalendar should be retrieved
    * @return an int representing the maximum value that the specified 
    *         field can hold
    */
   public int getActualMaximum(int field) {
      return calendar.getActualMaximum(field);
   }
   
   /**
    * sets the flag that indicates whether the year value was set manually
    * @param value a boolean to indicate whether the year value was set
    * manually by the user via the combo box
    */
   public void setIsYearSet(boolean value) {
      isYearSet = value;
   }
   
   /**
    * gets the flag that indicates whether the year value was set manually
    * @return a boolean to indicate whether the year value was set
    * manually by the user via the combo box
    */
   public boolean isYearIsSet() {
      return isYearSet;
   }
   
   /**
    * sets the flag that indicates whether the month value was set manually
    * @param value a boolean to indicate whether the month value was set
    * manually by the user via the combo box
    */
   public void setIsMonthSet(boolean value) {
      isMonthSet = value;
   }
   
   /**
    * gets the flag that indicates whether the month value was set manually
    * @return a boolean to indicate whether the month value was set
    * manually by the user via the combo box
    */
   public boolean isMonthIsSet() {
      return isMonthSet;
   }
   
   /**
    * sets the flag that indicates whether the day value was set manually
    * @param value a boolean to indicate whether the day value was set
    * manually by the user via the combo box
    */
   public void setIsDaySet(boolean value) {
      isDaySet = value;
   }
   
   /**
    * gets the flag that indicates whether the day value was set manually
    * @return a boolean to indicate whether the day value was set
    * manually by the user via the combo box
    */
   public boolean isDayIsSet() {
      return isDaySet;
   }
   
   /**
    * wrapper to get the year stored by the model
    * @return an int representing the year
    */
   public int getYear() {
      return calendar.get(GregorianCalendar.YEAR);
   }
   
   /**
    * wrapper to set the year stored by the model
    * @param an int representing the year
    */
   public void setYear(int year) {
      calendar.set(GregorianCalendar.YEAR, year);
   }
   
   /**
    * wrapper to get the month stored by the model
    * @return an int representing the month
    */
   public int getMonth() {
      return calendar.get(GregorianCalendar.MONTH);
   }
   
   /**
    * wrapper to set the month stored by the model
    * @param an int representing the month
    */
   public void setMonth(int month) {
      calendar.set(GregorianCalendar.MONTH, month);
   }
   
   /**
    * wrapper to get the day stored by the model
    * @return an int representing the day of the month
    */
   public int getDay() {
      return calendar.get(GregorianCalendar.DAY_OF_MONTH);
   }
   
   /**
    * wrapper to set the day stored by the model
    * @param an int representing the day of the month
    */
   public void setDay(int day) {
      calendar.set(GregorianCalendar.DAY_OF_MONTH, day);
   }
   
   /**
    * wrapper to get the hour of day stored by the model
    * @return an int representing the hour of day
    */
   public int getHour() {
      return calendar.get(GregorianCalendar.HOUR_OF_DAY);
   }
   
   /**
    * wrapper to set the hour of day stored by the model
    * @param an int representing the hour of day
    */
   public void setHour(int hour) {
      calendar.set(GregorianCalendar.HOUR_OF_DAY, hour);
   }
}