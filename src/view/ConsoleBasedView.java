package view;

import view.ImageView;

import model.Image;

/**
 * This class represents the View; it will display the logs for each operation asked by the user.
 */
public class ConsoleBasedView implements ImageView {

  @Override
  public void printStatements(String statement) {
    System.out.println(statement);
  }

}
