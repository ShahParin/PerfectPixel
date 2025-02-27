package view;

import controller.GUIFeatures;

/**
 * This class represents the View; it will display the logs for each operation asked by the user.
 */
public class ConsoleBasedView implements ImageView {

  @Override
  public void printStatements(String statement) {
    System.out.println(statement);
  }

  @Override
  public void setFeatures(GUIFeatures features) {
    return;
  }
}
