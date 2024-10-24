package testView;

import view.ImageView;

public class MockImageView implements ImageView {
  private final StringBuilder log = new StringBuilder();

  @Override
  public void printStatements(String message) {
    log.append(message).append("\n");
  }

  public String getLog() {
    return log.toString();
  }

}
