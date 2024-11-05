package model;


import static model.ImageOperations.applyBlurSplit;
import static model.ImageOperations.colorCorrect;
import static model.ImageOperations.haar;
import static model.ImageOperations.histogramVisualization;
import static model.ImageOperations.invertHaar;
import static model.ImageOperations.levelsAdjust;
import static model.ImageOperations.thresholdChannel;

public class ImageModelImplV2 extends ImageModelImpl implements ImageModelV2 {

  /*
  This class uses a Map to store images by their names and applies different operations
  such as flipping, brightening, and applying filters on these images.
  */

  /**
   * This constructor initializes the HashMap.
   */
  public ImageModelImplV2() {
    super();
  }

  public void applyCompression(String imageName, String newImageName, double percent) {
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
      Image blurredImagePercentage = applyBlurSplit(original, percentage);
      blurredImagePercentage.clamp();
      images.put(newImageName, blurredImagePercentage);
    }
  }
}
