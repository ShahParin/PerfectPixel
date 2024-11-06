package model;

import static model.ImageOperations.applyOperationSplit;
import static model.ImageOperations.colorCorrect;
import static model.ImageOperations.haar;
import static model.ImageOperations.histogramVisualization;
import static model.ImageOperations.invertHaar;
import static model.ImageOperations.levelsAdjust;
import static model.ImageOperations.thresholdChannel;

/**
 * This class extends the class, ImageModelImpl and supports additional operations like compression,
 * histogram, image corrections, and split image view.
 * This class implements the ImageModelV2 interface.
 */
public class ImageModelImplV2 extends ImageModelImpl implements ImageModelV2 {

  /**
   * This constructor initializes the HashMap of the superclass, ImageModelImpl, and fetches
   * its objects.
   */
  public ImageModelImplV2() {
    super();
  }

  @Override
  public void applyCompression(String imageName, String newImageName, double percent) {
    if (percent < 0 || percent > 100) {
      throw new IllegalArgumentException("Percent values should be between 0 and 100.");
    }
    Image original = images.get(imageName);

    double[][] newRed = haar(original.getRedChannel());
    double[][] newGreen = haar(original.getGreenChannel());
    double[][] newBlue = haar(original.getBlueChannel());

    int[][] thresholdRed = thresholdChannel(newRed, percent);
    int[][] thresholdGreen = thresholdChannel(newGreen, percent);
    int[][] thresholdBlue = thresholdChannel(newBlue, percent);

    int[][] invertRed = invertHaar(thresholdRed);
    int[][] invertGreen = invertHaar(thresholdGreen);
    int[][] invertBlue = invertHaar(thresholdBlue);

    Image result = new Image(invertRed, invertGreen, invertBlue);
    images.put(newImageName, result);
  }

  @Override
  public void applyHistogramVisualization(String imageName, String newImageName) {
    Image original = images.get(imageName);
    if (original != null) {
      Image histogramVisualization = histogramVisualization(original);
//      histogramVisualization.clamp();

      images.put(newImageName, histogramVisualization);
    }
  }

  @Override
  public void applyColorCorrection(String imageName, String newImageName) {
    Image original = images.get(imageName);
    if (original != null) {
      Image colorCorrection = colorCorrect(original);

      images.put(newImageName, colorCorrection);

    }
  }

  @Override
  public void applyLevelsAdjustment(int black, int mid, int white, String imageName, String newImageName) {
    Image original = images.get(imageName);
    if (original != null) {
      Image levelsAdjust = levelsAdjust(original, black, mid, white);

      images.put(newImageName, levelsAdjust);
    }
  }

  @Override
  public void blurImageSplit(String imageName, String newImageName, double percentage) {
    Image original = images.get(imageName);
    if (original != null) {
      Image blurredImagePercentage = applyOperationSplit(original, percentage, ImageOperations::blur);
      blurredImagePercentage.clamp();
      images.put(newImageName, blurredImagePercentage);
    }
  }
}
