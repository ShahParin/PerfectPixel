package testmodel;

import model.ImageModelV2;

/**
 * This class is created for implementing mock model for testing the controller with the extended
 * model.
 */
public class MockImageModelV2 extends MockImageModel implements ImageModelV2 {

  @Override
  public void applyCompression(String imageName, String newImageName, double percent) {
    if (percent < 0 || percent > 100) {
      throw new IllegalArgumentException("Percent values should be between 0 and 100.");
    }
  }

  @Override
  public void applyHistogramVisualization(String imageName, String newImageName) {

  }

  @Override
  public void applyColorCorrection(String imageName, String newImageName) {

  }

  @Override
  public void applyLevelsAdjustment(int black, int mid, int white, String imageName,
                                    String newImageName) {

  }

  @Override
  public void blurImageSplit(String imageName, String newImageName, double percentage) {

  }
}
