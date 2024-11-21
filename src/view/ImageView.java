package view;

import controller.GUIFeatures;

/**
 * This is an Interface for the View. It displays all the output statements to the end user.
 */
public interface ImageView {

  /**
   * A function to display all the print statements.
   *
   * @param statement the print statements to be displayed.
   */
  void printStatements(String statement);

  /**
   * Sets the controller for managing the image features.
   *
   * @param features The GUIFeatures controller to manage image operations.
   */
  void setFeatures(GUIFeatures features);
}
