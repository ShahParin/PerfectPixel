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
    log.append("applyCompression called with imageName: ")
            .append(imageName).append(" and newImageName: ").append(newImageName)
            .append(" with percent: ").append(percent).append("\n");
  }

  @Override
  public void applyHistogramVisualization(String imageName, String newImageName) {
    log.append("applyHistogramVisualization called with imageName: ")
            .append(imageName).append(" and newImageName: ").append(newImageName).append("\n");
  }

  @Override
  public void applyColorCorrection(String imageName, String newImageName) {
    log.append("applyColorCorrection called with imageName: ")
            .append(imageName).append(" and newImageName: ").append(newImageName).append("\n");
  }

  @Override
  public void applyLevelsAdjustment(int black, int mid, int white, String imageName,
                                    String newImageName) {
    log.append("applyLevelsAdjustment called with imageName: ")
            .append(imageName).append(" and newImageName: ").append(newImageName)
            .append(" with black: ").append(black).append(", mid: ").append(mid)
            .append(", white: ").append(white).append("\n");
  }

  @Override
  public void blurImageSplit(String imageName, String newImageName, double percentage) {
    log.append("blurImageSplit called with imageName: ")
            .append(imageName).append(" and newImageName: ").append(newImageName)
            .append(" with percentage: ").append(percentage).append("\n");
  }

  @Override
  public void sharpenImageSplit(String imageName, String newImageName, double percentage) {
    log.append("sharpenImageSplit called with imageName: ")
            .append(imageName).append(" and newImageName: ").append(newImageName)
            .append(" with percentage: ").append(percentage).append("\n");
  }

  @Override
  public void sepiaImageSplit(String imageName, String newImageName, double percentage) {
    log.append("sepiaImageSplit called with imageName: ")
            .append(imageName).append(" and newImageName: ").append(newImageName)
            .append(" with percentage: ").append(percentage).append("\n");
  }

  @Override
  public void valueComponentImageSplit(String imageName, String newImageName, double percentage) {
    log.append("valueComponentImageSplit called with imageName: ")
            .append(imageName).append(" and newImageName: ").append(newImageName)
            .append(" with percentage: ").append(percentage).append("\n");
  }

  @Override
  public void lumaComponentImageSplit(String imageName, String newImageName, double percentage) {
    log.append("lumaComponentImageSplit called with imageName: ")
            .append(imageName).append(" and newImageName: ").append(newImageName)
            .append(" with percentage: ").append(percentage).append("\n");
  }

  @Override
  public void intensityComponentImageSplit(String imageName, String newImageName,
                                           double percentage) {
    log.append("intensityComponentImageSplit called with imageName: ")
            .append(imageName).append(" and newImageName: ").append(newImageName)
            .append(" with percentage: ").append(percentage).append("\n");
  }

  @Override
  public void applyColorCorrectionSplit(String imageName, String newImageName, double percentage) {
    log.append("applyColorCorrectionSplit called with imageName: ")
            .append(imageName).append(" and newImageName: ").append(newImageName)
            .append(" with percentage: ").append(percentage).append("\n");
  }

  @Override
  public void applyLevelsAdjustmentSplit(int black, int mid, int white, String imageName,
                                         String newImageName, double percentage) {
    log.append("applyLevelsAdjustmentSplit called with imageName: ")
            .append(imageName).append(" and newImageName: ").append(newImageName)
            .append(" with black: ").append(black).append(", mid: ").append(mid)
            .append(", white: ").append(white).append(", percentage: ").append(percentage)
            .append("\n");
  }

}