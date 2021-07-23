/**
 * ReservationModel.java
 * Feb. 19, 2012
 * Copyright © 2012 
 * @version 1.0
 * @author Mari Masuda
 */

package org.marimasuda.cmusvdiagnostic;

import java.util.GregorianCalendar;

/**
 * a model that keeps track of the details of a travel reservation, including 
 * the departure and arrival cities, departure and return dates, and the 
 * number of passengers
 */
public class ReservationModel {
   /** the city from which the user is departing, e.g., San Francisco */
   private String departureCity;

   /** the city to which the user is traveling, e.g., Boston */
   private String arrivalCity;

   /** a GregorianCalendar containing the details of the departure date */
   private GregorianCalendar departureDate;

   /** a GregorianCalendar containing the details of the return date */
   private GregorianCalendar returnDate;

   /** the number of passengers */
   private int numPassengers;
   
   /**
    * Constructs a new ReservationModel and initializes the departure and arrival
    * cities, the departure and return dates, and the number of passengers to
    * default values
    */
   public ReservationModel() {
      departureCity = "";
      arrivalCity = "";
      departureDate = new GregorianCalendar();
      departureDate.clear();
      departureDate.setLenient(false);
      returnDate = new GregorianCalendar();
      returnDate.clear();
      returnDate.setLenient(false);
      numPassengers = 1;
   }
   
   /**
    * @return the name of the city from which the user will be departing
    */
   public String getDepartureCity() {
      return departureCity;
   }

   /**
    * @param the name of the city from which the user will be departing
    */
   public void setDepartureCity(String departureCity) {
      this.departureCity = departureCity;
   }

   /**
    * @return the name of the city to which the user will be traveling
    */
   public String getArrivalCity() {
      return arrivalCity;
   }

   /**
    * @param the name of the city to which the user will be traveling
    */
   public void setArrivalCity(String arrivalCity) {
      this.arrivalCity = arrivalCity;
   }

   /**
    * @return a copy of the details of the departure date
    */
   public GregorianCalendar getDepartureDate() {
      return (GregorianCalendar) departureDate.clone();
   }

   /**
    * @param the details of the departure date
    */
   public void setDepartureDate(GregorianCalendar departureDate) {
      this.departureDate = departureDate;
   }

   /**
    * @return a copy of the details of the return date
    */
   public GregorianCalendar getReturnDate() {
      return (GregorianCalendar) returnDate.clone();
   }

   /**
    * @param the details of the return date
    */
   public void setReturnDate(GregorianCalendar returnDate) {
      this.returnDate = returnDate;
   }

   /**
    * @return the number of passengers in the traveling party
    */
   public int getNumPassengers() {
      return numPassengers;
   }

   /**
    * @param the number of passengers in the traveling party
    */
   public void setNumPassengers(int numPassengers) {
      this.numPassengers = numPassengers;
   }
}
