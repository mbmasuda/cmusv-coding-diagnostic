/**
 * DatePickerView.java
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
import java.util.GregorianCalendar;

import javax.swing.JComboBox;
import javax.swing.JPanel;

/**
 * A Swing GUI to front a DatePickerModel.  Displays as four JComboBoxes
 * in a horizontal row with one combo box each for picking the month,
 * day of month, year, and hour of day.
 */
public class DatePickerView extends JPanel {
   /** constant used by firePropertyChange to differentiate the event's source */
   public static final String PROPERTY_CHANGE_IDENTIFIER = "date picker";
   
   /** constant representing the amount of pixels to use as padding by the 
    * layout manager
    */
   private static final int INSET_PADDING = 1;

   /** this view's controller */
   private DatePickerController controller;

   /** a GregorianCalendar used to hold the value of the model before it was 
    * changed so that firePropertyChange can send both the old state and the 
    * new state
    */
   private GregorianCalendar oldModel;

   /** combo box holding the available months */
   private JComboBox monthPicker;

   /** combo box holding the available days of the month */
   private JComboBox dayPicker;

   /** combo box holding the available years */
   private JComboBox yearPicker;
   
   /** combo box holding the available hours of the day */
   private JComboBox hourPicker;
   
   /** GUI layout helper object */
   private GridBagConstraints gbc;

   /**
    * Constructs a new view that allows the user to use combo boxes to 
    * select a date
    */
   public DatePickerView() {
      super(new GridBagLayout());
      controller = new DatePickerController();
      oldModel = null;
      
      yearPicker = new JComboBox(controller.getYearModel());
      monthPicker = new JComboBox(controller.getMonthModel());
      dayPicker = new JComboBox(controller.getDayModel());
      hourPicker = new JComboBox(controller.getHours());
      
      DatePickerYearListener yearListener = new DatePickerYearListener();
      DatePickerMonthListener monthListener = new DatePickerMonthListener();
      DatePickerDayListener dayListener = new DatePickerDayListener();
      DatePickerHourListener hourListener = new DatePickerHourListener();
      yearPicker.addActionListener(yearListener);
      monthPicker.addActionListener(monthListener);
      dayPicker.addActionListener(dayListener);
      hourPicker.addActionListener(hourListener);

      gbc = new GridBagConstraints();
      gbc.gridwidth = 1;
      gbc.gridheight = 1;
      gbc.insets = new Insets(
            INSET_PADDING, 
            INSET_PADDING, 
            INSET_PADDING, 
            INSET_PADDING
      );

      gbc.anchor = GridBagConstraints.LINE_START;
      gbc.gridx = 0;
      gbc.gridy = 0;
      add(monthPicker, gbc);

      gbc.gridx = 1;
      gbc.gridy = 0;
      add(dayPicker, gbc);

      gbc.gridx = 2;
      gbc.gridy = 0;
      add(yearPicker, gbc);

      gbc.gridx = 3;
      gbc.gridy = 0;
      add(hourPicker, gbc);
   }
      
   /**
    * Inner class to handle events when the year is changed
    */
   private class DatePickerYearListener implements ActionListener {
      @Override
      public void actionPerformed(ActionEvent ae) {
         ComboBoxItem selectedItem = 
            (ComboBoxItem) ((JComboBox) ae.getSource()).getSelectedItem();
         int year = selectedItem.getValue();
         controller.setYear(year);
         dayPicker.setModel(controller.getDayModel());
         GregorianCalendar newModel = controller.getValue();
         firePropertyChange(PROPERTY_CHANGE_IDENTIFIER, 
                            oldModel, 
                            newModel);
         oldModel = newModel;
      }
   }
   
   /**
    * Inner class to handle events when the month is changed
    */
   private class DatePickerMonthListener implements ActionListener {
      @Override
      public void actionPerformed(ActionEvent ae) {
         ComboBoxItem selectedItem = 
            (ComboBoxItem) ((JComboBox) ae.getSource()).getSelectedItem();
         int month = selectedItem.getValue();
         controller.setMonth(month);
         dayPicker.setModel(controller.getDayModel());
         GregorianCalendar newModel = controller.getValue();
         firePropertyChange(PROPERTY_CHANGE_IDENTIFIER, 
                            oldModel, 
                            newModel);
         oldModel = newModel;
      }
   }
   
   /**
    * Inner class to handle events when the day of month is changed
    */
   private class DatePickerDayListener implements ActionListener {
      @Override
      public void actionPerformed(ActionEvent ae) {
         ComboBoxItem selectedItem = 
            (ComboBoxItem) ((JComboBox) ae.getSource()).getSelectedItem();
         int day = selectedItem.getValue();
         controller.setDay(day);
         GregorianCalendar newModel = controller.getValue();
         firePropertyChange(PROPERTY_CHANGE_IDENTIFIER, 
                            oldModel, 
                            newModel);
         oldModel = newModel;
      }
   }
   
   /**
    * Inner class to handle events when the hour of day is changed
    */
   private class DatePickerHourListener implements ActionListener {
      @Override
      public void actionPerformed(ActionEvent ae) {
         ComboBoxItem selectedItem = 
            (ComboBoxItem) ((JComboBox) ae.getSource()).getSelectedItem();
         int hour = selectedItem.getValue();
         controller.setHour(hour);
         GregorianCalendar newModel = controller.getValue();
         firePropertyChange(PROPERTY_CHANGE_IDENTIFIER, 
                            oldModel, 
                            newModel);
         oldModel = newModel;
      }
   }   
}
