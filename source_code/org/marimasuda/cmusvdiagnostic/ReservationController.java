/**
 * ReservationController.java
 * Feb. 19, 2012
 * Copyright © 2012 
 * @version 1.0
 * @author Mari Masuda
 */

package org.marimasuda.cmusvdiagnostic;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

import javax.swing.DefaultComboBoxModel;

/**
 * Contains business logic related to the values that are considered valid and 
 * translates GUI actions to model updates.
 */
public class ReservationController {
   /** defines the minimum acceptable date as given in the business rules */
   private static final GregorianCalendar MIN_ACCEPTABLE_DATE = 
      new GregorianCalendar(DatePickerController.MIN_YEAR, 
            GregorianCalendar.JANUARY, 1, 0, 0, 0);

   /** defines the maximum acceptable date as given in the business rules */
   private static final GregorianCalendar MAX_ACCEPTABLE_DATE = 
      new GregorianCalendar(DatePickerController.MAX_YEAR, 
            GregorianCalendar.DECEMBER, 31, 23, 59, 59);

   /** defines the minimum acceptable number of passengers as given in the 
    * business rules
    */
   private static final int MIN_PASSENGERS = 1;

   /** defines the maximum acceptable number of passengers as given in the 
    * business rules
    */
   private static final int MAX_PASSENGERS = 10;

   /** underlying model that keeps track of the data
    */
   private ReservationModel model;

   /** contains any error messages generated when the model is validated */
   private StringBuilder errorMessages;
   
   /** contains the itinerary if no error messages are detected when the 
    * model is validated
    */
   private StringBuilder itinerary;
   
   /**
    * Constructs a new model and initializes the error message and itinerary
    */
   public ReservationController() {
      model = new ReservationModel();
      errorMessages = new StringBuilder();
      itinerary = new StringBuilder();
   }

   /**
    * Validates the contents of the model against the provided business rules.
    * Basic business rules for validation to succeed:
    * 1. From: not blank
    * 2. To: not blank
    * 3. Departure date: combination of month/day/year must be a valid date
    * 4. Return date: combination of month/day/year must be a valid date
    * 5. Return date must be on or after the departure date
    * 6. The years must be 2011-2012
    * 7. The number of passengers must be 1-10
    * @return true if all fields are valid or false if one or more fields 
    * is not valid
    */
   public boolean isValid() {
      errorMessages.setLength(0);
      itinerary.setLength(0);
      validateDepartureCity(); // rule 1
      validateArrivalCity();   // rule 2
      validateNumPassengers(); // rule 7
      validateDepartureDate(); // rule 3 and 6
      validateReturnDate();    // rule 4 and 6
      validateRelativeDates(); // rule 5
      if (getErrorMessages().isEmpty()) {
         return true;
      } else {
         return false;
      }
   }
   
   /**
    * Private helper function to ensure the departure city is not blank.  If 
    * it is, an error message is generated.  If not, the itinerary is updated.
    */
   private void validateDepartureCity() {
      if (model.getDepartureCity().isEmpty()) {
         errorMessages.append("Departure city cannot be blank.\n");
      } else {
         itinerary.append("From: ")
                  .append(model.getDepartureCity())
                  .append("\n");
      }
   }
   
   /**
    * Private helper function to ensure the arrival city is not blank.  If 
    * it is, an error message is generated.  If not, the itinerary is updated.
    */
   private void validateArrivalCity() {
      if (model.getArrivalCity().isEmpty()) {
         errorMessages.append("Arrival city cannot be blank.\n");
      } else {
         itinerary.append("To: ")
                  .append(model.getArrivalCity())
                  .append("\n");
      }
   }

   /**
    * Private helper function to ensure the number of passengers is valid.  If 
    * not, an error message is generated.  If it is, the itinerary is updated.
    */
   private void validateNumPassengers() {
      if (!(model.getNumPassengers() >= MIN_PASSENGERS) 
          &&
          !(model.getNumPassengers() <= MAX_PASSENGERS)) {
         errorMessages.append("Number of passengers must be between ")
                      .append(MIN_PASSENGERS)
                      .append(" and ")
                      .append(MAX_PASSENGERS)
                      .append(".\n");
      } else {
         itinerary.append("Number of passengers: ")
                  .append(model.getNumPassengers())
                  .append("\n");
      }
   }

   /**
    * Private helper function to ensure the departure date is set and valid. If 
    * not, an error message is generated.  If it is, the itinerary is updated.
    */
   private void validateDepartureDate() {
      if (!(model.getDepartureDate().compareTo(MIN_ACCEPTABLE_DATE) >= 0)
          &&
          !(model.getDepartureDate().compareTo(MAX_ACCEPTABLE_DATE) <= 0)) {
         
         errorMessages.append("Departure date must be between ")
                      .append(formatDate(MIN_ACCEPTABLE_DATE))
                      .append(" and ")
                      .append(formatDate(MAX_ACCEPTABLE_DATE))
                      .append(".\n");
      } else {
         itinerary.append("Departing: ")
                  .append(formatDate(model.getDepartureDate()))
                  .append("\n");
      }
      
      if (!model.getDepartureDate().isSet(GregorianCalendar.YEAR) 
          ||
          !model.getDepartureDate().isSet(GregorianCalendar.MONTH)
          ||
          !model.getDepartureDate().isSet(GregorianCalendar.DAY_OF_MONTH)) {
         
         errorMessages.append("Departure date must be set.")
                      .append("\n");
      }
   }
   
   /**
    * Private helper function to ensure the return date is set and valid. If 
    * not, an error message is generated.  If it is, the itinerary is updated.
    */
   private void validateReturnDate() {
      if (!(model.getReturnDate().compareTo(MIN_ACCEPTABLE_DATE) >= 0) 
          &&
          !(model.getReturnDate().compareTo(MAX_ACCEPTABLE_DATE) <= 0)) {
         
         errorMessages.append("Return date must be between ")
                      .append(formatDate(MIN_ACCEPTABLE_DATE))
                      .append(" and ")
                      .append(formatDate(MAX_ACCEPTABLE_DATE))
                      .append(".\n");
      } else {
         itinerary.append("Returning: ")
                  .append(formatDate(model.getReturnDate()))
                  .append("\n");
      }
      
      if (!model.getReturnDate().isSet(GregorianCalendar.YEAR) 
          ||
          !model.getReturnDate().isSet(GregorianCalendar.MONTH) 
          ||
          !model.getReturnDate().isSet(GregorianCalendar.DAY_OF_MONTH)) {
         
         errorMessages.append("Return date must be set.")
                      .append("\n");
      }
   }
   
   /**
    * Private helper function to ensure the departure date is on or before the 
    * return date. If not, an error message is generated.  If it is, the 
    * itinerary is updated.
    */
   private void validateRelativeDates() {
      // In this implementation, the value of the "any time" menu item 
      // is 0, which represents the first hour of the day.  (In Java, 
      // hours range from 0 to 23.)  Therefore, if the return date's 
      // time is "any time" and the departure date's time is any choice 
      // EXCEPT "any time", validation will fail if the rest 
      // of the date (i.e., year/month/day) are the same.  For example, 
      // if the departure date is "January 1, 2011 evening" and the 
      // return date is "January 1, 2011 any time", logically this is 
      // a valid combination because "any time" could include 
      // "late night", which is after "evening", but in terms of pure 
      // numbers, it would fail to validate because I have represented 
      // "any time" as 0, "morning" as 7, "noon" as 12, "evening" as 17, 
      // and "late night" as 21.  The below code checks for this 
      // situation and makes sure that these date/time situations are 
      // marked as valid.
      
      Calendar departureDateCopy = model.getDepartureDate();
      Calendar returnDateCopy = model.getReturnDate();

      // if the departure date is on or before the return date, do nothing
      // and return
      if (departureDateCopy.compareTo(returnDateCopy) <= 0) {
         return;

      // otherwise...
      } else {
         // save the return date's hour of day
         int returnHour = returnDateCopy.get(GregorianCalendar.HOUR_OF_DAY);

         // Ignore the hour of day for both the departure date and the 
         // return date.
         departureDateCopy.set(GregorianCalendar.HOUR_OF_DAY, 0);
         returnDateCopy.set(GregorianCalendar.HOUR_OF_DAY, 0);

         // Compare the modified departure and return dates to see if they 
         // are the same and if they are, check the saved return hour to see
         // if it represents "any time".  If it does, then do nothing and 
         // return.
         if (departureDateCopy.compareTo(returnDateCopy) == 0
             &&
             returnHour == DatePickerController.ANYTIME_HOUR) {
            return;
            
         // Otherwise, we are in a situation where the return date is before 
         // the departure date so we need to report the error.
         } else {
            errorMessages.append("Departure date/time must be on or before ")
                         .append("return date/time.")
                         .append("\n");
         }
  
      }      
   }
   
   /**
    * Private helper function to format the date stored in a GregorianCalendar
    * into something practical
    */
   private String formatDate(GregorianCalendar calendar) {
      StringBuilder date = new StringBuilder();
      date.append(calendar.getDisplayName(
               GregorianCalendar.MONTH, 
               GregorianCalendar.LONG, 
               new Locale("US")))
           .append(" ")
           .append(calendar.get(GregorianCalendar.DAY_OF_MONTH))
           .append(", ")
           .append(calendar.get(GregorianCalendar.YEAR))
           .append(" ");
      switch (calendar.get(GregorianCalendar.HOUR_OF_DAY)) {
         case DatePickerController.ANYTIME_HOUR:
            date.append(DatePickerController.ANYTIME_TEXT);
            break;
         case DatePickerController.MORNING_HOUR:
            date.append(DatePickerController.MORNING_TEXT);
            break;
         case DatePickerController.NOON_HOUR:
            date.append(DatePickerController.NOON_TEXT);
            break;
         case DatePickerController.EVENING_HOUR:
            date.append(DatePickerController.EVENING_TEXT);
            break;
         case DatePickerController.LATENIGHT_HOUR:
            date.append(DatePickerController.LATENIGHT_TEXT);
            break;
         default:
            date.append("Unknown time");
      }
      return date.toString();
   }
   
   /**
    * wrapper to set the departure city
    * @param newCity is a String such as "San Francisco"
    */
   public void setDepartureCity(String newCity) {
      model.setDepartureCity(newCity);
   }
   
   /**
    * wrapper to set the arrival city
    * @param newCity is a String such as "Denver"
    */
   public void setArrivalCity(String newCity) {
      model.setArrivalCity(newCity);
   }
      
   /**
    * wrapper to set the departure date
    * @param departureDate is a GregorianCalendar
    */
   public void setDepartureDate(GregorianCalendar departureDate) {
      model.setDepartureDate(departureDate);
   }
   
   /**
    * wrapper to set the return date
    * @param returnDate is a GregorianCalendar
    */
   public void setReturnDate(GregorianCalendar returnDate) {
      model.setReturnDate(returnDate);
   }
   
   /**
    * wrapper to set the number of passengers
    * @param numPassengers
    */
   public void setNumPassengers(int numPassengers) {
      model.setNumPassengers(numPassengers);
   }
   
   /**
    * wrapper to get any error messages generated when the model is validated
    * @return a String containing any error messages to be displayed to the user
    */
   public String getErrorMessages() {
      return errorMessages.toString();
   }
   
   /**
    * wrapper to get the itinerary generated when the model is validated
    * @return a String containing the itinerary to be displayed to the user
    */
   public String getItinerary() {
      return itinerary.toString();
   }
   
   /**
    * a DefaultComboBoxModel containing the acceptable values for the 
    * number of passengers combo box
    * @return a DefaultComboBoxModel representing the choices for the 
    * number of passengers combo box
    */
   public DefaultComboBoxModel getPassengerModel() {
      DefaultComboBoxModel dcbModel = new DefaultComboBoxModel();
      for (Integer i = MIN_PASSENGERS; i <= MAX_PASSENGERS; i++) {
         ComboBoxItem item = new ComboBoxItem(i, i.toString());
         dcbModel.addElement(item);
         if (model.getNumPassengers() == item.getValue()) {
            dcbModel.setSelectedItem(item);
         }
      }
      return dcbModel;
   }
}
