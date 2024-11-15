package view;

import model.Image;

/**
 * This class represents the View, it will display the logs for each operation asked by the user.
 */
public class ConsoleBasedView implements ImageView {

  @Override
  public void printStatements(String statement) {
    throw new UnsupportedOperationException("Wrong Class.");
  }

  @Override
  public void LoadFile(Image image) {
    System.out.println("Loading fileeeeeeeeeeee: " + image);
    throw new UnsupportedOperationException("Wrong Class.");
  }

  @Override
  public void SaveFile(String filename) {
    throw new UnsupportedOperationException("Wrong Class.");
  }
}
