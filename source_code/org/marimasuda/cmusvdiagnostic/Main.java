/**
 * Main.java
 * Feb. 19, 2012
 * Copyright © 2012 
 * @version 1.0
 * @author Mari Masuda
 */

package org.marimasuda.cmusvdiagnostic;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * A simple class to start the CMUSV coding diagnostic program.  Creates 
 * and displays the GUI on the event dispatch thread.
 */
public class Main extends JFrame {
   public Main() {
      ReservationView theGUI = new ReservationView();
      setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      setResizable(false);
      add(theGUI);
      pack();
      setVisible(true);      
   }
   
   public static void main(String[] args) {
      SwingUtilities.invokeLater(new Runnable() {
         public void run() {
            new Main();
         }
     });      
   }   
}
