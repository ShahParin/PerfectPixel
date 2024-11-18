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

  @Override
  public void LoadFile(Image image) {

  }

  @Override
  public void SaveFile(String filename) {

  }

  @Override
  public void blurImage(Image image) {

  }

  public String getLog() {
    return log.toString();
  }

}
