package testview;

import model.Image;
import view.ImageView;

/**
 * This class is created for implementing mock view for testing the controller.
 */
public class MockImageView implements ImageView {
  private final StringBuilder log = new StringBuilder();

  @Override
  public void printStatements(String message) {
    log.append(message).append("\n");
  }

  /**
   * Returns the operation log as a string.
   *
   * @return the logs
   */
  public String getLog() {
    return log.toString();
  }

}
