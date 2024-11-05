package testmodel;

import java.io.IOException;

import model.Image;
import model.ImageModel;

/**
 * This class is created for implementing mock model for testing the controller.
 */
public class MockImageModel implements ImageModel {

  private final StringBuilder log = new StringBuilder();

  @Override
  public void loadImage(String path, String imageName) throws IOException {
    log.append("loadImage called with path: ").append(path)
            .append(" and name: ").append(imageName).append("\n");
  }

  @Override
  public void saveImage(String path, String imageName) {
    log.append("saveImage called with path: ").append(path)
            .append(" and name: ").append(imageName).append("\n");
  }


  @Override
  public void applyRedComponent(String imageName, String newImageName) {
    log.append("applyRedComponent called with imageName: ").append(imageName)
            .append(" and newImageName: ").append(newImageName).append("\n");

  }

  @Override
  public void applyGreenComponent(String imageName, String newImageName) {
    log.append("applyGreenComponent called with imageName: ").append(imageName)
            .append(" and newImageName: ").append(newImageName).append("\n");

  }

  @Override
  public void applyBlueComponent(String imageName, String newImageName) {
    log.append("applyBlueComponent called with imageName: ").append(imageName)
            .append(" and newImageName: ").append(newImageName).append("\n");

  }

  @Override
  public void applyValue(String imageName, String newImageName) {
    log.append("applyValue called with imageName: ").append(imageName)
            .append(" and newImageName: ").append(newImageName).append("\n");

  }

  @Override
  public void applyIntensity(String imageName, String newImageName) {
    log.append("applyIntensity called with imageName: ").append(imageName)
            .append(" and newImageName: ").append(newImageName).append("\n");

  }

  @Override
  public void applyLuma(String imageName, String newImageName) {
    log.append("applyLuma called with imageName: ").append(imageName)
            .append(" and newImageName: ").append(newImageName).append("\n");

  }

  @Override
  public void flipHorizontally(String imageName, String newImageName) {
    log.append("flipHorizontally called with imageName: ").append(imageName)
            .append(" and newImageName: ").append(newImageName).append("\n");

  }

  @Override
  public void flipVertically(String imageName, String newImageName) {
    log.append("flipVertically called with imageName: ").append(imageName)
            .append(" and newImageName: ").append(newImageName).append("\n");

  }

  @Override
  public void brightenImage(int increment, String imageName, String newImageName) {
    log.append("brightenImage called with imageName: ").append(imageName)
            .append(" and newImageName: ").append(newImageName).append("\n");

  }

  @Override
  public void blurImage(String imageName, String newImageName) {
    log.append("blurImage called with imageName: ").append(imageName)
            .append(" and newImageName: ").append(newImageName).append("\n");

  }

  @Override
  public void sharpenImage(String imageName, String newImageName) {
    log.append("sharpenImage called with imageName: ").append(imageName)
            .append(" and newImageName: ").append(newImageName).append("\n");

  }

  @Override
  public void applySepia(String imageName, String newImageName) {
    log.append("applySepia called with imageName: ").append(imageName)
            .append(" and newImageName: ").append(newImageName).append("\n");

  }

  @Override
  public void rgbSplit(String imageName, String redImage, String greenImage, String blueImage) {
    log.append("rgbSplit called with imageName: ").append(imageName)
            .append(" and redImageName: ").append(redImage).append(" and greenImage: ")
            .append(greenImage).append(" and blueImage: ").append(blueImage).append("\n");

  }

  @Override
  public void rgbCombine(String newImageName, String redImage, String greenImage,
                         String blueImage) {
    log.append("rgbCombine called with newImageName: ").append(newImageName)
            .append(" and redImage: ").append(redImage).append(" and greenImage: ")
            .append(greenImage).append(" and blueImage: ").append(blueImage).append("\n");


  }

  @Override
  public Image getImage(String imgName) {
    return null;
  }

  @Override
  public void applyHistogramVisualization(String imageName, String newImageName) {

  }

  @Override
  public void applyColorCorrection(String imageName, String newImageName) {

  }

  @Override
  public void applyLevelsAdjustment(int black, int mid, int white, String imageName, String newImageName) {

  }

  @Override
  public void blurImageSplit(String imageName, String newImageName, double percentage) {

  }

  public String getLog() {
    return log.toString();
  }

  public void clearLog() {
    log.setLength(0); // Clear the log for reuse
  }
}
