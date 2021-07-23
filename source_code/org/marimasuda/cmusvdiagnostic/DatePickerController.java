/**
 * DatePickerController.java
 * Feb. 19, 2012
 * Copyright © 2012 
 * @version 1.0
 * @author Mari Masuda
 */

package org.marimasuda.cmusvdiagnostic;

import java.util.GregorianCalendar;
import java.util.Locale;

import javax.swing.DefaultComboBoxModel;

/**
 * Contains business logic related to the dates that are considered valid, 
 * translates GUI actions to model updates, and provides the means for 
 * generating DefaultComboBoxModels to be used by the GUI to display the
 * appropriate choices in the date picker combo boxes.
 */
public class DatePickerController {
   /** the minimum year to be displayed in the year picker combo box */
   public static final int MIN_YEAR = 2011;
   
   /** the maximum year to be displayed in the year picker combo box */   
   public static final int MAX_YEAR = 2012;
   
   /** a "machine" value to be stored and used by the hour picker combo box*/
   public static final int ANYTIME_HOUR = 0;
   
   /** a "human-readable" value to be stored and used by the hour picker combo box */
   public static final String ANYTIME_TEXT = "Any time";
   
   /** a "machine" value to be stored and used by the hour picker combo box */
   public static final int MORNING_HOUR = 7;

   /** a "human-readable" value to be stored and used by the hour picker combo box */
   public static final String MORNING_TEXT = "Morning";
   
   /** a "machine" value to be stored and used by the hour picker combo box */
   public static final int NOON_HOUR = 12;
   
   /** a "human-readable" value to be stored and used by the hour picker combo box */
   public static final String NOON_TEXT = "Noon";
   
   /** a "machine" value to be stored and used by the hour picker combo box */
   public static final int EVENING_HOUR = 17;
   
   /** a "human-readable" value to be stored and used by the hour picker combo box */
   public static final String EVENING_TEXT = "Evening";
   
   /** a "machine" value to be stored and used by the hour picker combo box */
   public static final int LATENIGHT_HOUR = 21;
   
   /** a "human-readable" value to be stored and used by the hour picker combo box */
   public static final String LATENIGHT_TEXT = "Late night";
   
   /** a "machine" value to be stored and used by combo boxes that have 
    *  "select XYZ" as their initial value.  This value must be negative so
    *  as to not be accidentally interpreted as a valid value for any of the 
    *  fields used by a GregorianCalendar.
    */
   public static final int COMBO_BOX_FIRST_ITEM_PLACEHOLDER = -1;

   /** When true, indicates that the day combo box should use the value 
    * of the day stored in the underlying DatePickerModel.  When false, 
    * the day combo box will display the first item in the combo box
    * (i.e., "Select day").
    */
   private boolean isDaySticky;

   /** the underlying model that holds the data being represented */
   private DatePickerModel model;
   
   /**
    * Constructs a new DatePickerModel and initializes isDaySticky
    */
   public DatePickerController() {
      model = new DatePickerModel();
      isDaySticky = false;
   }
   
   /**
    * @return a cloned copy of the underlying DatePickerModel's 
    * GregorianCalendar if all values have been manually set, or a blank 
    * calendar otherwise
    */
   public GregorianCalendar getValue() {
      if (model.isYearIsSet() &&
          model.isMonthIsSet() &&
          model.isDayIsSet()) {
         return model.getCopyOfCalendar();
      } else {
         GregorianCalendar newCalendar = new GregorianCalendar();
         newCalendar.clear();
         newCalendar.setLenient(false);
         return newCalendar;
      }
   }

   /**
    * @return value of isDaySticky
    */
   public boolean isDaySticky() {
      return isDaySticky;
   }
   
   /**
    * @param newYear an int indicating the year to set in the model
    */
   public void setYear(int newYear) {
      if (newYear != COMBO_BOX_FIRST_ITEM_PLACEHOLDER) {
         int diff = newYear - model.getYear();
         model.add(GregorianCalendar.YEAR, diff);
         model.setIsYearSet(true);
      } else {
         model.setIsYearSet(false);
      }
   }
   
   /**
    * @param newMonth an int indicating the month to set in the model
    */
   public void setMonth(int newMonth) {
      if (newMonth != COMBO_BOX_FIRST_ITEM_PLACEHOLDER) {
         int diff = newMonth - model.getMonth();
         model.add(GregorianCalendar.MONTH, diff);
         model.setIsMonthSet(true);
      } else {
         model.setIsMonthSet(false);
      }
   }
   
   /**
    * @param newDay an int indicating the day of the month to set in the model
    */
   public void setDay(int newDay) {
      if (newDay != COMBO_BOX_FIRST_ITEM_PLACEHOLDER) {
         int diff = newDay - model.getDay();
         model.add(GregorianCalendar.DAY_OF_MONTH, diff);
         model.setIsDaySet(true);
         isDaySticky = true;
      } else {
         model.setIsDaySet(false);
         isDaySticky = false;
      }
   }
   
   /**
    * @param newHour an int indicating the hour of day to set in the model
    */
   public void setHour(int newHour) {
      int diff = newHour - model.getHour();
      model.add(GregorianCalendar.HOUR_OF_DAY, diff);
   }
   
   /**
    * @return a DefaultComboBoxModel containing the acceptable values for the 
    * year combo box
    */
   public DefaultComboBoxModel getYearModel() {
      DefaultComboBoxModel dcbModel = new DefaultComboBoxModel();
      dcbModel.addElement(
            new ComboBoxItem(COMBO_BOX_FIRST_ITEM_PLACEHOLDER, "Select year")
      );
      for (Integer i = MIN_YEAR; i <= MAX_YEAR; i++) {
         dcbModel.addElement(new ComboBoxItem(i, i.toString()));
      }
      return dcbModel;
   }
   
   /**
    * @return a DefaultComboBoxModel containing the acceptable values for the 
    * month combo box
    */
   public DefaultComboBoxModel getMonthModel() {
      DefaultComboBoxModel dcbModel = new DefaultComboBoxModel();
      dcbModel.addElement(
            new ComboBoxItem(COMBO_BOX_FIRST_ITEM_PLACEHOLDER, "Select month")
      );
      int min = model.getActualMinimum(GregorianCalendar.MONTH);
      int max = model.getActualMaximum(GregorianCalendar.MONTH);
      GregorianCalendar dummyCalendar = new GregorianCalendar();
      dummyCalendar.clear();
      for (Integer i = min; i <= max; i++) {
         String description = dummyCalendar.getDisplayName(
               GregorianCalendar.MONTH,
               GregorianCalendar.LONG,
               new Locale("US")
         );
         ComboBoxItem item = new ComboBoxItem(i, description);
         dummyCalendar.roll(GregorianCalendar.MONTH, true);
         dcbModel.addElement(item);
      }
      return dcbModel;
   }

   /**
    * @return a DefaultComboBoxModel containing the acceptable values for the 
    * day of month combo box
    */
   public DefaultComboBoxModel getDayModel() {
      DefaultComboBoxModel dcbModel = new DefaultComboBoxModel();
      dcbModel.addElement(
            new ComboBoxItem(COMBO_BOX_FIRST_ITEM_PLACEHOLDER, "Select day")
      );
      int min = model.getActualMinimum(GregorianCalendar.DAY_OF_MONTH);
      int max = model.getActualMaximum(GregorianCalendar.DAY_OF_MONTH);
      for (Integer i = min; i <= max; i++) {
         ComboBoxItem item = new ComboBoxItem(i, i.toString());
         dcbModel.addElement(item);
         if (isDaySticky() && 
             model.getDay() == item.getValue()) {
            dcbModel.setSelectedItem(item);
         }
      }
      return dcbModel;
   }

   /**
    * @return a DefaultComboBoxModel containing the acceptable values for the 
    * hour of day combo box
    */
   public DefaultComboBoxModel getHours() {
      DefaultComboBoxModel dcbModel = new DefaultComboBoxModel();
      dcbModel.addElement(new ComboBoxItem(ANYTIME_HOUR, ANYTIME_TEXT));
      dcbModel.addElement(new ComboBoxItem(MORNING_HOUR, MORNING_TEXT));
      dcbModel.addElement(new ComboBoxItem(NOON_HOUR, NOON_TEXT));
      dcbModel.addElement(new ComboBoxItem(EVENING_HOUR, EVENING_TEXT));
      dcbModel.addElement(new ComboBoxItem(LATENIGHT_HOUR, LATENIGHT_TEXT));
      return dcbModel;
   }
}
