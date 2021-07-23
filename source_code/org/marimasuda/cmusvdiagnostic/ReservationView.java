/**
 * ReservationView.java
 * Feb. 19, 2012
 * Copyright © 2012 
 * @version 1.0
 * @author Mari Masuda
 */

package org.marimasuda.cmusvdiagnostic;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.GregorianCalendar;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

/**
 * A Swing GUI to front a ReservationModel.
 */
public class ReservationView extends JPanel {
   /** constant representing the amount of pixels to use as padding by the 
    * layout manager
    */
   private static final int INSET_PADDING = 5;

   /** this view's controller */
   private ReservationController controller;

   /** label for the departure city text field */
   private JLabel departureCityLabel;

   /** label for the arrival city text field */
   private JLabel arrivalCityLabel;
   
   /** label for the departure date date picker */
   private JLabel departureDateLabel;
   
   /** label for the return date date picker */
   private JLabel returnDateLabel;

   /** label for the number of passengers combo box */
   private JLabel numPassengersLabel;
   
   /** the text field in which to enter the departure city */
   private JTextField departureCityTextField;

   /** the text field in which to enter the arrival city */
   private JTextField arrivalCityTextField;   

   /** the date picker in which to enter the departure date */
   private DatePickerView departureDatePicker;

   /** the date picker in which to enter the return date */
   private DatePickerView returnDatePicker;

   /** the combo box in which to enter the number of passengers */
   private JComboBox numPassengersComboBox;

   /** the button that, when pressed, causes validation to occur */
   private JButton submitButton;
   
   /** GUI layout helper object */
   private GridBagConstraints gbc;

   /**
    * Constructs a new ReservationView, initializes components, and assigns
    * various listeners to those components.
    */
   public ReservationView() {
      super(new GridBagLayout());
      controller = new ReservationController();
      
      departureCityLabel = new JLabel("From:");
      arrivalCityLabel = new JLabel("To:");
      departureDateLabel = new JLabel("Departure date:");
      returnDateLabel = new JLabel("Return date:");
      numPassengersLabel = new JLabel("Passengers:");
      departureCityTextField = new JTextField();
      arrivalCityTextField = new JTextField();
      departureDatePicker = new DatePickerView();
      returnDatePicker = new DatePickerView();
      numPassengersComboBox = new JComboBox(controller.getPassengerModel());
      submitButton = new JButton("Submit");
      
      departureDatePicker.addPropertyChangeListener(new DepartureDatePickerListener());
      returnDatePicker.addPropertyChangeListener(new ReturnDatePickerListener());
      departureCityTextField.addCaretListener(new DepartureTextFieldListener());
      arrivalCityTextField.addCaretListener(new ArrivalTextFieldListener());
      numPassengersComboBox.addActionListener(new NumPassengersListener());
      submitButton.addActionListener(new SubmitButtonListener());
      
      gbc = new GridBagConstraints();
      gbc.gridwidth = 1;
      gbc.gridheight = 1;
      gbc.insets = new Insets(
            INSET_PADDING, 
            INSET_PADDING, 
            INSET_PADDING, 
            INSET_PADDING
      );
      
      gbc.anchor = GridBagConstraints.LINE_END;

      gbc.gridx = 0;
      gbc.gridy = 0;
      add(departureCityLabel, gbc);

      gbc.gridx = 0;
      gbc.gridy = 1;
      add(arrivalCityLabel, gbc);

      gbc.gridx = 0;
      gbc.gridy = 2;
      add(departureDateLabel, gbc);

      gbc.gridx = 0;
      gbc.gridy = 3;
      add(returnDateLabel, gbc);

      gbc.gridx = 0;
      gbc.gridy = 4;
      add(numPassengersLabel, gbc);

      gbc.gridx = 1;
      gbc.gridy = 5;
      add(submitButton, gbc);

      gbc.anchor = GridBagConstraints.LINE_START;
      gbc.fill = GridBagConstraints.HORIZONTAL;

      gbc.gridx = 1;
      gbc.gridy = 0;
      add(departureCityTextField, gbc);

      gbc.gridx = 1;
      gbc.gridy = 1;
      add(arrivalCityTextField, gbc);

      gbc.fill = GridBagConstraints.NONE;
      
      gbc.gridx = 1;
      gbc.gridy = 2;
      add(departureDatePicker, gbc);

      gbc.gridx = 1;
      gbc.gridy = 3;
      add(returnDatePicker, gbc);

      gbc.gridx = 1;
      gbc.gridy = 4;
      add(numPassengersComboBox, gbc);
   }
   
   /**
    * Inner class to handle updating this view's model with the properties of 
    * the departure date picker by listening for changes in the date picker's model.
    */
   private class DepartureDatePickerListener implements PropertyChangeListener {
      @Override
      public void propertyChange(PropertyChangeEvent pce) {
         if (pce.getPropertyName().equals(DatePickerView.PROPERTY_CHANGE_IDENTIFIER)) {
            controller.setDepartureDate((GregorianCalendar) pce.getNewValue());
         }
      }
   }
   
   /**
    * Inner class to handle updating this view's model with the properties of 
    * the return date picker by listening for changes in the date picker's model.
    */
   private class ReturnDatePickerListener implements PropertyChangeListener {
      @Override
      public void propertyChange(PropertyChangeEvent pce) {
         if (pce.getPropertyName().equals(DatePickerView.PROPERTY_CHANGE_IDENTIFIER)) {
            controller.setReturnDate((GregorianCalendar) pce.getNewValue());
         }
      }
   }
   
   /**
    * Inner class to handle updating this view's model with the current value 
    * of the departure text field.
    */
   private class DepartureTextFieldListener implements CaretListener {
      @Override
      public void caretUpdate(CaretEvent ce) {
         JTextField source = (JTextField) ce.getSource();
         String text = source.getText().trim();
         controller.setDepartureCity(text);
      }
   }

   /**
    * Inner class to handle updating this view's model with the current value 
    * of the arrival text field.
    */
   private class ArrivalTextFieldListener implements CaretListener {
      @Override
      public void caretUpdate(CaretEvent ce) {
         JTextField source = (JTextField) ce.getSource();
         String text = source.getText().trim();
         controller.setArrivalCity(text);
      }
   }

   /**
    * Inner class to handle updating this view's model with the current value 
    * of the number of passengers combo box.
    */
   private class NumPassengersListener implements ActionListener {
      @Override
      public void actionPerformed(ActionEvent ae) {
         JComboBox source = (JComboBox) ae.getSource();
         ComboBoxItem comboBoxItem = (ComboBoxItem) source.getSelectedItem();
         int value = comboBoxItem.getValue();
         controller.setNumPassengers(value);
      }      
   }
   
   /**
    * Inner class to handle when the submit button is pressed.  It validates 
    * the model and then shows either the itinerary if the model is valid, or 
    * an error message is the model is not valid.
    */
   private class SubmitButtonListener implements ActionListener {
      @Override
      public void actionPerformed(ActionEvent ae) {
         if (controller.isValid()) {
            JOptionPane.showMessageDialog(
                  null, 
                  controller.getItinerary(),
                  "Itinerary", 
                  JOptionPane.INFORMATION_MESSAGE);
         } else {
            JOptionPane.showMessageDialog(
                  null, 
                  controller.getErrorMessages(),
                  "An error has occurred", 
                  JOptionPane.ERROR_MESSAGE);

         }
      }
   }
}
