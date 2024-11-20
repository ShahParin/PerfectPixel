package testcontroller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import controller.GUIFeatures;
import model.Image;

/**
 * This class is created for implementing mock GUI Features for mocking GUI.
 */
public class MockGUIFeatures implements GUIFeatures {
  private final StringBuilder log;

  /**
   * Constructs a new instance of MockGUIFeatures.
   */
  public MockGUIFeatures() {
    log = new StringBuilder();
  }

  @Override
  public void load(String path, String imageName) throws IOException {
    log.append("load called with imageName: ").append(imageName)
            .append(" for the path: ").append(path).append("\n");
  }

  @Override
  public void save(String path, String imageName) throws IOException {
    log.append("save called with imageName: ").append(imageName)
            .append(" for the path: ").append(path).append("\n");
  }

  @Override
  public void blur(String imageName, String newImageName) {
    log.append("blur called with imageName: ").append(imageName)
            .append(" and newImageName: ").append(newImageName).append("\n");
  }

  @Override
  public void sharpen(String imageName, String newImageName) throws IOException {
    log.append("sharpen called with imageName: ").append(imageName)
            .append(" and newImageName: ").append(newImageName).append("\n");
  }

  @Override
  public void horizontalFlip(String imageName, String newImageName) throws IOException {
    log.append("horizontalFlip called with imageName: ").append(imageName)
            .append(" and newImageName: ").append(newImageName).append("\n");
  }

  @Override
  public void verticalFlip(String imageName, String newImageName) throws IOException {
    log.append("verticalFlip called with imageName: ").append(imageName)
            .append(" and newImageName: ").append(newImageName).append("\n");
  }

  @Override
  public void greyscale(String imageName, String newImageName) throws IOException {
    log.append("greyscale called with imageName: ").append(imageName)
            .append(" and newImageName: ").append(newImageName).append("\n");
  }

  @Override
  public void sepia(String imageName, String newImageName) throws IOException {
    log.append("sepia called with imageName: ").append(imageName)
            .append(" and newImageName: ").append(newImageName).append("\n");
  }

  @Override
  public void redComponent(String imageName, String newImageName) throws IOException {
    log.append("redComponent called with imageName: ").append(imageName)
            .append(" and newImageName: ").append(newImageName).append("\n");
  }

  @Override
  public void greenComponent(String imageName, String newImageName) throws IOException {
    log.append("greenComponent called with imageName: ").append(imageName)
            .append(" and newImageName: ").append(newImageName).append("\n");
  }

  @Override
  public void blueComponent(String imageName, String newImageName) throws IOException {
    log.append("blueComponent called with imageName: ").append(imageName)
            .append(" and newImageName: ").append(newImageName).append("\n");
  }

  @Override
  public void compress(String imageName, String newImageName, double percent) throws IOException {
    log.append("compress called with imageName: ").append(imageName)
            .append(" and newImageName: ").append(newImageName)
            .append(" with percent: ").append(percent).append("\n");
  }

  @Override
  public void levelsAdjust(String imageName, String newImageName, int black, int mid, int white)
          throws IOException {
    log.append("levelsAdjust called with imageName: ").append(imageName)
            .append(" and newImageName: ").append(newImageName)
            .append(" with black: ").append(black).append(", mid: ").append(mid)
            .append(", white: ").append(white).append("\n");
  }

  @Override
  public void colorCorrect(String imageName, String newImageName) throws IOException {
    log.append("colorCorrect called with imageName: ").append(imageName)
            .append(" and newImageName: ").append(newImageName).append("\n");
  }

  @Override
  public Image getImage(String imageName) {
    log.append("getImage called with imageName: ").append(imageName).append("\n");
    return null;
  }

  @Override
  public Image getHistogram(String imageName) {
    log.append("getHistogram called with imageName: ").append(imageName).append("\n");
    return null;
  }

  @Override
  public void blurSplitOperation(String imageName, String newImageName, double percent)
          throws IOException {
    log.append("blurSplitOperation called with imageName: ").append(imageName)
            .append(" and newImageName: ").append(newImageName)
            .append(" with percent: ").append(percent).append("\n");
  }

  @Override
  public void levelsAdjustmentSplitOperation(String currentImageName, String outputImageName,
                                             double black, double mid, double white, double percent)
          throws IOException {
    log.append("levelsAdjustmentSplitOperation called with imageName: ").append(currentImageName)
            .append(" and newImageName: ").append(outputImageName)
            .append(" with black: ").append(black).append(", mid: ").append(mid)
            .append(", white: ").append(white)
            .append(" with percent: ").append(percent).append("\n");
  }

  @Override
  public void sharpenSplitOperation(String currentImageName, String outputImageName,
                                    double inputValue) throws IOException {
    log.append("sharpenSplitOperation called with imageName: ").append(currentImageName)
            .append(" and newImageName: ").append(outputImageName)
            .append(" with percent: ").append(inputValue).append("\n");
  }

  @Override
  public void sepiaSplitOperation(String currentImageName, String outputImageName,
                                  double inputValue) throws IOException {
    log.append("sepiaSplitOperation called with imageName: ").append(currentImageName)
            .append(" and newImageName: ").append(outputImageName)
            .append(" with percent: ").append(inputValue).append("\n");
  }

  @Override
  public void greyscaleSplitOperation(String currentImageName, String outputImageName,
                                      double inputValue) throws IOException {
    log.append("greyscaleSplitOperation called with imageName: ").append(currentImageName)
            .append(" and newImageName: ").append(outputImageName)
            .append(" with percent: ").append(inputValue).append("\n");
  }

  @Override
  public void colorCorrectionSplitOperation(String currentImageName, String outputImageName,
                                            double inputValue) throws IOException {
    log.append("colorCorrectionSplitOperation called with imageName: ").append(currentImageName)
            .append(" and newImageName: ").append(outputImageName)
            .append(" with percent: ").append(inputValue).append("\n");
  }

  /**
   * Returns the accumulated log as a string.
   *
   * @return A string representation of the log, which is the contents of the log accumulated
   *         in the StringBuilder.
   */
  public String getLog() {
    return log.toString();
  }

}

