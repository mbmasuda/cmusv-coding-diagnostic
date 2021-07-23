/**
 * ComboBoxItem.java
 * Feb. 19, 2012
 * Copyright © 2012 
 * @version 1.0
 * @author Mari Masuda
 */

package org.marimasuda.cmusvdiagnostic;

/**
 * Represents one item to be contained in a combo box model.  Use of this 
 * class allows the text displayed in the combo box to be different than
 * the actual value that the text represents.  For example, the combo box 
 * choices as seen in the GUI could be "one", "two", "three" while the 
 * respective values are the integers 1, 2, and 3.
 */
public class ComboBoxItem {
   /** the internal "machine" value of the represented item */
   private int value;

   /** the external "human-readable" value of the represented item */
   private String description;

   /**
    * Creates a new ComboBoxItem to represent something with both a 
    * "machine" value and a "human-readable" value;
    * @param value an integer representing the "machine" value.
    * @param description a String representing the "human-readable" value.
    *                     If the provided argument is null, the description
    *                     will be the String representation of the value
    */
   public ComboBoxItem(Integer value, String description) {
      this.value = value;
      this.description = 
         (description == null ? value.toString() : description);
   }

   /**
    * @return the "machine" value
    */
   public int getValue() {
      return value;
   }

   /**
    * @return the "human-readable" description
    */
   @Override
   public String toString() {
      return description;
   }
}
